package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    @Override
    public Order createOrder(Long membeId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(membeId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // cmd + opt + v

        return new Order(membeId, itemName, itemPrice, discountPrice);
    }
}
