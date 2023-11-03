package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.util.Validator;
import lotto.util.converter.ConverterHolder;

public class InputView {

    private static final String LOTTO_MONEY_INPUT = "구입금액을 입력해 주세요.";
    private static final String ANSWER_LOTTO_INPUT = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT = "보너스 번호를 입력해 주세요.";

    public LottoMoney getLottoMoney() {
        System.out.println(LOTTO_MONEY_INPUT);
        String input = Console.readLine();
        try {
            return ConverterHolder.convert(input, LottoMoney.class);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getLottoMoney();
        }
    }

    public Lotto getAnswerLotto() {
        System.out.println(ANSWER_LOTTO_INPUT);
        String input = Console.readLine();
        try {
            return ConverterHolder.convert(input, Lotto.class);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getAnswerLotto();
        }
    }

    public BonusNumber getBonusNumber(Lotto answerNumber) {
        System.out.println(BONUS_NUMBER_INPUT);
        String input = Console.readLine();
        try {
            BonusNumber bonusNumber = ConverterHolder.convert(input, BonusNumber.class);
            Validator.checkAlreadyContains(answerNumber, bonusNumber);
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumber(answerNumber);
        }
    }

}
