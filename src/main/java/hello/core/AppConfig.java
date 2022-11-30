package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 어플리케이션의 설정 정보
public class AppConfig { //구성영역만 바꾸면 된다 , ioc컨테이너 또는 di컨테이너 라고 한다.(어셈블러, 오브젝트 팩토리)

    @Bean  // 각 메서드에 bean 어노테이션을 달면 스프링컨테이너에 등록이 된다.
    //@Bean(name = "지정가능")
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); // 생성자를 주입 , cmd + opt + m 으로 리팩토링
    }


    // cmd + opt + m 으로 리팩토링 결과
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {

        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // cmd + opt + m 으로 리팩토링 결과
    @Bean
    public DiscountPolicy discountPolicy() {

        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    //cmd + e => 파일 히스토리가 나온다 
    
}
