package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {

    MemberService memberService;// = new MemberServiceImpl(new MemoryMemberRepository());
    OrderService orderService;// = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

    @BeforeEach
    public void beforeEach(){

        /*
        java 변경 전
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();*/

        /*Spring 변경 후 */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = ac.getBean("memberService", MemberService.class);
        orderService = ac.getBean("orderService", OrderService.class);

    }
    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "ItemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest(){
        //OrderServiceImpl orderService = new OrderServiceImpl();
        //orderService.createOrder(1L, "itemA", 10000);
    }
}
