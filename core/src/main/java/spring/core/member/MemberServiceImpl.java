package spring.core.member;

/**
 * 회원 서비스(역할)을 구현하는 클래스를 작성한다.
 * 회원 서비스(역할)에 대한 구현체 : MemberServiceImpl
 */

public class MemberServiceImpl implements MemberService {
    // 회원 서비스(MemberServiceImpl)는 회원 저장소(MemberRepository)에 의존한다.
    // 이때, 회원 저장소는 '메모리 회원 저장소'를 사용한다.
    // 그러므로 new 연산자와 함께 MemoryMemberRepositoryImpl을 객체로 주입해준다.
    // 주입 객체가 없을 시, NullPointerException이 발생한다.

    // 현재, MemberServiceImpl은 MemberRepository와 MemoryMemberRepositoryImpl
    // 모두에 의존하는 상태이다. => 즉, 추상화와 구체화 모두에 의존하고 있다.
    // 이 경우, 추후 코드 상의 변경이 존재할 시 클라이언트의 코드까지 변경해야 하는
    // 문제점을 초래하게 되며, 이에 따라 DIP를 위반하게 된다.
    private final MemberRepository memberRepository =
            new MemoryMemberRepositoryImpl();

    // 회원 등록 (회원 저장소의 메서드를 사용하여 회원을 등록한다.)
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    // 회원 조회 (회원 저장소의 메서드를 사용하여 회원을 조회한다.)
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
