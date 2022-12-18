package hello.core.lifecyle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {
    //InitializingBean 초기화 빈 , DisposableBean 종료 => 단점: 스프링에 의존적 , (초기화,소멸) 메소드명 변경 불가 , 외부 라이브러리에 적용 X
    // 위에 방법은 초창기에 나온 방법이다.
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        //connect();
        //call("초기화 연결 메시작");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("cooncect : " + url);
    }

    public void call(String message){
        System.out.println("call : " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

//    @Override
//    public void afterPropertiesSet() throws Exception { //초기화 후 호출
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//    @Override
//    public void destroy() throws Exception { // 종료 후 호출
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    //메서드 이름을 자유롭게 줄 수 있다, 스프링빈이 스프링코드에 의존하지 않는다. , 외부 라이브러리에 적용 가능

//    public void init() { //초기화 후 호출
//        System.out.println("NetworkClient.init");
//        connect();
//        call("초기화 연결 메시지");
//    }
//    public void close() { // 종료 후 호출
//        System.out.println("NetworkClient.close");
//        disconnect();
//    }

    @PostConstruct
    public void init() { //초기화 후 호출
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
    @PreDestroy
    public void close() { // 종료 후 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }

    // 애노테이션 방식 최신 스프링에서 권장하는 방식  , 간단하고 스프링에 종속적이지 않다 JSR-250이라는 자바표준이다.
    // 컴포넌트 스캔과 잘 어울린다.
    // 단점은 외부 라이브러리에 적용하지 못한다는것 외부 라이브러리를 초기화, 종료 해야 하면 @Bean의 기능(iniMethod, destroyMethod)을 사용하자
    // @Bean(initMethod = "init", destroyMethod = "close")


}
