package spring.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    public void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // 프로토타입 스코프 빈은 조회 시점에 생성 및 초기화된다.
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); // 첫 번째 프로토타입 빈 찾기
        prototypeBean1.addCount();  // count 필드 증가
        assertThat(prototypeBean1.getCount()).isEqualTo(1);     // count = 1

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); // 두 번째 프로토타입 빈 찾기
        prototypeBean2.addCount();  // count 필드 증가
        assertThat(prototypeBean2.getCount()).isEqualTo(1);     // count = 1

        // 프로토타입 스코프 빈은 수동으로 종료해줘야 한다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();

        // ac.close(); 작동하지 않는다.
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);   // 현재 프로토타입 빈의 참조값 확인
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");        // 프로토타입 스코프 빈이므로 호출 X
        }
    }
}