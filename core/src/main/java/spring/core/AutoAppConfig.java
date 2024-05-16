package spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration  // 설정 정보 클래스이므로 싱글톤 보장을 위한 @Configuration 애너테이션 부착

// @ComponentScan: @Bean 애너테이션 없이도 자동으로 스프링 빈을 등록
// excludeFilters = @ComponentScan.Filter: 스프링 빈 등록에서 제외할 것들을 지정
// => Configuration.class를 스프링 빈 등록에서 제외한다.
// => @Configuration 애너테이션이 붙은 경우, 자동으로 컴포넌트 스캔의 대상이 되므로 충돌을 막기 위해 제외시킨다.
@ComponentScan(
        basePackages = "spring.core",               // 지정한 패키지를 포함하여 하위 패키지 모두를 스캔한다.
        basePackageClasses = AutoAppConfig.class,   // 지정한 클래스가 속한 패키지부터 스캔을 시작한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}