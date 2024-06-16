package lotto;
// https://staticclass.tistory.com/72 참고
public class MyException extends java.lang.Exception {
    public MyException() {
    }

    public MyException(String errMoney) {
        super(errMoney);
    }
}
