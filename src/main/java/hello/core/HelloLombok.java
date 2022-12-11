package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter   // 롬복 자동으로 getter, setter를 만들어준다
@ToString
public class HelloLombok {
    
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("testLombok");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);
    }
}
