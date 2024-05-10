package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        // MemberService memberService = new MemberServiceImpl();

        // 회원 서비스 객체를 생성한다. => AppConfig 클래스 이용
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // ApplicationContext(=스프링 컨테이너): AppConfig 클래스의 @Bean 애너테이션을 통해 등록해둔 것들을 관리해준다.
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 스프링 컨테이너에서 꺼내올 클래스를 선택한다.
        // name 속성: 스프링 컨테이너에 등록된 이름(=메서드 이름)을 작성한다.
        // 타입 속성: 반환 타입을 작성한다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // 회원 가입
        // 1) 회원 객체를 생성한다. : ID(1), 이름(memberA), 등급(VIP)
        // 2) 회원 서비스의 join 메서드를 통해 회원 가입한다.
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        // 회원 조회
        // 1) 회원 서비스의 findMember 메서드를 통해 ID가 1인 회원을 조회한다.
        Member findMember = memberService.findMember(1L);

        // 가입한 회원의 이름을 출력한다.
        System.out.println("new member = " + member.getName());
        // 조회한 회원의 이름을 출력한다.
        System.out.println("find Member = " + findMember.getName());
    }
}
