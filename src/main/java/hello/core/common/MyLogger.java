package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request") // request  스코프면 실제 고객 요청이와야 실행이 가능하다
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

//    로그를 출력하기위한 MyLogger 클래스이다.
//    기대하는 공통 포멧: [UUID][requestURL] {message}
//    UUID를 사용해서 HTTP 요청을 구분하자.
//    requestURL 정보도 추가로 넣어서 어떤 URL을 요청해서 남은 로그인지 확인하자.
    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
