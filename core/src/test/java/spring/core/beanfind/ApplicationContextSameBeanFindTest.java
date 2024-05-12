package spring.core.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac =
            new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("두 개 이상의 동일 타입이 존재하는 경우, 조회 시 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        // 이름 없이 타입으로만 조회 시, 두 개의 동일 타입이 존재하므로 오류가 발생한다.
        // MemberRepository bean = ac.getBean(MemberRepository.class);

        // () -> : 우측에 지정한 로직을 실행했을 시, 지정한 예외가 발생하면 테스트에 성공한다.
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Configuration
    static class SameBeanConfig {   // 본 테스트 코드에서 사용할 임의의 설정(구성) 클래스
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepositoryImpl();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepositoryImpl();
        }
    }
}