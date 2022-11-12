# Please, complete the following task.

      1.Transform project from Spring Introduction module into a web application, configure dispatcher servlet.
        (0.5 point)

 - add maven dependency:
 - servlet-context (group: 'javax.servlet', name: 'javax.servlet-api')
 - spring-webmwc(group:'org.springframework', name: 'spring-webmvc')It enables the MVC support for Servlet environments.
 - To enable Spring MVC support through a Java configuration class, we just add the @EnableWebMvc,
   @Configuration annotations public class WebConfig implements WebMvcConfigurer. 
 - To bootstrap an application that loads this configuration, we also need an initializer class:
   declare Dispatcher Servlet in MyClass implements WebApplicationInitializer(it could be in web.xml)
source: 
 - https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc
 - https://www.baeldung.com/spring-mvc-tutorial

        2.Implement annotation-based controllers that will delegate to BookingFacade methods.
        For methods, that accept Entity, just send the list of parameters from the client and assemble the entity
        in the controller, no need for automatic conversion of request payload to java object. (0.5 point)

        3.For methods, that should return a single entity or entity list result (e.g. getUsersByName), implement simple thymeleaf templates for displaying results. No sophisticated markup required, just the fact that you know how to implement the chain:

        ModelAndView à Resolver à ThymeleafTemplate à Html page in the browser. (1 point)
 - 
 source- https://www.codejava.net/frameworks/spring-boot/spring-boot-thymeleaf-form-handling-tutorial
 https://www.digitalocean.com/community/tutorials/spring-mvc-exception-handling-controlleradvice-exceptionhandler-handlerexceptionresolver

      4. For the following facade method:

List getBookedTickets(User user, int pageSize, int pageNum);

Implement alternative controller, which will be mapped on header value "accept=application/pdf" and return PDF version of booked tickets list. (0.5 point)

      5. Implement batch creation of ticket bookings from XML file. 

         Add a method public void preloadTickets() to facade that will load this file from some predefined place (or from a location specified in parameter), unmarshal ticket objects using Spring OXM capabilities and update the storage. The whole batch should be performed in a single transaction, using programmatic transaction management. (1 point)

     6. Implement custom HandlerExceptionResolver, which in case of controller exception just send a simple text response to the client with a brief description of the error. (0.5 point) 
HandlerExceptionResolver will resolve any exception thrown by the application.
It will also allow us to implement a uniform exception handling mechanism in our REST API.
source:
https://www.baeldung.com/exception-handling-for-rest-with-spring
https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
     7. Unit tests, logging, javadocs. (0.5 point)

     8. Implement integration tests for Booking service controllers using the MockMVC framework. (0.5 point)
Integration testing plays an important role in the application development cycle by verifying the end-to-end behavior of a system.
@ExtendWith is specifying the extension class to load. To run the Spring test, we use SpringExtension.class.
@ContextConfiguration is used to load the context configuration
@WebAppConfiguration, which will load the web application context.

source:https://www.baeldung.com/integration-testing-in-spring

test html using-thymeleaf-with-spring-mvc
https://blog.zenika.com/2013/01/21/using-thymeleaf-with-spring-mvc/
https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#spring-mvc-test-server-htmlunit
https://rieckpil.de/test-thymeleaf-controller-endpoints-with-spring-boot-and-mockmvc/