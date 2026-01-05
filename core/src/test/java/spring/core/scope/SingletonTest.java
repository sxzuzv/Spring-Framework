package spring.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    public void singletonBeanFind() {
        // 컴포넌트 클래스를 파라미터로 넣어주면 자동으로 컴포넌트 스캔하여 등록 가능
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class); // 첫 번째로 조회하는 싱글톤 빈
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class); // 두 번째로 조회하는 싱글톤 빈
        System.out.println("singletonBean1: " + singletonBean1);
        System.out.println("singletonBean2: " + singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean destroy");
        }
    }
}