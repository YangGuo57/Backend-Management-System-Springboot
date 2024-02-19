# MVC Framework

 The Model-View-Controller (MVC) is a **design pattern** widely used in the development of user interfaces. It divides an application into three interconnected components:

![Model1](/Users/guoyang/Desktop/management-event/MVC.jpg)

- **Model**: The Model represents the application's dynamic data structure, independent of the user interface. It directly manages the data, logic, and rules of the application.

- **View**: The View represents the visual representation of the Model. It is responsible for rendering the Model data to the user and provides the capability for user interaction.

- **Controller**: The Controller acts as an intermediary between the Model and the View. It listens to the user input from the View, processes the input (often involving interactions with the Model), and returns the output display to the View.

![maxresdefault](/Users/guoyang/Desktop/management-event/SpringBoot.png)

Spring Boot is an extension of the Spring framework that simplifies the initial setup and development of new Spring applications. It follows the MVC architecture closely and provides an easy way to create stand-alone, production-grade Spring-based applications that you can "just run."

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

Classes annotated with `@Service` are automatically detected by Spring (through classpath scanning) and are **instantiated as beans** in the Spring application context. This makes them available for dependency injection where needed.

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

## Simplification Annotations

### **@Data**

The `@Data` annotation provided by **Lombok** is a simplification annotation used to generate common Java class methods automatically. It automatically generates `getter` and `setter` methods for each field in the class, along with `equals()`, `hashCode()`, and `toString()` methods. By using the `@Data` annotation, the amount of boilerplate code can be significantly reduced, enhancing the readability and maintainability of the code.

```java
import lombok.Data;

@Data
public class Example {
    private String name;
    private int age;
}
```

In the above example, the `@Data` annotation automatically generates `getName()`, `setName()`, `getAge()`, and `setAge()` methods, as well as `equals()`, `hashCode()`, and `toString()` methods, without the need for manual coding.

## **Data Validation** 

### @Validated

**Description**: The `@Validated` annotation is used in Spring applications to enable method-level parameter validation. It works in conjunction with Bean Validation annotations to validate method parameters based on specified constraints.

```java
@RestController
@Validated
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<String> createUser(
            @RequestBody @Validated User user) {
        return ResponseEntity.ok("User created successfully");
    }
    
}

public class User {
    @NotNull
    @Size(min = 2, max = 50)
    private String username;
    
    @NotNull
    @Size(min = 6, max = 20)
    private String password;
    
}

```

### @JsonFormat

**Description**: The `@JsonFormat` annotation is **used at the field level** in a class to specify how dates, times, or numbers should be formatted when the class is serialized into JSON.

```java
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime updateTime;
```

## MyBatis

### @Mapper

The `@Mapper` annotation in MyBatis is used to indicate that an interface is a Mapper **INTERFACE**. This interface is responsible for defining methods that **perform database operations**, such as queries, inserts, updates, and deletes. By marking an interface with `@Mapper`, MyBatis automatically generates the implementation at runtime, allowing you to directly use these interfaces in your service layer without needing to provide an implementation.

1. **Define a User Class**

First, define a POJO (Plain Old Java Object) to represent the data structure:

```java
public class User {
    private Integer id;
    private String username;
    private String password;
}
```

2. **Create a Mapper Interface**

Next, create an interface for database operations and annotate it with `@Mapper`:

```java

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUserName(String username);

    @Insert("INSERT INTO user(username, password) VALUES (#{username}, #{password})")
    void add(User user);
}
```

3. **Configure MyBatis to Recognize the Mapper**

Ensure MyBatis is configured to recognize the mapper, typically by specifying the mapper in the MyBatis configuration file or scanning the package containing the mapper interfaces.

4. **Use the Mapper in Your Application**

Finally, you can autowire the mapper interface in your service class and use it to interact with the database:

```java
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username) {
        return userMapper.findByUserName(username);
    }

    public void createUser(User user) {
        userMapper.add(user);
    }
}
```

### **@Select**

 Used to mark a method for executing a SELECT query. The annotation value is the SQL query to be executed.

### **@Insert**

 Marks a method for executing an INSERT query. The annotation value is the SQL query for inserting records.

### **@Update**

 Used to mark a method for executing an UPDATE query. The annotation value is the SQL statement for updating records.

### **@Delete**

 Marks a method for executing a DELETE query. The annotation value is the SQL statement for deleting records.

## Constructor Generation

### **@NoArgsConstructor**

### **@AllArgsConstructor**



