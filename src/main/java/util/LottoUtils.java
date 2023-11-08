package util;

import consts.LottoConsts;
import exception.WrongLottoSizeException;
import exception.WrongRangeLottoNumberException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoUtils {
    public static List<Integer> lottoNumbersParser(String inputLottoNumbers) {
        List<Integer> numbers = Stream.of(inputLottoNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        validateLotto(numbers);

        return numbers;
    }

    public static Integer bonusNumberParser(String inputBonusNumber) {
        Integer bonusNumber = Integer.parseInt(inputBonusNumber);
        validateBonusNumber(bonusNumber);
        return bonusNumber;
    }

    private static void validateLotto(List<Integer> numbers) {
        validateSize(numbers);
        validateNumbersRange(numbers);
    }

    private static void validateBonusNumber(Integer bonusNumber) {
        validateRange(bonusNumber);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LottoConsts.LOTTO_SIZE) {
            throw new WrongLottoSizeException();
        }
    }

    private static void validateNumbersRange(List<Integer> numbers) {
        numbers.forEach(LottoUtils::validateRange);
    }

    private static void validateRange(Integer number) {
        if (!(LottoConsts.MIN_RANGE <= number && number <= LottoConsts.MAX_RANGE)) {
            throw new WrongRangeLottoNumberException();
        }
    }
}