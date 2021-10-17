# Cakes Test

This project is a quick demonstration of my ability to modernise legacy web applications with a modern tech stack. 



## Running with Docker
The easiest way to start the application 

``` docker build . -t cbenskey/cake-test```

and then 

```docker run -p 8080:8080 cbenskey/cake-test:latest```

The application should service requests running on port 8080. 

Will render the cakes UI, which includes a list of cakes and a form to add new:

```http://localhost:8080``` 

Returns of list of Cakes in JSON format:

```curl --location --request GET 'http://localhost:8080/cakes'``` 


Saves a cake to the backend:
```curl --location --request POST 'http://localhost:8080/cakes' 
--header 'Content-Type: text/plain'  --data-raw '{
    "title" : "cake title",
    "desc" : "description of the cake", 
    "image": "some url" 
}'
```

## Running with Maven 
The backend can be run entirely using Maven. If one wishes to run the frontend with the backend, npm is also required.

```cd <project-root>/frontend```
```npm run build```

The backend should pickup the frontend in code, so executing the following should bring up the stack:

```cd <project-root>/backend```
```mvn spring-boot:run```

## Changes made from original 
The original code certainly uses a legacy appraoch to solving for the requirements. To modernise, I've made the 
following changes:

1. Replaced servlet stack with spring boot
2. Replaced hibernate utility with JPA/JPA Repositories
3. Database and seed data bootstrapped with flyway
4. Added h2 database as backing store
5. GET and POST requests fulfilled with Spring RestController
6. Front end is now React
7. Frontend invokes GET and POST calls via exposed RESTful endpoints

## Glaring omissions and caveats 

Time was a constraint during implementation so please be aware of the following:

1. There is no validation on the backend or frontend
2. The SpringMVC unit tests run an entire spring stack - should be broken down by application layer as future improvement
3. There are no jest tests on the frontend 
4. The frontend UI styling could be massively improved
5. The docker build could be optimised. Docker compose could be used and nginx or other web server could serve frontend. Also, a docker ignore could make things more efficient. 
6. The frontend could have configuration pulled into seperate file(s)
