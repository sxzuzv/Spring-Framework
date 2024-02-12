package spring.core.order;

/**
 * 주문에 대한 Entity 설정
 */


public class Order {
    private Long memberId;              // 회원 ID
    private String itemName;            // 상품 이름
    private int itemPrice;              // 상품 가격
    private int discountPrice;          // 할인 가격

    // 생성자
    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    // 할인 금액 계산 로직 : 상품 가격 - 할인 가격
    public int calculatePrice() {
        return itemPrice - discountPrice;
    }

    // 각 필드에 대한 Getter, Setter
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    // 특정 객체 출력 시 결과값 = 해당 객체의 toString() 메서드의 반환값
    // Order 객체 출력 시, toString() 메서드의 반환값이 출력된다.
    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
