package hello.core.singleton;

public class StatefulService {  //cmd + shift + t 로 테스트 만들기 가능
    //private int price; // 상태를 유지하는 필드, 10000 -> 20000원으로 바꿔짐


    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        //this.price = price; // 여기가 문제!
        return price;
    }

//    public int getPrice() {
//        return price;
//    }

}
