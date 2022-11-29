package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 생성자를 주입 , cmd + opt + m 으로 리팩토링
    }

    // cmd + opt + m 으로 리팩토링 결과
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // cmd + opt + m 으로 리팩토링 결과
    public FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    //cmd + e => 파일 히스토리가 나온다 
    
}
