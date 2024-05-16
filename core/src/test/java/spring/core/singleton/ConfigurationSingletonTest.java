package spring.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;
import spring.core.member.MemberRepository;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepositoryImpl;
import spring.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        // 스프링 컨테이너(싱글톤 컨테이너) 선언
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemoryMemberRepositoryImpl memberRepositoryImpl = ac.getBean("memberRepositoryImpl", MemoryMemberRepositoryImpl.class);

        // MemoryMemberRepositoryImpl 객체 인스턴스가 각각 다른 2개로 생성되면서 싱글톤이 깨졌을까?
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();

        // 동일한 참조 값이 출력된다. => 하나의 객체 인스턴스만 생성된 상태다.
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepositoryImpl);

        // 검증: 객체 인스턴스가 동일한지 판단한다.
        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepositoryImpl);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepositoryImpl);
    }
}