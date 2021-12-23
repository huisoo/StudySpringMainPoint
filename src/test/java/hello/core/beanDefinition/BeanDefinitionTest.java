package hello.core.beanDefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    //Spring은 BeanDefinition으로 AppConfig를 추상화 한다. --> BeanDefinition 정보에서 메타정보를 가져올 수 있다.
    //Java Config의 경우 factoryBean을 통해서 등록하는 방식, factoryMethod를 통해서 등록, 메소드 호출해서 등록하는 방식, class [null], factoryBeanName=AppConfig, factoryMethodName=orderService
    //xml Config의 경우 bean의 대한 정보가 명확히 등록되어 있음, factorymethod, factorybean이 빠져있음
    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplcationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName + " beanDefinition = " + beanDefinition);
            }

        }
    }
}
