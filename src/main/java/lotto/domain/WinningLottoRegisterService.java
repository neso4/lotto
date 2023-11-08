package lotto.domain;

import java.util.List;
import lotto.utils.ParseUtil;
import lotto.utils.StringUtil;
import lotto.utils.ValidationUtil;

public class WinningLottoRegisterService {
    private WinningLotto winningLotto;

    public WinningLottoRegisterService() {
        this.winningLotto = new WinningLotto();
    }

    public WinningLotto registerNumbers(String numbers) {
        validateNumbers(numbers);
        winningLotto.saveNumbers(parseNumbers(numbers));
        return winningLotto;
    }

    public WinningLotto registerBonusNumber(String bonusNumber) {
        validateBonusNumber(bonusNumber);
        winningLotto.saveBonusNumber(parseBonusNumber(bonusNumber));
        return winningLotto;
    }

    private void validateBonusNumber(String bonusNumber) {
        ValidationUtil.validateIsDigit(bonusNumber);
    }

    private void validateNumbers(String numbers) {
        ValidationUtil.validateIsAllDigit(StringUtil.splitByCommas(numbers));
    }

    private List<Integer> parseNumbers(String numbers) {
        return ParseUtil.parseNumbers(numbers);
    }

    private int parseBonusNumber(String bonusNumber) {
        return ParseUtil.parseNumber(bonusNumber);
    }
}
