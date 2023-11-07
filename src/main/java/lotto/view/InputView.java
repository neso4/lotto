package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String getInput(String type) {
        String input_text = Console.readLine();
        switch (type) {
            case "number":
                try {
                    Integer.parseInt(input_text);
                } catch (NumberFormatException ex) {
                    System.out.println("[ERROR] 잘못된 타입을 입력하셨습니다.");
                    return null;
                }
            case "text":
            default:
                break;
        }
        return input_text;
    }
}
