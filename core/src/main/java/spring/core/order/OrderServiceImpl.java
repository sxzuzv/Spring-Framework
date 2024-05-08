package spring.core.order;

import spring.core.discount.DiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;

/**
 * 주문 서비스(역할)을 구현하는 클래스를 작성한다.
 * 주문 서비스(역할)에 대한 구현체 : OrderServiceImpl
 */

public class OrderServiceImpl implements OrderService {
    // 주문 서비스는 회원 저장소, 할인 정책에 접근하여 회원 및 할인 정책에 대한 정보를 얻어야 한다.
    // 주문 서비스는 할인 정책과 관련한 부분은 DiscountPolicy에게 권한을 위임하고 있다. (할인 금액 파악 업무를 맡기고, 금액만 받는다.)
    // 이를 통해 단일 책임 원칙을 준수하는 설계가 가능해진다. (할인 관련 코드 변경이 있다고 해도, 주문 서비스에서는 변경할 코드가 없다.)

    // 새로운 할인 정책을 적용한다.: FixDiscountPolicy -> RateDiscountPolicy
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 현재 클라이언트(OrderServiceImpl)는 DiscountPolicy 인터페이스 뿐만 아니라
    // 구체 클래스인 FixDiscountPolicy, RateDiscountPolicy도 함께 의존하고 있다. (DIP, OCP 위반)

    // 클라이언트(OrderServiceImpl)가 MemberRepository, DisountPolicy 인터페이스에만 의존하도록 코드를 변경한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        // 생성자를 통해 memberRepository, discountPolicy에 어떤 객체가 들어갈 것인지를 결정한다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createorder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);                        // 회원 등급 파악
        int discountPrice = discountPolicy.discount(member, itemPrice);             // 할인 금액 파악

        // 회원 ID, 상품 이름, 상품 원가, 할인 금액이 포함된 최종 주문 객체를 반환한다.
        // (주문 서비스는 최종 주문 결과를 반환하면 된다.)
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
