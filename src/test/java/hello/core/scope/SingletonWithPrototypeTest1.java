package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() { // 프로토타입
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() { //싱글톤
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

    }
    @Scope("singleton")
    //@RequiredArgsConstructor
    static class ClientBean{


        //private final PrototypeBean prototypeBean; // 생성시점에 주입


//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        //@Autowired
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        //ObjectProvider는 지금딱 필요한 DL정도의 기능만 제공한다 Dependency Lookup (DL) 의존관계 조회(탐색) , 스프링에 의존
        // 그래서 나온것이 JSR-330 Provider
        // 마지막 방법은 javax.inject.Provider 라는 JSR-330 자바 표준을 사용하는 방법이다.
        // 이 방법을 사용하려면 javax.inject:javax.inject:1 라이브러리를 gradle에 추가해야 한다.
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        // 자바표준이고 기능이 단순해 단위테스트를 만들거나 mock코드를 만들기 쉽다


        public int logic() {
            //PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // 항상 새로운 프로토타입 비이 생성되는 것을 확인할 수 있다.
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // 항상 새로운 프로토타입 비이 생성되는 것을 확인할 수 있다.
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this); // 나를 찍는다
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
