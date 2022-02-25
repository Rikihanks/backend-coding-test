# Spring Boot to-do app challenge


## Principal

### With this project, it is also included a JSON export of a postman collection with examples to test all the methods.


A **DTO** was created for the TaskEntity since I believe it's a good practice to keep the entities objects to use with  
the persistence layer and decouple them from the other layers.  
A library to perform the conversions between **DTO** and **Entity** was used in order to save time writing conversion  
methods.

For the improved request that returns a list of tasks, the **Criteria** and **Specification** patterns where used  
to filter the results. On the other hand, comparators where used to order the results after they were  
retrieved from the database.

For the SubtaskEntity, my first approach was to use **hierarchy** where SubtaskEntity was a child of TaskEntity  
but due to time restrictions I couldn't make it work, so I ended up using a bidirectional **OneToMany** annotation.