package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final MyLogger myLogger;
    // request  스코프면 실제 고객 요청이와야 실행이 가능하다  그래서 에러 발생 provider를 사용하여 해결
    // ObjectProvider 덕분에 ObjectProvider.getObject() 를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다.
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        MyLogger myLogger = myLoggerProvider.getObject();
        String requestURL = request.getRequestURI().toString(); // 고객이 어떤 url로 요청했는지 알 수 있다.
        myLogger.setRequestURL(requestURL);
        //requestURL을 MyLogger에 저장하는 부분은 컨트롤러 보다는 공통 처리가 가능한 스프링 인터셉터나 서블릿 필터 같은 곳을 활용하는 것이 좋다

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testID");
        return "OK";
    }

}
