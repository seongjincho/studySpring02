package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    //빈 정보 찾기
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {  // iter + tap for문 자동
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("1.Name = " + beanDefinitionName + " object = " + bean);
        }
    }
    // cmd + d 똑같이 복사
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findAppllicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {  // iter + tap for문 자동
           BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);


            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("2.Name = " + beanDefinitionName + " object = " + bean);
//                2.Name = appConfig object = hello.core.AppConfig$$EnhancerBySpringCGLIB$$aa31659a@3f390d63
//                2.Name = memberService object = hello.core.member.MemberServiceImpl@74a6a609
//                2.Name = memberRepository object = hello.core.member.MemoryMemberRepository@5a411614
//                2.Name = orderService object = hello.core.order.OrderServiceImpl@2374d36a
//                2.Name = discountPolicy object = hello.core.discount.RateDiscountPolicy@54d18072
            }

        }
    }
}
