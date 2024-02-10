package spring.core.member;

/**
 * 회원 서비스(역할)에 해당하는 인터페이스를 작성한다. : MemberService
 * 회원 서비스(역할)은 1) 회원가입 2) 회원조회 기능을 제공한다.
 */

public interface MemberService {
    void join(Member member);               // 회원가입 메서드
    Member findMember(Long memberId);       // 회원 ID를 통한 회원조회 메서드
}
