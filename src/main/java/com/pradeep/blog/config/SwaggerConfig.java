package com.pradeep.blog.config;



import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	private static final String AUTHORIZATION_HEADER="Authorization";	
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	private List<SecurityContext> securityContexts(){
		return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
	}
	
	
	private List<SecurityReference> securityReferences(){
		
		
		AuthorizationScope scope= new AuthorizationScope("global", "acceessEverything");
		
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}
	
	@Bean
	public Docket api() {
		return  new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
				
	}
	
	

	private ApiInfo getInfo() {
		
		
		return new ApiInfo("Blog App Apis", 
				"Blog Api developed By Pradeep Yadav", 
				"1.0", "Term and Conditions",
				new Contact("Pradeep", "pradeep", "er.pradeepy118@gmail.com"), 
				"Apache2.0",
				"https://www.apache.org/licenses/LICENSE-2.0",
				Collections.emptyList());
	}

}
