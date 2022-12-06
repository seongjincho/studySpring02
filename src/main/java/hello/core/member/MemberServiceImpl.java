package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; // 추상화에만 의존하게됨

    //@Component를 사용하게 되면 AppConfig.java처럼 의존 관계 주입에 관한 부분이 작성되어 있지 않은데 @Autowired를 사용하면 자동으로 주입
    @Autowired //ac.getBean(MemberRepository.class) 처럼 자동으로 코드가 들어간다
    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자를 통해 주입 받는다
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
