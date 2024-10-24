package class4.demoday.global.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("데모데이 4반 아이들 API 명세서")
                        .version("1.0.0")
                        .description("데모데이 4반 아이들 API 명세서입니다."));
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("Auth")
                .pathsToMatch("/api/v1/auth/**")
                .packagesToScan("class4.demoday.domain.auth.controller")
                .build();
    }

    @Bean
    public GroupedOpenApi markerApi() {
        return GroupedOpenApi.builder()
                .group("Marker")
                .pathsToMatch("/api/v1/marker/**")
                .packagesToScan("class4.demoday.domain.marker.controller")
                .build();
    }

    @Bean
    public GroupedOpenApi individualMarkerApi() {
        return GroupedOpenApi.builder()
                .group("IndividualMarker")
                .pathsToMatch("/api/v1/individual-marker/**")
                .packagesToScan("class4.demoday.domain.individualmarker.controller")
                .build();
    }

    @Bean
    public GroupedOpenApi recodeApi() {
        return GroupedOpenApi.builder()
                .group("Recode")
                .pathsToMatch("/api/v1/recode/**")
                .packagesToScan("class4.demoday.domain.recode.controller")
                .build();
    }
}