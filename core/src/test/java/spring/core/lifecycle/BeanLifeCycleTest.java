package spring.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        // 스프링 컨테이너(싱글톤 컨테이너) 생성
        // ConfigurableApplicationContext: AnnotationConfigApplicationContext의 상위 인터페이스
        // => 부모는 자식을 담을 수 있다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient client = ac.getBean(NetworkClient.class);

        ac.close();
    }

    @Configuration  // 설정 정보 클래스에 부착
    static class LifeCycleConfig {
//        @Bean(initMethod = "init", destroyMethod = "close") // 빈 등록 초기화, 소멸 메서드를 지정한다.
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();  // 스프링 빈 등록 과정에서 객체 생성, 생성자가 호출된다.
            networkClient.setUrl("http://hello-spring.dev");    // 임의의 URL 지정
            return networkClient;   // 반환 객체가 스프링 빈으로 등록된다.
        }
    }
}