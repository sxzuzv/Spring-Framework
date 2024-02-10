package spring.core.member;

/**
 * 회원 저장소(역할)에 해당하는 인터페이스를 작성한다. : MemberRepository
 */

public interface MemberRepository {
    void save(Member member);           // 회원 등록 메서드
    Member findById(Long memberId);     // 회원 ID를 통한 회원 조회 메서드
}
