package spring.core;

import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        // 회원 서비스 객체를 생성한다.
        MemberService memberService = new MemberServiceImpl();

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
        System.out.println("findMember = " + findMember.getName());
    }
}
