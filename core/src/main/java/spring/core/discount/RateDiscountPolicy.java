package spring.core.discount;

import org.springframework.stereotype.Component;
import spring.core.member.Grade;
import spring.core.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {
    // 주문한 금액 대비 %를 할인해주는 새로운 정률 할인 정책을 추가한다.
    private int discountPercent = 10;   // 할인율 10% 고정

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}