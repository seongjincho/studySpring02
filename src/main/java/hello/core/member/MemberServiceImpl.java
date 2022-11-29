package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; // 추상화에만 의존하게됨

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
}
