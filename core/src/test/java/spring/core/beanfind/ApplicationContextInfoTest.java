package spring.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AppConfig;

class ApplicationContextInfoTest {
    // 스프링 컨테이너 생성: 설정(구성) 정보는 AppConfig 클래스를 사용한다.
    AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 Bean 출력")
    void findAllBean() {
        // 정의된 모든 스프링 빈을 출력한다.: 스프링 기본 빈 포함
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 각 빈의 이름은 알고 있으나, 반환 형태는 알 수 없으므로 최상위 클래스인 Object 형태로 반환 값을 받는다.
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " / object = " + bean);
        }
    }
}