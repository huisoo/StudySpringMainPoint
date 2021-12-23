package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {

    MemberService memberService;// = new MemberServiceImpl(new MemoryMemberRepository());

    @BeforeEach
    public void beforeEach(){

        //변경 전 (java)
        //AppConfig appConfig = new AppConfig();
        //memberService = appConfig.memberService();

        //Spring 변경 후
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService  = ac.getBean("memberService", MemberService.class);
    }

    @Test
    void join(){

        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
