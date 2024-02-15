# Annotations

## Controller

### **@RestController**

**Description**: This annotation is used at the class level and denotes a special controller that handles HTTP requests. `@RestController` simplifies the development of RESTful web services by combining `@Controller` and `@ResponseBody`, meaning the data returned by each method is written directly to the body of the response in a format like JSON or XML.

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

**Description**: Applied at the class or method level to define routing information. It can specify the URL, HTTP method, and other request parameters. When used at the class level, it sets a base path for all resource URLs in the controller.

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

**Description**: A shortcut for `@RequestMapping(method = RequestMethod.GET)` that is only used at the method level. It indicates that a method should be used to handle a GET request for a specific URL.

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

`@RequestParam` is used to extract query parameters from the query string of the HTTP request. It binds the value(s) of a query parameter to a method parameter in your handler method.

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

`@RequestBody` is used to bind the body of the HTTP request to a method parameter. It's often used with POST or PUT requests to get request body content.

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

`@Validated` is used to enable validation on the annotated element. It can be applied at the class level or method parameter level. When used on method parameters (especially with `@RequestBody`), it triggers validation for the incoming object.

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

the `@URL` annotation checks that the `url` field is a valid HTTPS URL.