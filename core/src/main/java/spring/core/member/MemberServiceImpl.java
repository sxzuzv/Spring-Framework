package spring.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 회원 서비스(역할)을 구현하는 클래스를 작성한다.
 * 회원 서비스(역할)에 대한 구현체 : MemberServiceImpl
 */

@Component
public class MemberServiceImpl implements MemberService {
    // 회원 서비스(MemberServiceImpl)는 회원 저장소(MemberRepository)에 의존한다.
    // 이때, 회원 저장소는 '메모리 회원 저장소'를 사용한다.
    // 그러므로 new 연산자와 함께 MemoryMemberRepositoryImpl을 객체로 주입해준다.
    // 주입 객체가 없을 시, NullPointerException이 발생한다.

    // 현재, MemberServiceImpl은 MemberRepository와 MemoryMemberRepositoryImpl
    // 모두에 의존하는 상태이다. => 즉, 추상화와 구체화 모두에 의존하고 있다.
    // 이 경우, 추후 코드 상의 변경이 존재할 시 클라이언트의 코드까지 변경해야 하는
    // 문제점을 초래하게 되며, 이에 따라 DIP를 위반하게 된다.

    // private final MemberRepository memberRepository = new MemoryMemberRepositoryImpl();
    // DIP 위반을 개선한다.: 클라이언트(MemberServiceImpl)는 회원 저장소(MemberRepository)에만 의존한다.

    // 의존 관계 주입: 클라이언트(MemberServiceImpl) 입장에서 보면 의존 관계를 마치 외부에서 주입해주는 것 같다.
    private final MemberRepository memberRepository;

    @Autowired  // @Autowired: 의존 관계를 자동으로 주입해주도록 생성자에 애너테이션을 부착한다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        // 생성자를 통해 memberRepository에 어떤 객체가 들어갈 것인지를 결정한다.
        this.memberRepository = memberRepository;
    }

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

    // 테스트를 위한 객체 인스턴스 조회 메서드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}