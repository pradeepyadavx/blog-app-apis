# blog-app-apis
 Rest Services To Create Blogs
 
 
 Steps To Build and run the project in Docker
 
 Step 1 : Deploy mysql image to docker 
         
         docker run -e MYSQL_ROOT_PASSWORD=root --name mysldb mysql
         
         here -e == enviorment variable 
              --name to give name 
            
         
         Above command will run mysql in docker if its not availabe it will pull but in case if mac 
         use this command 
         docker pull --platform linux/x86_64 mysql:8
         then run above command 
         
 Step2 :
       Now open application.properties file and edit datasourese url 
      from this  
      
      spring.datasource.url=jdbc:mysql://localhost:3306/blog_app_apis
                   to 
                  spring.datasource.url=jdbc:mysql://mysqldb:3306/blog_app_apis
                  spring.datasource.username=root
                  spring.datasource.password=root
                  
                  
       now need to create database ion mysql image
       
             docker exec -it mysqldb
             
             mysql -u root -p root
             
             create database blog_app_apis;

                  
        here mysqldb is same name as image name of mysql container in step 1
        change username and password accorfingly 
        
 Step 3: Build project 
           mvn install
       
 Step 4: Build docker image using jar created in step 3
 
        docker build -t blogs_api .
        
        blogs_api is name which image is created 
        . is used to take all from that folder using navigate to docker file
        
 Step 5 :Now run the build image using links to mysql
       
          docker run -p 9090:9090 --link mysqldb:mysql --name   blog_api
          
  
 boom server is started 
 to check logs
 
 docker logs blogsapi
 
 
 if everthing is good 
 open url in browser
            http://localhost:blogs
            
       
  Download post request from here 
  
  
             https://www.getpostman.com/collections/80f367b35d1f91447bee
 
                  
    



