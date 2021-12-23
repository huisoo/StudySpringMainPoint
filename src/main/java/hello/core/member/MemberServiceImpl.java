package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; //= new MemoryMemberRepository();

    //MemberServiceImpl 이 repository를 선택하도록 하지 않고
    //이 부분을 AppConfig에서 설정하도록 한다.
    //이렇게 설정하면, 추상화에 대해서만 의존한다. DIP를 만족한다.

    //생성자를 통해서 어떤 레포지토리를 선택할 지 정한다.
    @Autowired //자동의존 관계 주입 ( 생성자에 붙여줌 )
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
