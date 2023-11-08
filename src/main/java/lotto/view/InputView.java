package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import lotto.converter.InputConverter;
import lotto.validator.BonusNumberValidator;
import lotto.validator.LottoMoneyValidator;
import lotto.validator.LottoNumberValidator;

public class InputView {
    public static Integer getLottoMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        String lottoMoneyInput = Console.readLine();
        try {
            LottoMoneyValidator.validate(lottoMoneyInput);
        } catch (IllegalArgumentException e) {
            return getLottoMoney();
        }

        return InputConverter.convertLottoMoney(lottoMoneyInput);
    }

    public static List<Integer> getWinningLotto() {
        System.out.println("당첨 번호를 입력해 주세요.");

        String lottoNumberInput = Console.readLine();
        try {
            LottoNumberValidator.validate(lottoNumberInput);
        } catch (IllegalArgumentException e) {
            return getWinningLotto();
        }

        return InputConverter.convertWinningLotto(lottoNumberInput);
    }

    public static Integer getBonusNumber(List<Integer> winningLotto) {
        System.out.println("보너스 번호를 입력해 주세요.");

        String bonusNumberInput = Console.readLine();
        try {
            BonusNumberValidator.validate(bonusNumberInput, winningLotto);
        } catch (IllegalArgumentException e) {
            return getBonusNumber(winningLotto);
        }

        return InputConverter.convertBonusNumber(bonusNumberInput);
    }


}
