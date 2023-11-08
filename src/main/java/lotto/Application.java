package lotto;

import domain.LottoGenerator;
import domain.LottoPrinter;
import domain.PurchaseInput;
import domain.WinningNumbers;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        PurchaseInput purchaseInput = new PurchaseInput();
        LottoGenerator lottoGenerator = new LottoGenerator();
        WinningNumbers winningNumbers = new WinningNumbers();

        int purchaseAmount = purchaseInput.getPurchaseAmount();
        List<Lotto> lottoNumbers = lottoGenerator.generateLottoNumbers(purchaseAmount);

        System.out.println("\n" + lottoNumbers.size() + "개를 구매했습니다.");
        LottoPrinter.printFormattedLottoNumbers(lottoNumbers);

        winningNumbers.inputWinningNumbers();
        winningNumbers.inputBonusNumber();

    }
}
