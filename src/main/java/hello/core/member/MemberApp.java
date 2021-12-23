package hello.core.member;

import hello.core.AppConfig;

public class MemberApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
 //        new MemberServiceImpl(new MemoryMemberRepository());
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + findMember.getName());
        System.out.println("findMember = " + member.getName());
    }

}
