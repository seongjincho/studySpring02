package hello.core.lifecyle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
        // 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.  객체생성 -> 의존관계주입
        // 스프링빈의 이벤트 라이프사이클
        // 스프링 컨테이너 생성-> 스프링 빈 생성 -> 의존관계주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
