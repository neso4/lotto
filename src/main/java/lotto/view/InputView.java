package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class InputView {

    private static final String ASK_PURCHASE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String ASK_WINNING_NUMBER_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS_WINNING_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String START_OF_SENTENCE_CHARACTER = "[ERROR] 문장은 첫번째 문자를 숫자 외 허용하지 않습니다.";
    private static final String END_OF_SENTENCE_CHARACTER = "[ERROR] 문장은 마지막 문자를 숫자 외 허용하지 않습니다.";
    public static final String PERMIT_ONLY_NUMBER = "[ERROR] 정수 외에는 입력받을 수 없습니다.";
    public static final String NO_PERMIT_BLANK = "[ERROR] 공백은 입력될 수 없습니다.";
    public static final String NO_PERMIT_INPUT_LESS_ZERO = "[ERROR] 0 이하의 값은 입력받을 수 없습니다.";
    public static final String INDEX_OUT_OF_INPUT_LENGTH = "[ERROR] index 가 문자열의 범위를 벗어났습니다.";

    public int askPrice() {
        try {
            String input = printHowManyPurchase();
            validateBlankAndEmptyInteger(input);
            return (validateNegativeIntegerAndZero(validateInteger(input)));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askPrice();
        }
    }

    public Lotto askWinningNumber() {
        try {
            String input = printWinningNumber();
            validateBlankAndEmptyInteger(input);
            validateFirstCharacter(input);
            validateLastCharacter(input);
            return new Lotto(new ArrayList<>((Collection) new LottoNumber(Integer.parseInt(input))));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askWinningNumber();
        }
    }

    public int askBonusWinningNumber() {
        try {
            String input = printBonusWinningNumber();
            validateBlankAndEmptyInteger(input);
            return validateInteger(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return askBonusWinningNumber();
        }
    }

    public void validateLastCharacter(String input) {
        if (!Character.isDigit(getCharacterAtIndex(input, input.length() - 1))) {
            throw new IllegalArgumentException(END_OF_SENTENCE_CHARACTER);
        }
    }

    public void validateFirstCharacter(String input) {
        if (!Character.isDigit(getCharacterAtIndex(input, 0))) {
            throw new IllegalArgumentException(START_OF_SENTENCE_CHARACTER);
        }
    }

    private char getCharacterAtIndex(String input, int index) {
        if (index >= 0 && index < input.length()) {
            return input.charAt(index);
        }
        throw new IndexOutOfBoundsException(INDEX_OUT_OF_INPUT_LENGTH);
    }

    private String printWinningNumber() {
        System.out.println(ASK_WINNING_NUMBER_MESSAGE);
        return Console.readLine();
    }

    private String printBonusWinningNumber() {
        System.out.println(ASK_BONUS_WINNING_NUMBER_MESSAGE);
        return Console.readLine();
    }

    private String printHowManyPurchase() {
        System.out.println(ASK_PURCHASE_MESSAGE);
        return Console.readLine();
    }

    private int validateInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(PERMIT_ONLY_NUMBER);
        }
    }

    private void validateBlankAndEmptyInteger(String input) {
        if (input.isBlank() || input.isEmpty() || input.contains(" ")) {
            throw new IllegalArgumentException(NO_PERMIT_BLANK);
        }
    }

    private int validateNegativeIntegerAndZero(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException(NO_PERMIT_INPUT_LESS_ZERO);
        }
        return input;
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


}
