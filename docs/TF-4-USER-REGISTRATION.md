# TF-4 - User Registration

## 1. User Entity

Fields:
- id: Represents Database generated user id
- firstName: First name of user
- lastName: Last name of user
- email: email of user
- password: Bcrypt encoded password of user
- createdAt: Represents the created timestamp
- updatedAt: Represents the last modified timestamp
- address: Address details of the user

## 2. Database Design

Table name: taskflow_user

### Columns

| Column      | Type         | Constraints |
|-------------|--------------|-------------|
| id          | int          | primary key |
| first_name  | varchar(50)  | not null    |
| last_name   | varchar(50)  | not null    |
| email       | varchar(100) | not null    |
| password    | varchar(60)  | not null    |
| created_ts  | timestamp    | not null    |
| updated_ts  | timestamp    | not null    |
| street      | varchar(40)  | not null    |
| lane_number | int          | not null    |
| city        | varchar(40)  | not null    |
| state       | varchar(40)  | not null    |
| country     | varchar(40)  | not null    |

### Constraints

- email should be unique
- address can be embedded in user

## 3. API Design

### Endpoint

POST /api/v1/users

### Request

```json
{
  "firstName" : "Thanmai",
  "lastName" : "Bheemshetty",
  "password" : "BTS@143",
  "email" : "thanmai.bheemshetty@tlabs.com",
  "address" : {
    "street" : "Subash Nagar",
    "laneNumber" : 247,
    "city" : "Mahabubnagar",
    "state" : "Telangana",
    "country" : "India"
  }
}
```

### Response

```json
{
  "id" : 1,
  "firstName" : "Thanmai",
  "lastName" : "Bheemshetty",
  "email" : "thanmai.bheemshetty@tlabs.com"
}
```

### HTTP Status Codes

- 201 - User Successfully Registered 
- 400 - Invalid request data
- 500 - Internal Server Error
- 409 - User already exists

### Validation

- First Name
  - Required
  - Cannot be blank
  - Maximum 50 characters

- Last Name
    - Required
    - Cannot be blank
    - Maximum 50 characters

- Email
    - Required
    - Cannot be blank
    - Maximum 100 characters
    - Must be unique

- Password
    - Required
    - Cannot be blank
    - Minimum 8 characters