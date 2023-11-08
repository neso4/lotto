package lotto.util;

import java.util.HashSet;
import java.util.List;

public class Validator {
    private final static int LOTTO_PRICE = 1000;
    private final static int LOTTO_PRICE_MIN = 0;
    private final static int LOTTO_NUM = 6;
    private final static int MIN_LOTTO_NUM = 1;
    private final static int MAX_LOTTO_NUM = 45;

    //구입 금액 예외처리
    public void check_InputMoney(String input) throws IllegalArgumentException {
        check_Empty(input);
        check_NotNumber(input);

        int money = Integer.parseInt(input);

        check_PositiveNumber(money);
        check_OverMinLottoPrice(money);
        check_NotDividedByPrice(money);
    }

    private void check_Empty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.IS_EMPTY.getMessage());
        }
    }

    private void check_NotNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_NUMBER.getMessage());
        }
    }

    private void check_NotDividedByPrice(int money) {
        if (money % LOTTO_PRICE != LOTTO_PRICE_MIN) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_DIVIDED_BY_PRICE.getMessage());
        }
    }

    private void check_PositiveNumber(int money) {
        if (money < LOTTO_PRICE_MIN) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_POSITIVE_NUMBER.getMessage());
        }
    }

    private void check_OverMinLottoPrice(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_OVER_MIN_PRICE.getMessage());
        }
    }

    //당첨 번호 예외처리
    public void check_InputWinLotto(String input) throws IllegalArgumentException {
        check_Empty(input);
        check_LastIndex(input);

        List<String> stringList = GameUtil.converseStringToStringList(input);
        check_LottoNumberInteger(stringList);

        List<Integer> integerList = GameUtil.converseStringListToIntegerList(stringList);
        check_LottoNumberSize(integerList);
        check_LottoNumberRange(integerList);
        check_LottoNumberUnique(integerList);
    }

    private void check_LastIndex(String input) {
        if (input.lastIndexOf(",") == input.length() - 1) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_LAST_INDEX.getMessage());
        }
    }

    private void check_LottoNumberInteger(List<String> input) {
        if (!isInteger(input)) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_NUMBER.getMessage());
        }
    }

    private boolean isInteger(List<String> input) {
        for (String num : input) {
            try {
                Integer.parseInt(num);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private void check_LottoNumberSize(List<Integer> input) {
        if (input.size() != LOTTO_NUM) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_LOTTO_SIZE.getMessage());
        }
    }

    private void check_LottoNumberRange(List<Integer> input) {
        if (!isAllValidLottoNumberRange(input)) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private static boolean isAllValidLottoNumberRange(List<Integer> input) {
        return input.stream()
                .allMatch(Validator::isValidLottoNumberRange);
    }

    private static boolean isValidLottoNumberRange(int number) {
        return (number >= MIN_LOTTO_NUM) && (number <= MAX_LOTTO_NUM);
    }

    private void check_LottoNumberUnique(List<Integer> input) {
        if (isDuplicate(input)) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_UNIQUE_LOTTO_NUMBER.getMessage());
        }
    }

    private static boolean isDuplicate(List<Integer> input) {
        HashSet<Integer> hashSet = new HashSet<>(input);
        return hashSet.size() != input.size();
    }

    //보너스번호 예외처리
    public void check_BonusNum(String input, List<Integer> winLotto) throws IllegalArgumentException {
        check_Empty(input);
        check_NotNumber(input);

        int bonusNum = Integer.parseInt(input);

        check_BonusNumRange(bonusNum);
        check_BonusNumUnique(bonusNum,winLotto);
    }

    private void check_BonusNumRange(int input) {
        if (!isValidLottoNumberRange(input)) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private void check_BonusNumUnique(int input, List<Integer> winLotto){
        HashSet<Integer> hashSet = new HashSet<>(winLotto);
        hashSet.add(input);
        if(hashSet.size()!=7){
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_UNIQUE_LOTTO_NUMBER.getMessage());
        }
    }
}
