package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // 자기자신을 내부에 스태틱으로 가진다  1개만 존재하게됨 , 자기자신을 생성하여 instance에 객체로 생성해놓는다.

    // 조회시 사용, getInstance()를 통해서만 조회 가능
    // 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
