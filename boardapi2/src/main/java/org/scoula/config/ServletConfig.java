package org.scoula.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc  // Spring MVC 기능 활성화 - 기본 핸들러 매핑, 뷰 리졸버 등 자동 설정
@ComponentScan(basePackages = {
        "org.scoula.controller",      // 공통 컨트롤러 패키지
        "org.scoula.exception",// 예외 처리 클래스 스캔 대상 추가
        "org.scoula.board.controller"    // BoardController 패키지 추가
})
// 지정된 패키지에서 @Component 어노테이션이 있는 클래스를 자동으로 빈으로 등록

public class ServletConfig implements WebMvcConfigurer {

    // ViewResolver 설정 - 논리적 뷰 이름을 실제 JSP 파일 경로로 변환
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);        // JSTL 지원 활성화
        resolver.setPrefix("/WEB-INF/views/");        // JSP 파일 위치 접두사
        resolver.setSuffix(".jsp");                   // JSP 확장자 접미사
        registry.viewResolver(resolver);

        // 예: "home" → "/WEB-INF/views/home.jsp"로 변환
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 기본 리소스 설정
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

        // Swagger UI 리소스를 위한 핸들러 설정
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        // Swagger WebJar 리소스 설정 (Bootstrap, jQuery 등)
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // Swagger 메타데이터 리소스 설정
        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/");

        // API 문서 JSON 엔드포인트 설정
        registry.addResourceHandler("/v2/api-docs")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    // Servlet 3.0 파일 업로드 설정
    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver resolver =
                new StandardServletMultipartResolver();
        return resolver;
    }
}
