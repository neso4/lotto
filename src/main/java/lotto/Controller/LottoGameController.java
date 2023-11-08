package lotto.Controller;

import java.util.List;
import lotto.Model.BonusNumber;
import lotto.Model.CalculateProfits;
import lotto.Model.Lotto;
import lotto.Model.LottoMatch;
import lotto.Model.LottoPrize;
import lotto.Model.RandomLottos;
import lotto.Model.TicketsAmount;
import lotto.View.OuputView;

public class LottoGameController {

    private TicketsAmount ticketsAmount;
    private Lotto lotto;
    private BonusNumber bonusNumber;
    private List<List<Integer>> randomLottos;

    public void gameStart() {
        initialize();
        start(randomLottos);
    }

    private void initialize() {
        ticketsAmount = new TicketsAmount(InputController.inputTicketsAmount());
        OuputView.printNumOfTicket(ticketsAmount.getTickets());
        randomLottos = RandomLottos.SetRandomLottos(ticketsAmount.getTickets()); // 랜덤패
        RandomLottos.printLottoNumbers(randomLottos);
        lotto = new Lotto(InputController.inputWinningNumbers());
        List<Integer> winningNumbers = lotto.getNumbers();// 당첨패
        bonusNumber = new BonusNumber(InputController.inputBonusNumber(), winningNumbers);
    }

    private void start(List<List<Integer>> randomLottos) {
        List<Integer> countWinnings = LottoMatch.countMatchingNumbers(randomLottos,
                lotto.getNumbers(), bonusNumber.getBonus());
        resultOfGame(countWinnings);
        CalculateProfits.calculate(countWinnings, ticketsAmount.getTicketsPrice());
    }
    private void resultOfGame(List<Integer> countWinnings) {
        OuputView.printResultStr();
        for (LottoPrize prize : LottoPrize.values()) {
            if (prize != LottoPrize.FAIL) {
                int prizeCount = countWinnings.get(prize.ordinal());
                OuputView.printResultOfGame(prize.getDescription(), prizeCount);
            }
        }
    }
}
