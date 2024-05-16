package spring.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        // 스프링 컨테이너(싱글톤 컨테이너) 선언
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // statefulService1, statefulService2 모두 동일한 객체 인스턴스의 참조 값을 가지고 있다.
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A: 사용자 A가 10000원을 주문한다.
        int userAPrice = statefulService1.order("userA", 10000);

        // Thread B: 사용자 B가 20000원을 주문한다.
        int userBPrice = statefulService2.order("userB", 20000);

        // Thread A: 사용자 A가 주문 금액을 조회한다.
        // 조회 이전에 사용자 B가 주문을 완료한 상황이므로, price가 변경된다. (10000원 -> 20000)
        // 동일 객체 인스턴스를 사용하고 있으므로, 주문에 따라 price 값이 영향을 받는다.
        // int price = statefulService1.getPrice();

        // 지역 변수는 공유되지 않는다.: 주문 금액에 따라 정상적으로 price가 출력된다.
        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

        // 값의 변경이 이루어졌는지를 검증한다.: 10000원 -> 20000원 변경 true
        // assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig { // 본 테스트 코드에서 사용할 임의의 설정(구성) 클래스
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}