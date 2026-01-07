package spring.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void singletonClientUsePrototype() {
        // 싱글톤 빈(ClientBean), 프로토타입 빈(PrototypeBean) 컴포넌트 스캔, 자동으로 빈에 등록한다.
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        // 싱글톤 스코프 빈은 스프링 컨테이너 생성 시점에 만들어진다.
        // 싱글톤 빈은 프로토타입 빈의 참조값을 내부 필드에 보관한다.
        // ObjectProvider를 통해 프로토타입 빈을 사용할 때마다 새롭게 생성했다.
        // 즉, 싱글톤 빈이 참조하고 있는 프로토타입 빈은 서로 다른 인스턴스이므로 count 필드는 모두 0 -> 1이 된다.
        ClientBean clientBean1 = ac.getBean(ClientBean.class);  // 첫 번째 싱글톤 빈 찾기
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);                // count1 = 1

        ClientBean clientBean2 = ac.getBean(ClientBean.class);  // 두 번째 싱글톤 빈 찾기
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);                // count2 = 1
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

    @Scope("singleton")
    static class ClientBean {
        // 스프링 컨테이너에서 지정한 프로토타입 빈을 찾아줄 ObjectProvider 의존 관계 주입
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            // logic()이 호출되면 prototypeBeanProvider를 통해 프로토타입 빈을 찾아온다.
            // 스프링 컨테이너에 요청하여 프로토타입 빈을 새로 생성한다.
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();           // 프로토타입 빈의 addCount() 호출 (count 필드 값 증가)
            return prototypeBean.getCount();    // 증가된 count 필드 값 반환
        }

        @PostConstruct
        public void init() {
            System.out.println("ClientBean.init " + this);    // 현재 싱글톤 빈(clientBean)의 참조값 확인
        }

        @PreDestroy
        public void destroy() {
            System.out.println("ClientBean.destroy");
        }
    }
}