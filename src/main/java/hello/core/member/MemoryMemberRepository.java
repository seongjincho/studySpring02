package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component   // 빈으로 등록
public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //private static ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);  // shift + cmd + enter
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
