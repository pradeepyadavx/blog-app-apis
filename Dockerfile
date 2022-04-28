FROM openjdk:8 
COPY ./target/blog-app-apis-0.1.jar blog-app-apis-0.1.jar
CMD ["java","-jar","blog-app-apis-0.1.jar"]