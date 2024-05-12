package spring.core.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring.core.member.MemberService;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {
    @Test
    void xmlAppContext() {
        // GenericXmlApplicationContext: ApplicationContext(=interface)의 구현체
        // GenericXmlApplicationContext: XMl 설정 파일을 기반으로 스프링 컨테이너를 생성한다.
        ApplicationContext ac
                = new GenericXmlApplicationContext("appConfig.xml");

        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
