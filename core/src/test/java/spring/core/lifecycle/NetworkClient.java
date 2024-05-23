package spring.core.lifecycle;

public class NetworkClient {    // 테스트를 위한 클라이언트 객체
    private String url;

    public NetworkClient() {
        // NetworkClient 객체 생성 시, connect() 메서드 호출 + call() 메서드에 연결 메시지 전달
        System.out.println("생성자 호출! URL = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출 메서드: connect()
    public void connect() {
        System.out.println("CONNECT: " + url);
    }

    public void call(String message) {
        System.out.println("CALL : " + url + " / MESSAGE = " + message);
    }

    // 서비스 종료 시 호출 메서드: disconnect()
    public void disconnect() {
        System.out.println("CLOSE: " + url);
    }
}