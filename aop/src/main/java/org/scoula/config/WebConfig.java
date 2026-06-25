package org.scoula.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * 웹 애플리케이션 부트스트래핑 설정 클래스
 * - Spring MVC 웹 애플리케이션의 진입점 역할을 하는 클래스
 * - web.xml(배포 서술자)을 대체하여 서블릿 컨테이너 초기화와 Spring 컨텍스트 설정을 담당
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Root Application Context 설정 클래스 지정
     *
     * 💡 설정 예시
     * return new Class[] {
     *     RootConfig.class,           // 기본 설정
     *     DatabaseConfig.class,       // 데이터베이스 설정
     *     SecurityConfig.class,       // 보안 설정
     *     CacheConfig.class          // 캐시 설정
     * };
     *
     * 생명주기
     * - 웹 애플리케이션 시작 시 가장 먼저 생성
     * - 애플리케이션 종료 시 가장 마지막에 소멸
     * - 다른 모든 컨텍스트의 부모 역할
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }


    /**
     * Servlet Application Context 설정 클래스 지정
     *
     * 설정 예시:
     * return new Class[] {
     *     ServletConfig.class,        // 기본 웹 설정
     *     WebSecurityConfig.class,    // 웹 보안 설정
     *     ApiConfig.class            // REST API 설정
     * };
     *
     * 관계:
     * - Root Context의 Bean에 접근 가능 (부모-자식 관계)
     * - Root Context에서는 이 컨텍스트의 빈들에 접근 불가
     * - 여러 DispatcherServlet 생성 시 각각 독립적인 컨텍스트 보유
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Swagger 설정 클래스 추가
        return new Class[] { ServletConfig.class, SwaggerConfig.class };
    }

    /**
     * DispatcherServlet URL 매핑 패턴 설정
     * - Spring MVC의 Front Controller인 DispatcherServlet이 처리할 URL 패턴 정의
     *
     * 🔧 현재 설정: "/"
     * - 모든 요청을 DispatcherServlet이 처리
     * - 정적 자원 요청도 포함 (CSS, JS, 이미지 등)
     * - ServletConfig에서 addResourceHandlers()로 정적 자원 별도 처리 필요
     *
     * 고급 설정:
     * - 다중 DispatcherServlet 설정 가능
     * - 각각 다른 URL 패턴으로 분리 처리
     * - 예: /api/*, /admin/*, /mobile/* 등
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/",                        // 기본 매핑
                "/swagger-ui.html",         // Swagger UI 메인 페이지
                "/swagger-resources/**",    // Swagger 리소스
                "/v2/api-docs",            // API 명세 JSON
                "/webjars/**"              // WebJar 리소스 (CSS, JS 등)
        };
    }




    /**
     * 서블릿 필터 설정 (HTTP 요청/응답 전처리)
     * - DispatcherServlet으로 전달되기 전에 HTTP 요청/응답을 전처리할 필터들을 등록하고 설정
     *
     * 현재 설정: 문자 인코딩 필터 (CharacterEncodingFilter)
     * - setEncoding("UTF-8")
     *   → 요청 데이터의 문자 인코딩을 UTF-8로 설정
     *   → 한글, 특수문자 등 다국어 지원
     *   → POST 요청의 파라미터 인코딩에 주로 영향
     *
     * - setForceEncoding(true)
     *   → 응답 데이터에도 UTF-8 인코딩 강제 적용
     *   → 요청과 응답 모두 일관된 인코딩 보장
     *   → 클라이언트가 다른 인코딩을 요청해도 UTF-8로 응답
     *
     *
     * 웹 요청 처리 흐름:
     *
     * 클라이언트 요청
     *        ↓
     * 서블릿 컨테이너 (Tomcat)
     *        ↓
     * CharacterEncodingFilter ← 현재 설정
     *        ↓
     * [기타 필터들]
     *        ↓
     * DispatcherServlet
     *        ↓
     * HandlerMapping → Controller → ViewResolver
     *        ↓
     * JSP 렌더링 → 클라이언트 응답
     */
    protected Filter[] getServletFilters() {
        // UTF-8 문자 인코딩 필터 생성 및 설정
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");       // 요청 데이터 UTF-8 디코딩
        characterEncodingFilter.setForceEncoding(true);     // 응답 데이터도 UTF-8 강제 인코딩

        return new Filter[] { characterEncodingFilter };
    }

    // 파일 업로드 설정 상수
    final String LOCATION = "c:/upload";
    final long MAX_FILE_SIZE = 1024 * 1024 * 10L;      // 10MB
    final long MAX_REQUEST_SIZE = 1024 * 1024 * 20L;   // 20MB
    final int FILE_SIZE_THRESHOLD = 1024 * 1024 * 5;   // 5MB

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 404 에러를 Exception으로 변환
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");

        // Multipart 파일 업로드 설정
        MultipartConfigElement multipartConfig = new MultipartConfigElement(
                LOCATION,           // 업로드 처리 디렉토리 경로
                MAX_FILE_SIZE,      // 업로드 가능한 파일 하나의 최대 크기
                MAX_REQUEST_SIZE,   // 업로드 가능한 전체 최대 크기(여러 파일 업로드)
                FILE_SIZE_THRESHOLD // 메모리 파일의 최대 크기(임계값)
        );
        registration.setMultipartConfig(multipartConfig);
    }
}