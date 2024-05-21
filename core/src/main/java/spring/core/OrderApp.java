package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.order.Order;
import spring.core.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();

        // 회원 서비스, 주문 서비스 객체를 생성한다. => AppConfig 클래스 이용
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();
        // OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 스프링 컨테이너에서 꺼내올 클래스를 선택한다.
        MemberService memberService =
                applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService =
                applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;

        // 회원 생성
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // 생성한 회원을 가입시킨다.
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // 최종 주문 결과를 출력한다.
        // order.toString()과 결과는 동일하다.
        System.out.println("order = " + order);
    }
}