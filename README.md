# Authentication System

#### I started with: 
1. Created a MYSQL database: one table for user, one table for confirmation tokens. Users has a one to many relationship with tokens.
2. Created email service that is able to send emails using thymeleaf templates.
3. Created a global exception handler that sends formatted error message to the frontend.


#### Then:
I build the authentication system with Spring Security. <br/>
Here is a <strong>flow chart</strong> of how it works: 
![image](https://github.com/tinaafang/authentication-system/assets/94018300/cfdf3335-b4fe-4367-b8ad-a86b0f3d972a)
