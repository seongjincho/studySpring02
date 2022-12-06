package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderServie -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

//        memberService -> memberRepository1 = hello.core.member.MemoryMemberRepository@3c9bfddc
//        orderServie -> memberRepository2 = hello.core.member.MemoryMemberRepository@3c9bfddc
//        memberRepository = hello.core.member.MemoryMemberRepository@3c9bfddc

        // 모두 같은 instance로 호출됨
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }
    
    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
        //bean.getClass() = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$ee2b784e
        // 순수한 클래스라면 class hello.core.AppConfig 출력되어야함 , CGLIB라는 바이트코드 조작 라이브러리 사용
        // @Configuration 주석 처리후 출력 => bean.getClass() = class hello.core.AppConfig
    }
}
