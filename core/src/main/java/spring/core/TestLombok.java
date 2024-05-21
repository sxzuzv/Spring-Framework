package spring.core;

import lombok.Getter;
import lombok.Setter;

/**
 * @Getter, @Setter: getter, setter 메서드를 자동으로 생성한다.
 */
@Getter
@Setter
public class TestLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        TestLombok testLombok = new TestLombok();

        testLombok.setName("NAME");
        String name = testLombok.getName();

        System.out.println("name = " + name);
    }
}