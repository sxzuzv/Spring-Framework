package spring.core.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring.core.AppConfig;

class BeanDefinitionTest {
//    AnnotationConfigApplicationContext ac =
//            new AnnotationConfigApplicationContext(AppConfig.class);

    GenericXmlApplicationContext ac =
            new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean() {
        // 정의된 모든 스프링 빈을 출력한다.: 스프링 기본 빈 포함
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            // getBeanDefinition(name): 각 빈에 대한 메타 데이터 정보를 가져온다.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                // 해당 빈이 애플리케이션 빈인 경우, 출력한다.
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        " / beanDefinition = " + beanDefinition);
            }
        }
    }
}