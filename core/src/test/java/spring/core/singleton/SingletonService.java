package spring.core.singleton;

public class SingletonService {
    // 자기 자신의 객체를 내부에 static 상태로 보유한다. => static 영역에 하나의 객체만 존재한다.
    // 서버를 띄울 때 static 영역에 SingletonService의 객체를 생성한 후, instance에 참조 값을 넣어둔다.
    private static final SingletonService instance = new SingletonService();

    // 1. 조회 시 사용: instance의 참조를 꺼내기 위한 유일한 방법
    public static SingletonService getInstance() {
        // 생성된 인스턴스를 반환한다.
        return instance;
    }

    // 2. private 생성자 사용
    // 싱글톤 패턴에서는 단 하나의 객체 생성을 보장한다.
    // 외부에서 new 연산자를 사용한 객체 생성이 불가하도록 private 생성자를 사용한다.
    private SingletonService() {
        // 외부 클래스에서는 private 생성자에 접근할 수 없으므로, 객체 생성도 불가하다.
    }

    public void login() {
        System.out.println("싱글톤 패턴 객체 로직을 호출하였습니다.");
    }
}