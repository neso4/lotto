package lotto;

public class Application {
    public static void main(String[] args) {
        LottoManager lottoManager = new LottoManager();
        lottoManager.purchaseLottos();
        LottoDrawMachine lottoDrawMachine = new LottoDrawMachine();
        lottoDrawMachine.drawNumberManual();
        lottoManager.calculateMatchResults(lottoDrawMachine);
        lottoManager.printWinningReport();
    }
}
