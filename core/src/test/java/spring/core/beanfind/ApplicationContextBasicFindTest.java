package spring.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;

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
}