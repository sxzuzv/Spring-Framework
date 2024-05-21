package spring.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemoryMemberRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        MemoryMemberRepositoryImpl memberRepository = new MemoryMemberRepositoryImpl();
        memberRepository.save(new Member(1L, "userA", Grade.VIP ));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepositoryImpl(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        // 검증: 할인 금액이 1000원인지 확인한다.
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}