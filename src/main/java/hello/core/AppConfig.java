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

// @Configuration 을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장하지만
// @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
@Configuration // 어플리케이션의 설정 정보
public class AppConfig { //구성영역만 바꾸면 된다 , ioc컨테이너 또는 di컨테이너 라고 한다.(어셈블러, 오브젝트 팩토리)
    //팩토리 메서드를 이용한 방법

    // @Beam memberService -> new MemoryMemberRepository()
    // @Beam orderService -> new MemoryMemberRepository()
    // => 이렇게 되면 싱글톤이 깨지는것이 아닌가 ?  X

    /*
        예상결과 => @Configuration 를 주석 처리해서 싱글톤이 보장 안되었을때
        call AppConfig.memberService
        call AppConfig.memberRepository   <= new MemberServiceImpl
        call AppConfig.memberRepository  <= memberRepository()
        call AppConfig.orderService
        call AppConfig.memberRepository

        실제 결과
        call AppConfig.memberService
        call AppConfig.memberRepository
        call AppConfig.orderService

        이미 등록되어있는것은 스프링 컨테이너에서 찾아서 반환하고 없으면 생성 하기때문
     */
    @Bean  // 각 메서드에 bean 어노테이션을 달면 스프링컨테이너에 등록이 된다.
    //@Bean(name = "지정가능")
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService"); // soutm
        return new MemberServiceImpl(memberRepository()); // 생성자를 주입 , cmd + opt + m 으로 리팩토링
    }
    // cmd + opt + m 으로 리팩토링 결과
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
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
