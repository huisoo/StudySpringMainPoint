package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;

public class OrderApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
        //OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

        Member member= new Member(1L, "memberA", Grade.VIP) ;
        memberService.join(member);
        Order order = orderService.createOrder(1L, "itemA", 10000);
        System.out.println("order = " + order);

    }
}
