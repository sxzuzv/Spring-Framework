package spring.core.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;

        // 회원 생성
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // 생성한 회원을 가입시킨다.
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createorder(memberId, "itemA", 10000);

        // 최종 주문 결과에서의 할인 금액과 정액 할인 적용 금액이 동일한지 검증한다.
        Assertions.assertEquals(order.getDiscountPrice(), 1000);
    }
}
