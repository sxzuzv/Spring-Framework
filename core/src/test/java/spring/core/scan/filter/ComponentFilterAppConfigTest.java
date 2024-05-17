package spring.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        // 스프링 컨테이너(싱글톤 컨테이너) 선언
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        // BeanB beanB = ac.getBean("beanB", BeanB.class); 스프링 빈 자체가 없는 상태이므로 예외 발생!

        // 검증: 필터 지정에 따른 스프링 빈 생성 여부를 체크한다.
        assertThat(beanA).isNotNull();
        // () -> : 우측에 지정한 로직을 실행했을 시, 지정한 예외가 발생하면 테스트에 성공한다.
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration  // 설정 정보 클래스이므로 싱글톤을 보장해줄 수 있도록 @Configuration 애너테이션을 부착
    @ComponentScan(
            // @IncludeComponent 애너테이션이 부착된 클래스를 컴포넌트 스캔 대상에 추가로 지정한다.
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = IncludeComponent.class),
            // @ExcludeComponent 애너테이션이 부착된 클래스를 컴포넌트 스캔 대상에서 제외한다.
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = ExcludeComponent.class)
    )
    static class ComponentFilterAppConfig { // 본 테스트 코드에서 사용할 임의의 설정(구성) 클래스

    }
}