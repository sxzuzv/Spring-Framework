package spring.core;

import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepositoryImpl;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

/**
 * 애플리케이션 전반을 설정하고 구성하는 클래스를 만든다.
 * => 역할과 구현이 명확히 드러나도록 작성한다.
 */

public class AppConfig {
    public MemberService memberService() {
        // 1) MemoryMemberRepositoryImpl 객체를 생성한다.
        // 2) 객체 생성으로 반환되는 참조 값을 MemberServiceImpl 객체를 생성하면서 생성자로 전달한다.

        // return new MemberServiceImpl(new MemoryMemberRepositoryImpl());
        return new MemberServiceImpl(memberRepositoryImpl());

    }

    private static MemoryMemberRepositoryImpl memberRepositoryImpl() {
        return new MemoryMemberRepositoryImpl();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepositoryImpl(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}