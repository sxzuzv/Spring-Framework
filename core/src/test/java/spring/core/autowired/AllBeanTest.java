package spring.core.autowired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AutoAppConfig;
import spring.core.discount.DiscountPolicy;
import spring.core.member.Grade;
import spring.core.member.Member;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    @Test
    void findAllBean() {
        // 스프링 컨테이너(싱글톤 컨테이너) 생성
        // AutoAppConfig, DiscountService 클래스를 스프링 빈으로 등록한다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        // 검증: 고정 할인 정책의 경우, 할인 금액이 1000원이어야 한다.
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        // 검증: % 할인 정책의 경우, 할인 금액이 총 금액의 10%여야 한다. (20000원 -> 2000원)
        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;

            // DiscountService 클래스만 스프링 빈으로 등록할 시, Map과 List에는 아무 값도 들어가지 않는다.
            // AutoAppConfig 클래스를 스프링 빈으로 함께 등록하면 컴포넌트 스캔을 통해 등록된 빈들이 출력된다.
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            // 할인 코드(discountCode)를 빈 이름과 매칭시킨다.
            // Map에서 할인 코드와 일치하는 할인 정책을 가져와 저장한다.
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            // 할인 정책에 맞는 할인가를 반환한다.
            return discountPolicy.discount(member, price);
        }
    }
}