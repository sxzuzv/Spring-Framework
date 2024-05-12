package spring.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService =
                ac.getBean("memberService", MemberService.class);

        // memberService(MemberService = interface)의 구현 객체를 판단한다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    void findBeanByType() {
        // 이름 없이 타입으로만 빈을 조회한다.
        MemberService memberService = ac.getBean(MemberService.class);

        // memberService(MemberService = interface)의 구현 객체를 판단한다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByType2() {
        // 구현 객체 자체를 조회한다.
        // MemberService = interface, MemberServiceImpl = MemberService의 구현체
        MemberServiceImpl memberService =
                ac.getBean("memberService", MemberServiceImpl.class);

        // memberService의 구현 객체를 판단한다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("조회할 빈이 존재하지 않는 경우")
    void findBeanByNameX() {
        // ac.getBean("xxxxx", MemberService.class); => "xxxxx"라는 이름의 빈은 존재하지 않는다.

        // () -> : 우측에 지정한 로직을 실행했을 시, 지정한 예외가 발생하면 테스트에 성공한다.
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}