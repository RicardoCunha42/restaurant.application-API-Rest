# restaurant.application-API-Rest ("Read me" in construction)
It's an API Rest that can make CRUD operations through HTTP resquisitions over the entities involved. It was made as a challenge at the end of the CESAR NExT's Spring Boot module. The next goal is to add a view so that the user can interact.

#How to use it:

One can use it by simply downloading the files and running it in any IDE or in the command line. It'll be necessary to use Postman or a similar application to make the HTTP requisitions that aren't GET. The databank configurations'll also have to be made on the application.properties file.

#The entities and relatioships:

The application has three enitities: category, plate and order. Each category has many plates, and each plate has one category represented on it's table by the category id. Plates and orders hava a many to many relationship, in which the orders have arrays of the plates and vice versa, represented by joined table with colomuns of their id's.

#The mapping:

Post
- https:{host address}/category/create

Receives an atribute "name" in json format and creates a food category.

Put
- https:{host address}/category/update/{id}

Receives an atribute "name" in json format and creates a food category.
