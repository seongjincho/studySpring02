package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core.member", //위치를 정하지 않으면 모든 패키지와 라이브러리를 뒤지기 때문에 느려진다.
        //basePackageClasses = AutoAppConfig.class, //지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
        // 지정하지않게되면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다. hello.core여기서 부터 시작해서 하위 디렉토리를 다 찾는다

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 제외할 부분을 지정
        // 설정정보를 수동으로 입력하는 부분(@Configuration 해당 어노테이션에 @Component이 붙어있기때문)을 제외 충돌이 난다.
        // @ComponentScan은 @Component이 붙어있는것을 찾아서 빈으로 등록
        // filter 설정을 하지 않으면 다음과 같이 충돌 에러메시지가 나온다 expected single matching bean
) //스프링 빈을 끌어올리는 애노테이션
public class AutoAppConfig {


//        권장하는 방법
//        개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 제공한다.
//        com.hello
//        com.hello.serivce
//        com.hello.repository
//        com.hello 프로젝트 시작 루트, 여기에 AppConfig 같은 메인 설정 정보를 두고, @ComponentScan 애노테이션을 붙이고, basePackages 지정은 생략한다.
//        이렇게 하면 com.hello 를 포함한 하위는 모두 자동으로 컴포넌트 스캔의 대상이 된다. 그리고 프로젝트 메인 설정 정보는 프로젝트를 대표하는 정보이기
//        때문에 프로젝트 시작 루트 위치에 두는 것이 좋다 생각한다.
//        참고로 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다.
//            (그리고 이 설정안에 바로 @ComponentScan 이 들어있다!)

//            컴포넌트 스캔 기본 대상
//            컴포넌트 스캔은 @Component 뿐만 아니라 다음과 내용도 추가로 대상에 포함한다.
//            @Component : 컴포넌트 스캔에서 사용
//            @Controlller : 스프링 MVC 컨트롤러에서 사용
//            @Service : 스프링 비즈니스 로직에서 사용
//            @Repository : 스프링 데이터 접근 계층에서 사용 @Configuration : 스프링 설정 정보에서 사용

    // 자동빈 vs 수동빈 충돌 테스트 ?  에러 발생하지 않는다 수동빈이 우선권을 갖고  수동빈이 자동빈을 오버라이딩한다
    //Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing
//    물론 개발자가 의도적으로 이런 결과를 기대했다면, 자동 보다는 수동이 우선권을 가지는 것이 좋다. 하지만 현실은 개발자가 의도적으로 설정해서 이런 결과가 만들어지기 보다는 여러 설정들이 꼬여서 이런 결과가 만들어지는 경우가 대부분이다!
//    그러면 정말 잡기 어려운 버그가 만들어진다. 항상 잡기 어려운 버그는 애매한 버그다.
//    그래서 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.
    //The bean 'memoryMemberRepository',
    // defined in class path resource [hello/core/AutoAppConfig.class], could not be registered.
    // A bean with that name has already been defined in file [/Users/a17647/Desktop/study/core/out/production/classes/hello/core/member/MemoryMemberRepository.class]
    // and overriding is disabled.
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
