package spring.core;

import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.order.Order;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;

        // 회원 생성
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // 생성한 회원을 가입시킨다.
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createorder(memberId, "itemA", 10000);

        // 최종 주문 결과를 출력한다.
        // order.toString()과 결과는 동일하다.
        System.out.println("order = " + order);
    }
}
