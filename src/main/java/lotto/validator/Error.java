package lotto.validator;

public class Error {

    private Error() {
        throw new AssertionError("인스턴스화 불가능");
    }

    public static void printError(String message) {
        System.out.println("[ERROR]: " + message);
    }

}
