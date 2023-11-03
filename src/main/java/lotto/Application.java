package lotto;

import lotto.controller.LottoController;
import lotto.domain.lotto.Lottos;
import lotto.view.output.LottoBuyOutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        LottoController lottoController = new LottoController(new LottoBuyOutputView());
        Lottos lottos = lottoController.buyLottos();
        lottoController.printBuyLottos(lottos);
    }
}
