package spring.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IncludeComponent {
    // 사용자 정의 애너테이션 생성
    // @IncludeComponent 애너테이션을 부착하면 컴포넌트 스캔에 추가로 포함한다.
}