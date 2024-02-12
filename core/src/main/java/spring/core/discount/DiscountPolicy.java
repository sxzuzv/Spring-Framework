package spring.core.discount;

import spring.core.member.Member;

/**
 * 할인 정책(역할)에 해당하는 인터페이스를 작성한다. : DiscountPolicy
 */

public interface DiscountPolicy {
    /**
     * @return 할인 금액
     */
    int discount(Member member, int price);     // 정액 할인 적용 메서드
}
