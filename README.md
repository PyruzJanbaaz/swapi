# SWAPI-WARPPER
Simple web application which is created by Vue.js to show the Star Wars characters! All the data is accessible through our HTTP web API. Consult https://swapi.dev/ if you'd like to get started. Helper libraries for popular programming languages are also provided so you can consume swapi in your favourite programming language, in a style that suits you.

I create a Spring Boot project(Rest APIs) as a warpper to get data from online API. You need the following tools and technologies to develop the same.
- Spring-Boot 2.7.1
- Springfox-swagger2 2.9.2
- Lombok 1.18.12
- JavaSE 11
- Maven 3.3.9

# Dependencies
Open the pom.xml file for spring-aop configuration:

      <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.1</version>
        <relativePath/> <!-- lookup parent from repository -->
      </parent>
      
and dpendencies:

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>


# Usage
Run the project and go to http://localhost:7777/swagger-ui.html on your browser!
