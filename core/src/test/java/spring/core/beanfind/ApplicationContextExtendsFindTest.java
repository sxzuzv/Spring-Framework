package spring.core.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
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

    @Test
    @DisplayName("부모 타입으로 조회 시, 둘 이상의 자식이 존재하면 별도의 빈 이름을 지정한다.")
    void findBeanByParentTypeBeanName() {
        // 부모 타입으로 조회 시, 자식 타입 모두가 함께 조회된다.
        // => 이름 없이 타입으로만 조회 시, 두 개의 자식 타입이 조회되므로 중복 오류가 발생한다.
        // 이를 해결하기 위해 조회 시, 타입과 함께 별도의 빈 이름을 지정한다.
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        // rateDiscountPolicy(DiscountPolicy = interface)의 구현 객체를 판단한다.
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        // 이름 지정 없이, 특정 하위(자식) 타입으로 빈을 찾는다.
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);

        // bean(변수명)이 RateDiscountPolicy의 객체인지 판단한다.
        Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 하위(자식) 타입까지 모두 조회")
    void findAllBeanByParentType() {
        // DiscountPolicy(부모 타입) 하위(자식) 타입의 빈을 모두 가져온다.
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " / value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);

        assertThat(beansOfType.size()).isEqualTo(2);
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