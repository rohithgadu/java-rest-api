# Java REST API example

Task 1. Implement an application in java which provides a REST API with endpoints for searching,
creating and deleting “server” objects:
- GET servers. Should return all the servers if no parameters are passed. When server  id is passed as a parameter - return a single server or 404 if there’s no such a server.
- PUT a server. The server object is passed as a json-encoded message body. Here’s an example:
    ```json
    {
        "name": "my centos",
        "id": "123",
        "language":"java",
        "framework":"django"
    }
    ```
- DELETE a server. The parameter is a server ID.
- GET (find) servers by name. The parameter is a string. Must check if a server name contains this string and return one or more servers found. Return 404 if nothing is found.

“Server” objects should be stored in MongoDB database.



## Tech Stack

- `Backend Framework:` `Springboot`
- `Database:` `MongoDB`



## Schema
|    servers     |  |  
| -------- | ------- |
|   `id`      | `Long` |
|   `name`      | `String` |
|   `language`      | `String` |
|   `framework`      | `String` |




## API Reference 

  - Check the POSTMAN Documentation for both Success and Failure Examples
  - https://documenter.getpostman.com/view/19762721/2s93RMUuqJ

### API Endpoints


#### Add Server Object
```javascript
  PUT localhost:8090/addServer
```
#### Request Body
```json 
{
    "id":6,
    "name":"game",
    "language":"go",
    "framework":"python"
}
```
#### Example Response
```javascript
  201 CREATED

```

```json 
{
  "success": true,
  "statusCode": 201,
  "data": {
    "id": 6,
    "name": "game",
    "language": "go",
    "framework": "python"
  }
}
```

#### Delete Server Object
```javascript
  DELETE localhost:8090/deleteServer/0
```

#### Example Response
```javascript
  200 OK
```
```json 
{
    "success": true,
    "statusCode": 200,
    "data": "Record Successfully Deleted"
}
```

#### Get All Server Objects

```javascript
  GET localhost:8090/getServer
```
#### Example Response

```javascript
  200 OK
```

```json 
{
    "success": true,
    "statusCode": 200,
    "data": [
        {
            "id": 123,
            "name": "mentos",
            "language": "java",
            "framework": "python"
        },
        {
            "id": 1,
            "name": "mentoss",
            "language": "c",
            "framework": "python"
        },
        {
            "id": 3,
            "name": "gaming-hub",
            "language": "c",
            "framework": "django"
        },
        {
            "id": 83,
            "name": "mentos",
            "language": "c++",
            "framework": "python"
        },
        {
            "id": 10,
            "name": "mentoss",
            "language": "c++",
            "framework": "python"
        },
        {
            "id": 6,
            "name": "game",
            "language": "go",
            "framework": "python"
        }
    ]
}
```

#### Get Server Object By Id
```javascript
  GET localhost:8090/getServer/byId/123
```

#### Example Response
```javascript
  200 OK
```
```json
{
    "success": true,
    "statusCode": 200,
    "data": {
        "id": 123,
        "name": "mentos",
        "language": "java",
        "framework": "python"
    }
}
```


#### Get Server Object By Name

```javascript
  GET localhost:8090/getServer/byName/mentos
```
#### Example Response

```javascript
  200 OK
```

```json 
{
    "success": true,
    "statusCode": 200,
    "data": [
        {
            "id": 123,
            "name": "mentos",
            "language": "java",
            "framework": "python"
        },
        {
            "id": 83,
            "name": "mentos",
            "language": "c++",
            "framework": "python"
        }
    ]
}
```
