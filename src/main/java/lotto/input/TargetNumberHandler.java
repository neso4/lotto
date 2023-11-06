package lotto.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.config.Constants;
import lotto.domain.Lotto;

public class TargetNumberHandler {
    private static final String INPUT_DELIMITER = ",";
    private static final String TARGET_INFO_MESSAGE = "정답인 6개의 로또번호를 입력해주세요.";
    private static final String TARGET_ERROR_MESSAGE = "[ERROR] 유효한 로또 정답 입력이 아닙니다.";
    private static final String BONUS_INFO_MESSAGE = "6개의 번호와 중복되지 않는 보너스번호를 입력해주세요.";
    private static final String BONUS_ERROR_MESSAGE = "[ERROR] 유효한 보너스 번호 입력이 아닙니다.";

    public Lotto setTargetLottoByInput() {
        System.out.println(TARGET_INFO_MESSAGE);
        Lotto lotto = handleInputToLotto();
        return lotto;
    }

    private Lotto handleInputToLotto() {
        Lotto target = null;
        boolean isInputInvalid = true;
        do {
            try {
                target = getValidTargetNumberInput();
                isInputInvalid = false;
            } catch (IllegalArgumentException e) {
                System.out.println(TARGET_ERROR_MESSAGE);
            }
        } while (isInputInvalid);
        return target;
    }

    private Lotto getValidTargetNumberInput() {
        String input = Console.readLine();
        String[] strings = input.split(INPUT_DELIMITER, Constants.LOTTO_SIZE);
        List<Integer> list = convertToIntegerList(strings);
        return new Lotto(list);
    }

    private List<Integer> convertToIntegerList(String[] split) {
        return Arrays.stream(split)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    public int setBonusByInput(Lotto target) {
        System.out.println(BONUS_INFO_MESSAGE);
        int bonus = handleInputToBonus(target);
        return bonus;
    }

    private int handleInputToBonus(Lotto target) {
        boolean isInputInvalid = true;
        int bonus = 0;
        do {
            try {
                int tmp = getValidBonusInput();
                validateBonus(tmp, target);
                bonus = tmp;
                isInputInvalid = false;
            } catch (IllegalArgumentException e) {
                System.out.println(BONUS_ERROR_MESSAGE);
            }
        } while (isInputInvalid);
        return bonus;
    }

    private int getValidBonusInput() {
        String input = Console.readLine();
        int tmp = Integer.parseInt(input);
        return tmp;
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
