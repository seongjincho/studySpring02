package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    //psvm
    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig(); // appConfig를 이용
        //MemberService memberService = appConfig.memberService();

        // 스프링으로 이용
        // 스프링은 ApplicationContext부터 시작된다.  ApplicationContext은 스프링 컨테이너라 하고 bean으로 등록된 객체를 관리한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 메서드 이름을 적는다.

        Member member = new Member(1L, "memberA", Grade.VIP);  // cmd + option + v
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
