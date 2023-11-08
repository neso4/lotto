package lotto.preprocessor;

import java.util.List;
import java.util.stream.Stream;
import lotto.util.ExceptionHandler;
import lotto.util.LottoConst;

public class WinningNumsPreprocessor extends Preprocessor<List<Integer>> {

    @Override
    public boolean isInvalid(String userInput) {
        return !(isEverythingNumber(userInput)
                && isValidWinningNumsSize(userInput)
                && isValidWinningNumsRange(userInput)
                && isNotDuplicated(userInput));
    }

    @Override
    public List<Integer> convert(String userInput) {
        return Stream.of(userInput.split(","))
                .map(num -> num.trim())
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    private boolean isValidWinningNumsRange(String userInput) {
        if (convert(userInput)
                .stream().filter(num -> num < LottoConst.FIRST_NUM || num > LottoConst.LAST_NUM)
                .count() > 0) {
            ExceptionHandler.handleException("당첨 번호는 "
                    + LottoConst.FIRST_NUM
                    + "~"
                    + LottoConst.LAST_NUM
                    + " 사이의 숫자로 입력해주세요.");
            return ExceptionHandler.EXCEPTION_OCCURED;
        }
        return ExceptionHandler.NO_EXCEPTION;
    }

    private boolean isEverythingNumber(String userInput) {
        try {
            convert(userInput);
        } catch (NumberFormatException e) {
            ExceptionHandler.handleException("당첨 번호로 숫자를 입력해주세요.");
            return ExceptionHandler.EXCEPTION_OCCURED;
        }
        return ExceptionHandler.NO_EXCEPTION;
    }

    private boolean isValidWinningNumsSize(String userInput) {
        if (convert(userInput).size() != LottoConst.LOTTO_SIZE) {
            ExceptionHandler.handleException(LottoConst.LOTTO_SIZE + "개의 당첨 번호를 입력해주세요.");
            return ExceptionHandler.EXCEPTION_OCCURED;
        }
        return ExceptionHandler.NO_EXCEPTION;
    }

    private boolean isNotDuplicated(String userInput) {
        if (convert(userInput).stream().distinct().count() != convert(userInput).size()) {
            ExceptionHandler.handleException("중복되지 않는 "
                    + LottoConst.LOTTO_SIZE
                    + "개의 숫자를 입력해주세요.");
            return ExceptionHandler.EXCEPTION_OCCURED;
        }
        return ExceptionHandler.NO_EXCEPTION;
    }
}
