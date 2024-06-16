package lotto;

public class test {
    public String name;
    public int career;
    public DevType type;

    public static void main(String[] args) {
        for (DevType type : DevType.values()) {
            System.out.println(type.getName());
        }
        System.out.printf("총 수익률은 %0.2f%%입니다.", 0.22222);
    }
}

enum DevType {
    //상수("연관시킬 문자") <- 이땐 줄 끝에 세미콜론 (;) 붙이기.
    MOBILE("안드로이드"),
    WEB("스프링"),
    SERVER("리눅스");

    final private String name;
    public String getName() {
        return name;
    }
    private DevType(String name){
        this.name = name;
    }
}
