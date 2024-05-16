package spring.core.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AutoAppConfig;
import spring.core.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {
    @Test
    void basicScan() {
        // 스프링 컨테이너(싱글톤 컨테이너) 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);

        // 검증: 찾아온 빈이 MemberService 객체 인스턴스와 동일한지 판단한다.
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}