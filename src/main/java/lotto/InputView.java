package lotto;

public class InputView {

    public void askPrice(String input) {
        validateInteger(input);
    }


    public int validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 정수 외에는 입력받을 수 없습니다.");
        }

        return Integer.parseInt(input);
    }
}
