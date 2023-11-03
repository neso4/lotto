package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.util.LottoFactory;
import lotto.view.input.LottoBuyInputView;
import lotto.view.output.LottoBuyOutputView;

import java.util.List;
import java.util.stream.Stream;

public class LottoController {

    private static final int DENOMINATION = 1000;

    private final LottoBuyInputView lottoBuyInputView;
    private final LottoBuyOutputView lottoBuyOutputView;

    public LottoController(LottoBuyOutputView lottoBuyOutputView) {
        //로또 구매 단위가 변경되면 LottoBuyInputView의 영향(test등)을 파악하기 위해서 매개변수 삭제
        this.lottoBuyInputView = new LottoBuyInputView(DENOMINATION);
        this.lottoBuyOutputView = lottoBuyOutputView;
    }

    public void printBuyLottos(Lottos lottos) {
        lottoBuyOutputView.printPurchaseLottoCountMessage(lottos);
        lottoBuyOutputView.printAllLottoNumbers(lottos);
    }

    public Lottos buyLottos() {
        int amount = lottoBuyInputView.requestLottoPurchaseAmount();
        return new Lottos(createLottos(amount));
    }

    private List<Lotto> createLottos(int amount) {
        return Stream.generate(LottoFactory::getLotto)
                .limit(amount / DENOMINATION)
                .toList();
    }

    public Lotto requestWinningLotto() {
        List<Integer> winningLottoNumbers = lottoBuyInputView.requestWinningLottoNumbers();
        return LottoFactory.getLotto(winningLottoNumbers);
    }

}
