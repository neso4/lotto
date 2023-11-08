package lotto.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.config.Constants;
import lotto.domain.Lotto;

public class TargetNumberHandler {
    private static final String INPUT_DELIMITER = ",";

    public Lotto setTargetLottoByInput() {
        System.out.println(RequestPrompt.TARGET_REQUEST.message);
        Lotto target = handleInputToLotto();
        return target;
    }

    private Lotto handleInputToLotto() {
        Lotto target = null;
        boolean isInputInvalid = true;
        do {
            try {
                target = parseInputToLotto();
                isInputInvalid = false;
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorPrompt.TARGET_ERROR.message);
            }
        } while (isInputInvalid);
        return target;
    }

    private Lotto parseInputToLotto() {
        String input = Console.readLine();
        String[] strings = input.split(INPUT_DELIMITER, Constants.LOTTO_SIZE);
        List<Integer> numbers = convertToIntegerList(strings);
        return new Lotto(numbers);
    }

    private List<Integer> convertToIntegerList(String[] strings) {
        return Arrays.stream(strings)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    public int setBonusByInput(Lotto target) {
        System.out.println(RequestPrompt.BONUS_REQUEST.message);
        int bonus = handleInputToBonus(target);
        return bonus;
    }

    private int handleInputToBonus(Lotto target) {
        int bonus = 0;
        boolean isInputInvalid = true;
        do {
            try {
                bonus = parseInputToBonus(target);
                isInputInvalid = false;
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorPrompt.BONUS_ERROR.message);
            }
        } while (isInputInvalid);
        return bonus;
    }

    private int parseInputToBonus(Lotto target) {
        String input = Console.readLine();
        int bonus = Integer.parseInt(input.trim());
        validateBonus(bonus, target);
        return bonus;
    }

    private void validateBonus(int bonus, Lotto target) {
        if (target.contains(bonus)) {
            throw new IllegalArgumentException();
        }

        if (bonus < Constants.LOTTO_NUMBER_MIN_RANGE || bonus > Constants.LOTTO_NUMBER_MAX_RANGE) {
            throw new IllegalArgumentException();
        }
    }
}
