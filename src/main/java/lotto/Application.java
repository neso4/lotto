package lotto;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        inputView.printPurchaseMoneyMessage();

        LottoController lottoController = new LottoController();
        lottoController.inputPurchaseMoney();
        lottoController.createLottos();
    }
}
