package spring.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 회원 저장소(역할)를 구현하는 클래스를 작성한다.
 * 회원 저장소(역할)에 대한 구현체 : MemoryMemberRepositoryImpl
 */

@Component
public class MemoryMemberRepositoryImpl implements MemberRepository {
    // store : 회원 정보 저장소
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    // 저장소에 회원 정보 등록
    public void save(Member member) {
        // member 객체에서 회원 ID를 가져와 저장소(store)에 저장한다.
        store.put(member.getId(), member);
    }

    @Override
    // 저장소에서 회원 ID를 통해 회원 정보 조회
    public Member findById(Long memberId) {
        // 회원 ID를 통해 저장소(store)에 있던 회원 정보를 조회한다.
        return store.get(memberId);
    }
}