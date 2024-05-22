package spring.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.core.member.Grade;
import spring.core.member.Member;

/**
 * 할인 정책(역할)을 구현하는 클래스를 작성한다.
 * 할인 정책(역할)에 대한 구현체 : FixDiscountPolicy
 */
@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {
    // 회원 등급이 VIP일 경우, 할인 금액을 1000원으로 적용한다. (정액 할인 정책)
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {   // 회원 등급이 VIP일 경우
            return discountFixAmount;           // 할인 금액 1000원을 반환한다.
        } else {                                // 회원 등급이 VIP가 아닐 경우
            return 0;                           // 할인 금액 0원을 반환한다.
        }
    }
}
