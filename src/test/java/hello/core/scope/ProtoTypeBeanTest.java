package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ProtoTypeBeanTest { // 파일명 바꾸기 fn + shift + f6
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        System.out.println("find prototypeBean1");
        ProtoTypeBean prototypeBean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("find prototypeBean2");
        ProtoTypeBean prototypeBean2 = ac.getBean(ProtoTypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2); //isSameAs ==

        // 필요한 경우에는 수동으로 호출
//        prototypeBean1.destroy();
//        prototypeBean2.destroy();

        ac.close(); // 프로토타입은 스프링컨테이너가 종료될때 종료메서드 @PreDestroy 을 실행하지 않는다

        /*
        스프링 컨테이너에 요청할 때 마다 새로 생성된다.
        스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.
        종료 메서드가 호출되지 않는다.
        그래서 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 한다. 종료 메서드에 대한 호출도 클라이언트가 직접 해야한다.
         */

    }

    @Scope("prototype")
    static class ProtoTypeBean {
        @PostConstruct
        public void init() {
            System.out.println("ProtoTypeBean.init");
        }
        @PreDestroy
        public void destroy() {
            System.out.println("ProtoTypeBean.destroy");
        }
    }
}
