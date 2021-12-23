package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        /* 웹어플리케이션의 특징 : 호출이 연속적으로 들어온다, 다수의 클라이언트*/

        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService = appConfig.memberService();
        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인.
        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        // -> 객체가 여러개 생성됨을 확인 할 수 있다. 웹 어플리케이션에서 적합하지 않음.
        // -> 고객 트래픽이 초당 10이 나오면 초당 10개의 객체가 생성되고 소멸된다 --> 메모리 낭비가 심함
        // -> 해결방안은 해당 객체가 1개만 생성되고, 공유하도록 설계하면 된다. --> 싱글톤 패턴
        Assertions.assertThat(memberService).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체사용")
    void singletonServiceTest(){

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();


        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체사용")
    void springContainer(){

        /* 웹어플리케이션의 특징 : 호출이 연속적으로 들어온다, 다수의 클라이언트
            스프링은 기본적으로 싱글톤으로 동작한다.
        * */

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인.
        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        // -> 객체가 여러개 생성됨을 확인 할 수 있다. 웹 어플리케이션에서 적합하지 않음.
        // -> 고객 트래픽이 초당 10이 나오면 초당 10개의 객체가 생성되고 소멸된다 --> 메모리 낭비가 심함
        // -> 해결방안은 해당 객체가 1개만 생성되고, 공유하도록 설계하면 된다. --> 싱글톤 패턴
        Assertions.assertThat(memberService).isSameAs(memberService2);
    }
}
