package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepositoryImpl;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

/**
 * 애플리케이션 전반을 설정하고 구성하는 클래스를 만든다.
 * => 역할과 구현이 명확히 드러나도록 작성한다.
 */

/**
 * @Configuration: 스프링 컨테이너는 해당 애너테이션이 붙은 클래스를 설정(구성) 정보로 사용한다.
 * @Bean: 이때, @Bean 애너테이션이 붙은 메서드를 모두 호출, 반환된 객체를 스프링 컨테이너에 등록한다.
 * => 위의 과정을 거쳐 스프링 컨테이너에 등록된 객체를 '스프링 빈'이라고 명칭한다.
 * 스프링 빈은 @Bean 애너테이션이 붙은 메서드의 이름을 스프링 빈의 이름으로 사용한다.
 */

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        // 1) MemoryMemberRepositoryImpl 객체를 생성한다.
        // 2) 객체 생성으로 반환되는 참조 값을 MemberServiceImpl 객체를 생성하면서 생성자로 전달한다.

        // return new MemberServiceImpl(new MemoryMemberRepositoryImpl());
        return new MemberServiceImpl(memberRepositoryImpl());

    }

    @Bean
    public MemoryMemberRepositoryImpl memberRepositoryImpl() {
        return new MemoryMemberRepositoryImpl();
    }

    @Bean
    public OrderService orderService() {
//        return new OrderServiceImpl(memberRepositoryImpl(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // 할인 정책 변경: FixDiscountPolicy -> RateDiscountPolicy
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}