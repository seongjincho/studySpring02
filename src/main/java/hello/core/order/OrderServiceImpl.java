package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //interface에만 의존한다 , dip가 지켜지고 있다

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { // 생성자를 통해 주입 받는다
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // interface에만 의존하게됨  기존의 아래 코드는 구현체, 추상화된것에 둘다 의존 되어있다.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 할인 정책 변경
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    @Override
    public Order createOrder(Long membeId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(membeId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // cmd + opt + v

        return new Order(membeId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
