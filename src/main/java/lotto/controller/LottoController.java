package lotto.controller;

import lotto.constance.PrintConst;
import lotto.exceptionhandler.ExceptionHandler;
import lotto.exceptionhandler.RetryExceptionHandler;
import lotto.model.Lottos;
import lotto.ui.Reader;
import lotto.ui.Writer;
import lotto.util.lottogenerator.LottoGenerator;
import lotto.util.lottogenerator.RandomLottoGenerator;

public class LottoController {

    ExceptionHandler retryHandler = new RetryExceptionHandler();
    LottoGenerator lottoGenerator = new RandomLottoGenerator();

    //TODO 1. 로또 구매
    public void purchaseLotto() {
        int money = retryHandler.getResult(
                () -> {
                    Writer.printGuide(PrintConst.GUIDE_PURCHASE);
                    return Reader.getMoney();
                });

        Lottos lottos = lottoGenerator.purchase(money);
        Writer.printModelsInList(lottos.getLottosDTO());
    }

    //TODO 3. 로또 6자리 입력

    //TODO 4. 보너스 번호 입력

    //TODO 5. 결과 생성 및 처리
}
