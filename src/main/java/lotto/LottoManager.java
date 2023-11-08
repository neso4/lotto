package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.constant.WinningCondition;
import lotto.view.Input;
import lotto.view.Output;

import java.util.*;

import static lotto.util.Repeater.tryOnceMoreIfException;
import static lotto.util.Validator.validateBonusNumberDuplicate;
import static lotto.util.Validator.validateRangeOfNumbers;

public class LottoManager {
    public static final int NUMBER_OF_LOTTO_NUMBER = 6;
    public static final int THOUSAND = 1000;
    private final Output output = new Output();

    public List<Lotto> purchaseLottoTickets() {
        output.printEnteringAmountPrompt();
        int purchasedAmount = tryOnceMoreIfException(Input::readTotalPurchasedAmount);
        int purchasedQuantity = purchasedAmount / THOUSAND;

        return generateLottoTickets(purchasedQuantity);
    }

    public List<Lotto> generateLottoTickets(int quantity) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, NUMBER_OF_LOTTO_NUMBER);
            lottoTickets.add(new Lotto(numbers.stream().sorted().toList()));
        }
        return lottoTickets;
    }

    public void printLottoTickets(List<Lotto> tickets) {
        output.printPurchasedLottoTickets(tickets);
    }

    public void checkMyWinning(List<Lotto> myTickets) {
        Lotto winningNumbers = this.getWinningNumbers();
        int bonusNumber = this.getBonusNumber();
        validateBonusNumberDuplicate(winningNumbers, bonusNumber);
        validateRangeOfNumbers(winningNumbers, bonusNumber);

        Map<WinningCondition, Integer> result = getResultTable(myTickets, winningNumbers, bonusNumber);
        output.printWinningStatistics(result);
        output.printWinningRoR(calculateRoR(result, myTickets.size() * THOUSAND));
    }

    public Map<WinningCondition, Integer> getResultTable(List<Lotto> tickets, Lotto winning, int bonusNumber) {
        Map<WinningCondition, Integer> result = new HashMap<>();
        for (Lotto ticket : tickets) {
            WinningCondition tmpResult = checkWinningResult(ticket, winning, bonusNumber);
            result.put(tmpResult, result.getOrDefault(tmpResult, 0) + 1);
        }
        return result;
    }

    private Lotto getWinningNumbers() {
        output.printLottoWinningNumbersPrompt();
        return tryOnceMoreIfException(Input::readWinningNumbers);
    }

    public int getBonusNumber() {
        output.printLottoBonusNumberPrompt();
        return tryOnceMoreIfException(Input::readBonusNumber);
    }

    public WinningCondition checkWinningResult(Lotto myLotto, Lotto winningNumbers, int bonusNumber) {
        Set<Integer> mySet = new HashSet<>(myLotto.getNumbers());
        Set<Integer> lottoSet = new HashSet<>(winningNumbers.getNumbers());

        mySet.retainAll(lottoSet);
        int matchedCount = mySet.size();

        if (matchedCount == 6) {
            return WinningCondition.SIX_MATCH;
        }
        if (matchedCount == 5 && myLotto.getNumbers().contains(bonusNumber)) {
            return WinningCondition.FIVE_MATCH_WITH_BONUS;
        }
        if (matchedCount == 5) {
            return WinningCondition.FIVE_MATCH;
        }
        if (matchedCount == 3) {
            return WinningCondition.THREE_MATCH;
        }
        if (matchedCount == 4) {
            return WinningCondition.FOUR_MATCH;
        }
        return WinningCondition.NOTHING;
    }

    public double calculateRoR(Map<WinningCondition, Integer> result, int purchasedAmount) {
        int totalAmount = 0;
        for (WinningCondition condition : WinningCondition.values()) {
            totalAmount += condition.getPrize() * result.getOrDefault(condition, 0);
        }
        double RoR = ((double) totalAmount / purchasedAmount) * 100;
        return RoR;
    }
}
