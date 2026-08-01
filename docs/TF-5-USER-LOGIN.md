## 1. API Design

### Endpoint

POST /api/v1/auth/login

### Request

```json
{
  "email" : "thanmai.bheemshetty@tlabs.com",
  "password" : "BTS@143"
}
```

### Response

```json
{
  "user" : {
    "id" : 1,
    "firstName" : "Thanmai",
    "lastName" : "Bheemshetty",
    "email" : "thanmai.bheemshetty@tlabs.com"
  },
  "accessToken" : "ndweihruiy23uhNNUEWIHRNJHEIWURGFBUIRWF"
}
```

### HTTP Status Codes

- 200 - User Logged in Successfully
- 400 - Invalid request data
- 500 - Internal Server Error
- 401 - Invalid email/password

### Validation

- Username
    - Required
    - Cannot be blank
    - Maximum 100 characters
    - Valid email format

- Password
    - Required
    - Cannot be blank