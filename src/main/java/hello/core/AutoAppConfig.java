package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {"hello.core"}, // 어디서 부터 쳤는지 지정 basePackages = {"hello.core", "hello.core.member"}
        // 지정하지 않으면, 현재 클래스의 package hello.core 아래를 모두 찾는다.

        //@SpringBootApplication 어노테이션 아래에 @ComponentScan 이 등록되어 있어서 사실상 신경쓸게 없다..
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
        //기존 예제코드에 Configuration 이 exclude 됨.
        //@Component 붙은걸 자동 스캔해서 등록 함.
)
public class AutoAppConfig {

}
