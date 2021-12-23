package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{


        //Member는 Spring Bean이 아님 --> 즉 method 자체가 호출이 안됨.
        @Autowired(required = false) // true로 하면 호출이 안됨.
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);

        }

        @Autowired
        public void setNoBean2(@Nullable  Member noBean2){
            System.out.println("noBean2 = " + noBean2);
            //호출되도록 하기 위해 @Nullable 사용
            //noBean2 = null

        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
            //noBean3 = Optional.empty (java 8)

        }
    }
}
