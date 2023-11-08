package lotto.view;

import lotto.LottoService;

public class InputView {
    private static final LottoService lottoService = new LottoService();

    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = lottoService.inputPurchaseAmount();
        System.out.println();
        return purchaseAmount;
    }

}
