package lotto.Service;

import java.util.List;
import lotto.Util.Splitter;
import lotto.Validator.ValidatorFactory;
import lotto.Validator.WinningNumberValidator;
import lotto.domain.WinningNumber;

/**
 * 로또에 필요한 값들을 변환해주는 클래스
 */
public class LottoInitService {

    /**
     * 구입 금액을 검증하고 숫자로 반환
     * @param input 사용자가 입력한 구입 금액
     * @return 정수로 변환된 구입 금액
     */
    public Integer inputAmountToNumber(String input) {
        String validatedInput = ValidatorFactory.forInputAmount().valid(input);

        return Integer.parseInt(validatedInput);
    }

    /**
     * 당첨 번호를 검증하고 숫자 리스트로 반환
     * @param input 사용자가 입력한 당첨 번호
     * @return 정수로 변환된 당첨 번호가 담긴 리스트
     */
    public List<Integer> inputWinningNumberToList(String input) {
        String validInput = WinningNumberValidator.vaildFormat(input);
        List<String> splitInputs = Splitter.splitString(validInput, ",");
        List<String> validInputs = ValidatorFactory.forInputWinningNumber().valid(splitInputs);

        return validInputs.stream()
                .map(Integer::parseInt)
                .toList();
    }

    /**
     * 보너스 번호를 검증하고 정수로 반환
     * @param input 사용자가 입력한 보너스 번호
     * @return 정수로 변환된 보너스 번호
     */
    public Integer inputBonusNumberToInteger(String input) {
        String validatedInput = ValidatorFactory.forInputBonusNumber().valid(input);

        return Integer.parseInt(validatedInput);
    }

    /**
     * 당첨 번호와 보너스 번호를 받아 객체로 WinningNumber 객체로 반환
     * @param winningNumbers 당첨 번호가 담긴 리스트
     * @param bonusNumber 보너스 번호
     * @return WinningNumber 객체
     */
    public WinningNumber getWinningNumber(List<Integer> winningNumbers, Integer bonusNumber) {
        return new WinningNumber(winningNumbers, bonusNumber);
    }
}
