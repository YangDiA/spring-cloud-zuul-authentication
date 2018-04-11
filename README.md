# spring-cloud-zuul-authentication
Using Spring Cloud Zuul and Spring Security as the Dependency, the  authentication system implemented by JWT, which supports role based authentication . 

the er pic is simple : 

![er.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/er.jpg)

## how to use

* start eureka , api-company , api-personal , gateway , token-manager

* open eureka , you can find our server is registed , like this : 

![eureka.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/eureka.jpg)

* while the server under runing , we can start using , and first of all , we need get our token for users.
 
    We assume that personal user is logged in with a mobile phone number, and an company user is using an email to log in.

    * login for personal users .
    
        ![personal_login.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/personal_login.jpg)
        
    * login for company users . 
    
        * login as boss
        
        ![boss_login.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/boss_login.jpg)
        
        * login as manager
        
        ![manager_login.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/manager_login.jpg)
        
        * login as employee
        
        ![employee_login.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/employee_login.jpg)

* now , let's see how to use the token for different roles . 

    First of all , we need to set "Authorization" in Headers , which value should be like Bearer + " " + ${token} , for example :  
    
    ![authorization.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/authorization.jpg)
    
    next , we can use the token to do something meaningful . 

    * A personal user wants to say hello :
    
        ![personal_greeting.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/personal_greeting.jpg)
     
    * A personal user wants to view boss's api :
    
        ![unauthorized.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/unauthorized.jpg)
     
    * A employee user wants to say hello :
    
        ![employee_greeting.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/employee_greeting.jpg)
     
    * A employee user wants to view manager's api :
     
        ![unauthorized.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/unauthorized.jpg)
     
    * A manager user wants to say hello :
    
        ![manager_greeting.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/manager_greeting.jpg)
     
    * A manager user wants to view employee's api :
    
        ![employee_greeting.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/employee_greeting.jpg)
     
    * A manager user wants to view boss's api :
    
        ![unauthorized.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/unauthorized.jpg)
     
    * A boss user wants to say hello :
     
        ![boss_greeting.jpg](https://github.com/liumapp/spring-cloud-zuul-authentication/blob/master/pic/boss_greeting.jpg)
     
    