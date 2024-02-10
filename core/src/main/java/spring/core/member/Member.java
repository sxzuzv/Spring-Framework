package spring.core.member;

/**
 * 회원에 대한 Entity 설정
 */

public class Member {
    private Long id;            // 회원 ID
    private String name;        // 회원 이름
    private Grade grade;        // 회원 등급

    // 생성자
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // 각 필드에 대한 Getter, Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
