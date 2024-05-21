package spring.core.autowired;

import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Member;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption() {
        // 스프링 컨테이너(싱글톤 컨테이너) 생성: TestBean 클래스도 스프링 빈으로 등록된다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {     // 본 테스트 코드에서 사용할 임의의 클래스, 단순히 스프링 빈으로만 등록한다. (설정 정보로 사용 X)
        // @Autowired(required = false): 자동 주입 대상이 없을 시, setNoBeanOne() 메서드 자체가 호출되지 않는다.
        @Autowired(required = false)
        public void setNoBeanOne(Member noBeanOne) {    // 스프링 컨테이너에 의해 관리되지 않는 클래스(Member)를 이용
            System.out.println("noBeanOne = " + noBeanOne);
        }

        @Autowired
        // org.springframework.lang.@Nullable: 자동 주입 대상이 없을 시, null이 입력된다.
        public void setNoBeanTwo(@Nullable Member noBeanTwo) {
            System.out.println("noBeanTwo = " + noBeanTwo);
        }

        @Autowired
        // (Java 8) Optional<>: 자동 주입 대상이 없을 시, Optional.empty가 입력된다.
        public void setNoBeanThree(Optional<Member> noBeanThree) {
            System.out.println("noBeanThree = " + noBeanThree);
        }
    }
}