package spring.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.core.AppConfig;
import spring.core.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    @Test
    @DisplayName("스프링이 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 2. 확인: 참조 값이 다름을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 3. 검증: memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // new 연산자를 사용한 SingletonService 객체 생성은 불가하다. => private 생성자 때문!
        // 1. 조회: 생성된 객체 조회를 위해서는 getInstance() 메서드를 호출해야 한다.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 2. 확인: 참조 값이 동일함을 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // 3. 검증: singletonService1 == singletonService2
        // isSameAs(): 객체 인스턴스의 비교, 즉 참조 값을 비교한다.
        // isEqualTo(): 대상의 내용 자체를 비교한다.
        assertThat(singletonService1).isSameAs(singletonService2);
    }
}