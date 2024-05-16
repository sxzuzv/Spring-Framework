package spring.core.singleton;

public class StatefulService {
    // private int price; 상태를 유지하는 필드 => 공유되는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        // this.price = price;
        return price;   // 매개변수로 받은 price를 반환한다.
    }

//    public int getPrice() {
//        return price;
//    }
}