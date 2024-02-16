# Annotations

## Controller

### **@RestController**

**Description**: This annotation is **used at the class level** and denotes a special controller that handles HTTP requests. `@RestController` simplifies the development of RESTful web services by combining `@Controller` and `@ResponseBody`, meaning the **data returned by each method is written** directly to the body of the response **in a format like JSON** or XML.

```java
@RestController
public class GreetingController {
    
    @GetMapping("/greeting")
    public String sendGreeting() {
        return "Hello, World!";
    }
}
```

### **@RequestMapping**

**Description**: **Applied at the class or method level** to define routing information. It can specify the URL, HTTP method, and other request parameters. **When used at the class level, it sets a base path for all resource URLs in the controller**.

```java
@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        // Logic to fetch and return the user by userId
    }
    
    @PostMapping("/")
    public User createUser(@RequestBody User newUser) {
        // Logic to create a new user
    }
}
```

### **@GetMapping**

**Description**: A shortcut for `@RequestMapping(method = RequestMethod.GET)` that is **only used at the method level**. It indicates that a method should be used to handle a GET request for a specific URL.

```java
@RestController
public class BookController {
    
    @GetMapping("/books/{bookId}")
    public Book getBookDetail(@PathVariable String bookId) {
        // Logic to fetch and return the book details by bookId
    }
}
```

### **@PostMapping**

**Description**: `@PostMapping` is a composed annotation that acts as a shortcut for `@RequestMapping(method = RequestMethod.POST)`. It is specifically designed to handle HTTP POST requests, making it ideal for creating resources or submitting data to be processed.

```java
@RestController
public class RegistrationController {
    
    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) {
        // Logic to register a new user
        // This might involve saving the user to a database and returning the saved entity
        return newUser; // This is a simplified example. In practice, you would return the saved user object.
    }
}
```

### @PutMapping

`@PutMapping` is a composite annotation that is used to handle HTTP PUT requests. It's a shorthand for `@RequestMapping(method = RequestMethod.PUT)`. It maps HTTP PUT requests onto specific handler methods.

```java
@RestController
public class MyController {

    @PutMapping("/items/{id}")
    public Item updateItem(@RequestBody Item item, @PathVariable Long id) {
        // Logic to update item
        return item;
    }
}
```

### @PatchMapping

`@PatchMapping` is a composite annotation that is used to handle HTTP PATCH requests. It's a shorthand for `@RequestMapping(method = RequestMethod.PATCH)`. This annotation maps HTTP PATCH requests to specific handler methods, typically used for partial updates to a resource.

```java
@RestController
public class MyController {

    @PatchMapping("/items/{id}")
    public Item partialUpdateItem(@RequestBody Item item, @PathVariable Long id) {
        // Logic for partial update of item
        return item;
    }
}
```

### @RequestParam

`@RequestParam` is used to **extract query parameters from the query string of the HTTP request**. It binds the value(s) of a query parameter to a method parameter in your handler method.

```java
@RestController
public class MyController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }
}
```

### @RequestBody

`@RequestBody` is primarily used to **receive data within the JSON string sent from the frontend to the backend (data in the request body)**. Spring automatically uses the appropriate HTTP message converter to **convert the request body's JSON string into the corresponding Java object**. This allows you to directly manipulate these data in your method, eliminating the need to manually parse the JSON string. 

This is especially useful when you're sending data to a website to do things like signing up or updating your profile, using methods known as **POST or PUT requests**

```java
@RestController
public class MyController {

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        // Logic to add item
        return item;
    }
}
```

### @Validated

`@Validated` is used to **enable validation on the annotated element**. It can be applied at the class level or method parameter level. When used on method parameters (especially with `@RequestBody`), it triggers validation for the incoming object.

```java
@RestController
public class MyController {

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        // Logic to add user, user object will be validated
        return user;
    }
}
```

### @URL

the `@URL` annotation **checks that the `url` field is a valid HTTPS URL**.

## Initialization

### **@SpringBootApplication**

This annotation is used on the main application class to mark it as the **entry point for Spring Boot**.

```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

## Dependency Injection

### **@Autowired**

The `@Autowired` annotation in Spring is used to enable automatic dependency injection. What this means is that Spring automatically provides the required beans (objects managed by the Spring container) into your class without you having to manually instantiate or configure them.

When you mark a field, constructor, or setter method with `@Autowired`, Spring looks for a bean that matches the required type and injects it at runtime. This process is part of Spring's Inversion of Control (IoC) container where the framework takes control of managing the beans and their dependencies.

### @Service

The `@Service` annotation is used to mark a class as a service provider in your application. It's a specialization of the `@Component` annotation, signaling to the Spring framework that the annotated class should be treated as a service layer bean. These classes typically contain business logic, operations, or calls to the database.

Classes annotated with `@Service` are automatically detected by Spring (through classpath scanning) and are instantiated as beans in the Spring application context. This makes them available for dependency injection where needed.

**Step 1: Define the `UserService` Interface**

```java
public interface UserService {
    User findByUserName(String username);
    void register(String username, String password);
}
```

**Step 2: Implement the `UserService` with `UserServiceImpl`**

```java
@Service
public class UserServiceImpl implements UserService {
    
  	@Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        User user = new User(username, password); // Simplified for illustration
        userRepository.save(user);
    }
}
```

**Step 3: Create the `UserController` to Handle Requests**

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService; // Spring injects an instance of UserServiceImpl here

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUserName(username);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        userService.register(user.getUsername(), user.getPassword());
    }
}
```

`UserService` defines a series of business operation methods. This acts as a contract or protocol, specifying which operations can be executed, but it does not involve the specific logic of execution.

`UserServiceImpl` implements the `UserService` interface and overrides all the methods defined in the interface. This is where the specific business logic is implemented.

When you use the `@Service` annotation on `UserServiceImpl`, this annotation tells Spring, "This class is a service layer component, and I want you to manage it." Therefore, Spring will scan this class at startup and instantiate it as a bean managed by Spring. This process is automatic, based on classpath scanning and annotations.

The key point is that `@Service` is annotated on the implementation class (i.e., `UserServiceImpl`), not on the interface `UserService`. `UserService` itself does not become a bean, but the instance of `UserServiceImpl` (as an implementation of `UserService`) becomes a bean.

When you use the `@Autowired` annotation to refer to the `UserService` interface in `UserController`, you are telling Spring, "Please provide me with an instance of an implementation of this interface." Since `UserServiceImpl` is the only implementation of `UserService` and is annotated with `@Service` (i.e., it is already a bean), the Spring container will automatically inject an instance of `UserServiceImpl` into the `UserService` reference in `UserController`.

## Data Representation

### **@Data**



## Constructor Generation

### **@NoArgsConstructor**

### **@AllArgsConstructor**



