package com.app.global.config;

import com.app.global.resolver.memberinfo.MemberInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){ // Swagger 설정에 필요한 빈
        return new Docket(DocumentationType.OAS_30)
                .select() // ApiSelectorBuilder 생성
                .apis(RequestHandlerSelectors.basePackage("com.app.api")) // API 패키지 경로 todo : 패키지 경로 수정
                .paths(PathSelectors.ant("/api/**")) // /api로 시작하는 모든 경로에 대해 문서화  todo : api 경로 수정 시 수정 필요
                .build()
                .apiInfo(apiInfo()) // API ㅜㅁㄴ서에 대한 정보 추가
                .useDefaultResponseMessages(false) // swagger에서 기본으로 제공하는 응답 코드를 표시 X
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .ignoredParameterTypes(MemberInfo.class)
                ;
    }

    // API 정보를 나타내는 메서드
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API 문서")
                .description("API에 대해 설명하는 문서")
                .version("1.0")
                .build();
    }

    // Swagger문서에서 Authorization header를 입력할 수 있게 하는 메서드
    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

    private ApiKey apiKey(){
        return new ApiKey("Authorization", "Authorization", "header");
    }
}
