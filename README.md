# Employees and Orders REST service with Spring

## Description

### Employees service
I created a simple payroll service that manages the employees of a company. I saved employee objects in a (H2 in-memory) database, 
and accessed them (via JPA). Then wraped that with something that will allow access over the internet (Spring MVC layer). 
In order to write hypermedia-driven outputs I use Spring HATEOAS.

I have RESTful representation of a collection of employee resources.

In the end I created a Spring MVC REST controller that actually produces hypermedia-powered content! Clients that don’t speak HAL can ignore the extra 
bits while consuming the pure data. Clients that DO speak HAL can navigate our empowered API.

![image](https://user-images.githubusercontent.com/47283342/187711092-970c0e3e-e6c0-45fd-b565-11c422bd3bb4.png)
![image](https://user-images.githubusercontent.com/47283342/187711557-6aec83a8-70d7-41c6-bdbc-f1a138e8e4ee.png)


### Orders service
In the Orders part all the controller methods return one of Spring HATEOAS’s RepresentationModel subclasses to properly render hypermedia (or a wrapper around such a type).
This HAL document immediately shows different links for each order, based upon its present state.
The first order, being COMPLETED only has the navigational links. The state transition links are not shown.
The second order, being IN_PROGRESS additionally has the cancel link as well as the complete link.
![image](https://user-images.githubusercontent.com/47283342/187710058-6e917e37-5e8a-4b60-9410-256ef08976f0.png)

This response shows an HTTP 200 status code indicating it was successful. The response HAL document shows that order in its new state (CANCELLED).

![image](https://user-images.githubusercontent.com/47283342/187710546-0e0318fe-c988-414e-b2c7-797614e20ccf.png)
And the state-altering links gone.
![image](https://user-images.githubusercontent.com/47283342/187710941-a3943b7b-3947-4c70-99c4-91f3814e75e8.png)


you see an HTTP 405 Method Not Allowed response. DELETE has become an invalid operation. The Problem response object clearly indicates that you aren’t 
allowed to "cancel" an order already in the "CANCELLED" status.
![image](https://user-images.githubusercontent.com/47283342/187710673-b0a0f45d-65c2-40d3-a61c-864437aad17b.png)

### Tools and Languages
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Spring HATEOAS
