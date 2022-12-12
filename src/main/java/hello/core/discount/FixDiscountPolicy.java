package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // RateDiscountPolicy에 이미 @Component 를 달아놔서 테스트시 에러가 발생한다
@Qualifier("fixDiscountPolicy")
//NoUniqueBeanDefinitionException,  expected single matching bean but found 2: fixDiscountPolicy,rateDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy {
    private int discountFixAmount = 1000; //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
