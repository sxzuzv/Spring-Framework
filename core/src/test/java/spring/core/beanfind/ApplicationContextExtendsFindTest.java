package spring.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;

import static org.junit.Assert.assertThrows;

class ApplicationContextExtendsFindTest {
    // 설정(구성) 클래스를 통해 스프링 컨테이너를 생성한다.
    AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 둘 이상의 자식이 존재하면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        // 부모 타입으로 조회 시, 자식 타입 모두가 함께 조회된다.
        // => 이름 없이 타입으로만 조회 시, 두 개의 자식 타입이 조회되므로 중복 오류가 발생한다.
        // DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

        // () -> : 우측에 지정한 로직을 실행했을 시, 지정한 예외가 발생하면 테스트에 성공한다.
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Configuration
    static class TestConfig {   // 본 테스트 코드에서 사용할 임의의 설정(구성) 클래스
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}