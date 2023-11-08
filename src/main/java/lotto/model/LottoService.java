package lotto.model;

import static lotto.util.Utils.*;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoService {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final String LOTTO_SIZE_ERROR_MESSAGE = "[ERROR] 로또 번호는 6개만 가능합니다.";
    private static final String LOTTO_DUPLICATE_MESSAGE = "[ERROR] 로또 번호는 중복될 수 없습니다.";

    private int bonusNumber;

    public LottoService() {

    }

    public static void validateLottoNumber(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateLottoDuplicate(numbers);
    }

    public static List<Integer> generateLottoNumber() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        validateLottoNumber(numbers);
        return numbers;
    }

    public static List<Integer> validateInputLottoNumber(String input) { // 1,2,3,4,5,6
        List<Integer> numbers = makeStringToList(input);
        validateLottoNumber(numbers);
        return numbers;
    }

    public static int validateBonusNumber(String input) {
        return Integer.parseInt(input);
    }

    private static List<Integer> makeStringToList(String input) {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (String str : splitByComma(input)) {
            lottoNumbers.add(parseInt(str));
        }
        return lottoNumbers;
    }

    private static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size()!= LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR_MESSAGE);
        }
    }

    private static void validateLottoDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<Integer>(numbers);
        if(set.size()!=LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(LOTTO_DUPLICATE_MESSAGE);
        }
    }
}
