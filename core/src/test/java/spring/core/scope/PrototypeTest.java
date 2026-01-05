package spring.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    public void prototypeBeanFind() {
        // 컴포넌트 클래스를 파라미터로 넣어주면 자동으로 컴포넌트 스캔하여 등록 가능
        // 파라미터로 넣은 클래스가 컴포넌트 스캔 대상처럼 동작하므로 PrototypeBean 클래스에 @Component 애너테이션 안 붙여도 된다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // 프로토타입 스코프 빈은 조회 시점에 생성 및 초기화된다.
        System.out.println("find prototypeBean1");
        PrototypeTest.PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); // 첫 번째로 조회하는 프로토타입 빈

        System.out.println("find prototypeBean2");
        PrototypeTest.PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); // 두 번째로 조회하는 프로토타입 빈

        System.out.println("prototypeBean1: " + prototypeBean1);
        System.out.println("prototypeBean2: " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2); // 프로토타입 빈은 매번 인스턴스를 생성한다.

        // 프로토타입 스코프 빈은 수동으로 종료해줘야 한다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();

        ac.close(); // 작동하지 않는다.
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean destroy");
        }
    }
}