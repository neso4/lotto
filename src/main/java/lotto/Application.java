package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.controller.LottoMachine;
import lotto.input.PriceInputHandler;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        LottoMachine lottoMachine = new LottoMachine();
        lottoMachine.run();
    }
}
