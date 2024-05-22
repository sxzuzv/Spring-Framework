package spring.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import spring.core.member.Grade;
import spring.core.member.Member;

@Component
//@Qualifier("mainDiscountPolicy")
//@Primary: @Autowired 시, @Primary 애너테이션이 붙은 빈을 가장 높은 우선순위로 설정한다.
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