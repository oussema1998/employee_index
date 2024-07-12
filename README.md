This project involves the implementation of CRUD operations across multiple platforms, including mobile, web, and desktop applications, with input validation.

1. Database
Technology: MySQL
Location: Database Directory
Import Instructions: Use phpMyAdmin to import the database into the employee database.

2. Back End (REST API)
Technology: SpringBoot
Location: back_employee/employee
Configuration: Ensure to set up the port, username, and password to connect to your MySQL database in application.properties.

3. Front End (Web Application)
Technology: Angular
Location: front_employee/employee
Functionality: Consumes the REST API.

4. Desktop Application
Technology: JavaFX (with Maven)
Location: desktop_employee
Functionality: Consumes the REST API.

5. Mobile Application
Technology: Android Studio
Location: android_studio_employee
Configuration: Update the MY_IP_ADDRESS variable in the Constants file located in the Utils package with your own IP address.

 Additional Information
Screenshot: The file result_final.png showcases all the applications working simultaneously, displaying the list of employees.
Note: This project demonstrates full CRUD operations with input validation across mobile, web, and desktop platforms. This challenge is a personal effort to integrate previous individual projects in Angular, SpringBoot, Android Studio, and JavaFX into a single cohesive project.
