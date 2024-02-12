package spring.core.order;

/**
 * 주문 서비스(역할)에 해당하는 인터페이스를 작성한다. : OrderService
 */

public interface OrderService {
    // 주문 서비스는 회원 정보, 할인 정책을 토대로 최종 주문 결과를 반환한다.
    Order createorder(Long memberId, String itemName, int itemPrice);   // 최종 주문 결과 반환 메서드
}
