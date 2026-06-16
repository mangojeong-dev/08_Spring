package org.scoula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 🌐 Spring MVC Web Context 설정 클래스
 * - Spring MVC의 웹 계층(Presentation Layer)을 담당하는 컨텍스트 설정 클래스
 * - 사용자 요청 처리와 관련된 모든 웹 컴포넌트들을 관리하고 설정함
 *
 * 📋 주요 어노테이션 설명:
 * @EnableWebMvc
 * - Spring MVC 기능을 활성화하는 핵심 어노테이션
 * - DispatcherServlet, HandlerMapping, HandlerAdapter 등 MVC 인프라 자동 설정
 * - JSON/XML 변환, 데이터 바인딩, 유효성 검증 등 웹 기능 활성화
 * - <mvc:annotation-driven />의 자바 설정 버전
 *
 * @ComponentScan
 * - 지정된 패키지에서 Spring 컴포넌트를 자동으로 스캔하여 빈으로 등록
 * - 현재 설정: "org.scoula.controller" 패키지의 @Controller 클래스들을 스캔
 *
 * WebMvcConfigurer 인터페이스 구현
 * - Spring MVC의 세부 설정을 커스터마이징할 수 있는 인터페이스
 * - 필요한 메서드만 오버라이드하여 선택적 설정 가능
 */
@EnableWebMvc
@ComponentScan(basePackages = {"org.scoula.controller"}) // Spring MVC용 컴포넌트 등록을 위한 스캔 패키지
public class ServletConfig implements WebMvcConfigurer {

    /**
     * 📁 정적 자원 핸들러 설정
     * - 웹 애플리케이션의 정적 자원(CSS, JavaScript, 이미지, 폰트 등)에 대한 URL 매핑과 물리적 경로를 설정
     *
     * 🔧 설정 내용:
     * - URL 패턴: /resources/** (예: /resources/css/style.css)
     * - 물리적 위치: /resources/ (webapp/resources/ 디렉토리)
     *
     * 📁 추천 디렉토리 구조:
     * webapp/resources/
     * ├── css/          (스타일시트)
     * ├── js/           (자바스크립트)
     * ├── images/       (이미지 파일)
     * ├── fonts/        (웹 폰트)
     * └── libs/         (외부 라이브러리)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**") // URL이 /resources/로 시작하는 모든 경로
                .addResourceLocations("/resources/"); // webapp/resources/ 경로로 매핑
    }

    /**
     * 🖼️ View Resolver 설정 (JSP 뷰 리졸버)
     * - Controller에서 반환하는 논리적 뷰 이름을 실제 JSP 파일 경로로 변환하는 ViewResolver 설정
     *
     * 🔄 뷰 해결 과정:
     * 1. Controller 메서드가 "home"이라는 뷰 이름 반환
     * 2. ViewResolver가 prefix + viewName + suffix 조합
     * 3. 최종 경로: "/WEB-INF/views/" + "home" + ".jsp"
     * 4. 결과: /WEB-INF/views/home.jsp 파일을 렌더링
     *
     * 🎨 InternalResourceViewResolver 설정:
     *
     * - ViewClass: JstlView.class
     *   -> JSTL(JSP Standard Tag Library) 지원 활성화
     *
     * - Prefix: "/WEB-INF/views/"
     *   -> 모든 JSP 파일의 기본 경로
     *   -> /WEB-INF/ 디렉토리는 외부에서 직접 접근 불가 (보안)
     *
     * - Suffix: ".jsp"
     *   -> 뷰 파일의 확장자
     *   -> Controller에서 확장자 없이 파일명만 반환하면 됨
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);           // JSTL 지원 활성화
        bean.setPrefix("/WEB-INF/views/");           // JSP 파일 기본 경로
        bean.setSuffix(".jsp");                      // JSP 파일 확장자
        registry.viewResolver(bean);                 // ViewResolver 등록
    }
}
