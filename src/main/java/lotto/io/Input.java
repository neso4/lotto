package lotto.io;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.BonusNumber;
import lotto.domain.LottoAnswer;
import lotto.domain.Money;

public class Input {
    public Money setMoney() {
        return new Money(Console.readLine());
    }

    public LottoAnswer setLottoAnswer() {
        return new LottoAnswer(Console.readLine());
    }

    public BonusNumber setBonusNumber() {
        return new BonusNumber(Console.readLine());
    }
}
