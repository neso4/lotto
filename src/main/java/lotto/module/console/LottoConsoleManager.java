package lotto.module.console;

import lotto.module.console.output.composer.ConsoleMessageComposer;
import lotto.module.domain.LottoProfit;
import lotto.module.domain.PurchaseAmount;
import lotto.module.lotto.Lotto;
import lotto.module.lotto.UserLottoTickets;
import lotto.module.result.LottoResult;

import java.util.List;

public class LottoConsoleManager {
    private final Console console;
    private final ConsoleMessageComposer consoleMessageComposer;

    private LottoConsoleManager(Console console, ConsoleMessageComposer consoleMessageComposer) {
        this.console = console;
        this.consoleMessageComposer = consoleMessageComposer;
    }

    public static LottoConsoleManager of(Console console, ConsoleMessageComposer consoleMessageComposer) {
        return new LottoConsoleManager(console, consoleMessageComposer);
    }

    public PurchaseAmount getPurchaseAmount() {
        int payment = console.getPurchaseAmount();

        return PurchaseAmount.newInstance(payment);
    }

    public void printPurchasedLottoTickets(UserLottoTickets purchasedLottoTickets) {
        String message = consoleMessageComposer.generatePurchasedLottoTicketMessage(purchasedLottoTickets);

        console.printPurchasedLottoTickets(message);
    }

    public Lotto getWinningLottoNumbers() {
        List<Integer> winningLottoNumbers = console.getWinningLottoNumbers();

        return new Lotto(winningLottoNumbers);
    }

    public int getLottoBonusNumber() {
        return console.getLottoBonusNumber();
    }

    public void printLottoWinningResult(LottoResult lottoResult, LottoProfit lottoProfit) {
        String lottoResultMessage = consoleMessageComposer.generateLottoResultMessage(lottoResult, lottoProfit);
        console.printLottoWinningResult(lottoResultMessage);
    }

    public void printErrorMessage(String errorMessage) {
        console.printErrorMessage(errorMessage);
    }

}
