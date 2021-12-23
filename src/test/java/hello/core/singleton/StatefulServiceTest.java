package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //Thread A : A사용자가 10000원 주문
        int userAprice = statefulService1.order("userA", 10000);
        //Thread B : B사용자가 20000원 주문
        int userBprice = statefulService2.order("userB", 20000);
        //A는 10000원이 나오길 바랬으나 B때문에 20000원이 나옴
        // --> 문제!! // 같은 객체이기 때문에!! 싱글톤에서 생길 수 있는 문제!! --> 해결 : 무상태로 설계해야한다!!
        System.out.println("userAprice = " + userAprice);
    }


    @Test
    void statefulServiceSingleton2(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //Thread A : A사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        //Thread B : B사용자가 20000원 주문
        statefulService2.order("userB", 20000);
        //int price = statefulService1.getPrice();
        //A는 10000원이 나오길 바랬으나 B때문에 20000원이 나옴
        // --> 문제!! // 같은 객체이기 때문에!! 싱글톤에서 생길 수 있는 문제!! --> 해결 : 무상태로 설계해야한다!!
        //System.out.println("price = " + price);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}