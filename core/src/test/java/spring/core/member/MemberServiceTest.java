package spring.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.core.AppConfig;

/**
 * Junit 테스트 프레임워크를 활용한 테스트 코드를 작성한다.
 */

public class MemberServiceTest {
    // MemberService memberService = new MemberServiceImpl();

    MemberService memberService;

    @BeforeEach // 테스트 실행 전, 무조건 실행
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given : ~가 주어지고
        // 회원 객체를 생성한다.
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when : ~ 했을 때
        // 회원 가입한다.
        memberService.join(member);

        // 회원 ID로 회원을 조회한다.
        Member findMember = memberService.findMember(1L);

        // then : ~ 된다.
        // 가입한 회원과 조회한 회원이 동일한지 판단한다.
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}