**Employee Management System with JWT Authentication and Spring Security**

This project is a sample implementation of an Employee Management System using Spring Boot, JWT authentication, and Spring Security. It provides basic CRUD (Create, Read, Update, Delete) operations for managing employee records securely, with roles-based authentication for both users and admins.

**Features**
JWT Authentication: Secure authentication using JSON Web Tokens.
Spring Security: Role-based access control for endpoints.
Employee CRUD Operations: Endpoints to manage employee records.
RESTful API: Designed to follow REST principles for communication.

**Technologies Used**
Spring Boot: Framework for creating Spring applications with minimal setup.
Spring Security: Authentication and access-control framework.
Spring Data JPA: Simplifies data access for relational databases.
H2 Database: In-memory database for development and testing.
JSON Web Tokens (JWT): Secure method for token-based authentication.
Maven: Dependency management and build automation tool.
Thymeleaf: Server-side Java template engine for rendering HTML.

**Requirements**
Java 11 or higher
Maven 3.6.x or higher
IDE (Eclipse, IntelliJ IDEA, or any preferred IDE)

**Setup Instructions**
Clone the repository:

bash
Copy code
git clone https://github.com/aniket-31/SpringBoot.git
cd employee-management
Build and run the application:

bash
Copy code
mvn clean install
mvn spring-boot:run

import as Maven project in IDE
clean install
run as a java application

**Accessing the application:**

The application will start at http://localhost:8009.
Use an API client (e.g., Postman) or a web browser to interact with the endpoints.

**Configuration:**
Ensure proper setup of roles and permissions in SecurityConfig.java for endpoint access control.

**Endpoints**
View Home Page
GET /
Description: Displays the list of all employees.
Roles: User role required.
View: Renders index.html with paginated employee list.

Show New Employee Form
GET /showNewEmployeeForm
Description: Displays the form to add a new employee.
Roles: Admin role required.
View: Renders new_employee.html form.

Save Employee
POST /saveEmployee
Description: Saves a new employee.
Roles: Admin role required.
Redirect: Redirects to / after saving.

Show Form for Update
GET /showFormForUpdate/{id}
Description: Displays the form to update an employee by ID.
Roles: Admin role required.
View: Renders update_employee.html form.

Delete Employee
GET /deleteEmployee/{id}
Description: Deletes an employee by ID.
Roles: Admin role required.
Redirect: Redirects to / after deletion.

Pagination
GET /page/{pageNo}
Description: Displays paginated list of employees.
Roles: Public access.
View: Renders index.html with paginated employee list.

Authenticate User
POST /signin
Description: Authenticates user credentials and generates JWT token.
Roles: Public access.
Returns: JSON response with JWT token for authenticated users.

**Security**
JWT Authentication: Users authenticate by providing valid JWT tokens.
Authorization: Role-based access control (Admin vs. User) for endpoints.
Password Encryption: User passwords are securely hashed before storing in the database.
Roles and Permissions
User Role: Users have access to basic CRUD operations for employee management.
Admin Role: Admins have access to all CRUD operations 

**Deployment**
Run as Java application
Navigate to http://localhost:8080 in your web browser.
Use endpoints as described above for employee management.

Please find Below few Snippets of the application:
![1](https://github.com/aniket-31/SpringBoot/assets/73285098/4dd66b44-0d37-44bb-b88f-4ff1e6f2ae7f)
![2](https://github.com/aniket-31/SpringBoot/assets/73285098/c0164cc8-c113-4111-bae0-84907b4e0107)
![3](https://github.com/aniket-31/SpringBoot/assets/73285098/c714179a-188e-46f0-9e59-f1a76eddb2dd)
![4](https://github.com/aniket-31/SpringBoot/assets/73285098/59e13bdc-cc43-472c-a9d6-caf6e4b736b8)
![5](https://github.com/aniket-31/SpringBoot/assets/73285098/abd6a2ab-dc16-4dde-b21c-3ab42dbcfa00)
![6](https://github.com/aniket-31/SpringBoot/assets/73285098/ea740192-66b4-41cf-9cbe-964ae724cc64)
![7](https://github.com/aniket-31/SpringBoot/assets/73285098/380c47e0-7473-432f-a8e2-47ce81edc8f6)








