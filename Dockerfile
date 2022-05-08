FROM openjdk:8 
COPY ./target/blog-app-apis-0.4.jar blog-app-apis-0.4.jar
CMD ["java","-jar","blog-app-apis-0.4.jar"]