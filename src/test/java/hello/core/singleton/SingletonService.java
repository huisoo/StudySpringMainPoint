package hello.core.singleton;

public class SingletonService {

    //static 선언 : static 영역에 객체 instance를 미리 하나 생성해서 올려둠
    //getInstance를 통해서만 조회 가능. 항상 같은 instance만 반환
    // 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
