package spring.core.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.core.member.Grade;
import spring.core.member.Member;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {
    // 추가한 정률 할인 정책을 통해 주문 금액 대비 올바른 할인율이 적용되는지 테스트한다.
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")  // JUnit5부터 적용
    void vip_o() {  // 할인 금액 적용 성공에 대한 테스트
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);  // 1000

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아닐 시, 할인이 적용되지 않아야 한다.")
    void vip_x() {  // 할인 금액 적용 실패에 대한 테스트
        //given
        Member member = new Member(1L, "memberVIP", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);  // 0

        //then
        // Assertions.assertThat(discount).isEqualTo(1000); 오류 발생!
        assertThat(discount).isEqualTo(0);
    }
}