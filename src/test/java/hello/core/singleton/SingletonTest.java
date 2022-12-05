package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        //memberService1 = hello.core.member.MemberServiceImpl@17f7cd29
        System.out.println("memberService2 = " + memberService2);
        //memberService2 = hello.core.member.MemberServiceImpl@7d8704ef

        //assertj ,memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

        //메모리 낭비가 심하다   => 해결방안은 싱글톤 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다.
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        //new SingletonService(); //SingletonService() has private access in hello.core.singleton.SingletonService 객체 직접 접근 불가
        // private로 new키워드를 막았다.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isEqualTo(singletonService2);
        singletonService1.logic();
        // same ==
        // equal java의 equals

        // 스프링컨테이너가 있으면 싱글톤으로 직접 변경할 필요가 없다. 스프링컨테이너에서 싱글톤으로 처리한다.

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
//        memberService1 = hello.core.member.MemberServiceImpl@1a9c38eb
//        memberService2 = hello.core.member.MemberServiceImpl@1a9c38eb
        //스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용할 수 있다.

        assertThat(memberService1).isSameAs(memberService2);


    }
}
