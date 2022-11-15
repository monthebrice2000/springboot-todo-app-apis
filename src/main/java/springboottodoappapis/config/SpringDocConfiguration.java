package springboottodoappapis.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@EnableSwagger2
public class SpringDocConfiguration {

    /*@Bean
    public OpenAPI jhLightOpenAPI() {
        return new OpenAPI()
            .info(
                new Info()
                    .title("JHipster Light API")
                    .description("JHipster Light API")
                    .version("v0.0.1")
                    .license(new License().name("Apache 2.0").url("https://jhipster.tech"))
            )
            .externalDocs(new ExternalDocumentation().description("JHipster Light Documentation").url("https://jhipster.tech/light"));
    }

    @Bean
    public GroupedOpenApi jhLightAllOpenAPI() {
        // prettier-ignore
        return GroupedOpenApi.builder()
            .group("all")
            .pathsToMatch("/api/**")
            .build();
    }*/

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths( PathSelectors.any())
            .build();
    }
    /*@Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
            .addSecurityItem(new SecurityRequirement()
                .addList(securitySchemeName))
            .components(new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                    .name(securitySchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }*/

}
