
package com.ws.spring.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
@Profile({ "local", "dev", "stage", "prod" })
public class SwaggerConfig {
	
	@Bean
	public Docket eDesignApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiEndPointsInfo()).enable(Boolean.TRUE).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(Boolean.FALSE).enableUrlTemplating(Boolean.FALSE);
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().deepLinking(Boolean.TRUE).displayOperationId(Boolean.FALSE)
				.defaultModelsExpandDepth(1).defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(Boolean.FALSE).docExpansion(DocExpansion.NONE).filter(Boolean.FALSE)
				.maxDisplayedTags(0).operationsSorter(OperationsSorter.ALPHA).showExtensions(Boolean.FALSE)
				.tagsSorter(TagsSorter.ALPHA).supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null).build();
	}

	private ApiInfo apiEndPointsInfo() {
<<<<<<< HEAD
		return new ApiInfoBuilder().title("KWINLABS ADMIN REST API")
				.description("KWINLABS ADMIN Management REST API").termsOfServiceUrl("https://domainname.in")
=======
		return new ApiInfoBuilder().title("AMS ADMIN REST API")
				.description("AMS ADMIN Management REST API").termsOfServiceUrl("https://domainname.in")
>>>>>>> daccd45 (Initial commit)
				.contact(new Contact("Ramesh Patil", "www.domainname.in", "supportmailid")).license("walkinsoftware.com")
				.licenseUrl("www.walkinsoftware.com").version("1.0.0").build();
	}
	
}
