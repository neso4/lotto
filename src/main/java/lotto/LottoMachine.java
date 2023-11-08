package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.enums.LottoInfo;
import lotto.enums.LottoPrize;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static lotto.Input.getNaturalNumber;
import static lotto.Input.getSplitNaturalNumberList;
import static lotto.Output.*;

public class LottoMachine {
    private final int MIN_NUMBER;
    private final int MAX_NUMBER;
    private final int NUMBERS_PER_LOTTO;
    private final int LOTTO_PRICE;

    public LottoMachine() {
        this.MIN_NUMBER = LottoInfo.MIN_NUMBER.getNumber();
        this.MAX_NUMBER = LottoInfo.MAX_NUMBER.getNumber();
        this.NUMBERS_PER_LOTTO = LottoInfo.NUMBERS_PER_LOTTO.getNumber();
        this.LOTTO_PRICE = LottoInfo.LOTTO_PRICE.getNumber();
    }

    public void run() {
        int lottoCount = setLottoCount();
        printLottoCount(lottoCount);
        List<List<Integer>> myLottos = getAllLotto(lottoCount);
        printLottos(myLottos);
        Lotto lotto = setWinningNumbers();
        int bonus = setBonusNumber(lotto);
        List<LottoResult> lottoResults = getCountingMatches(lotto, bonus, myLottos);
        TreeMap<LottoPrize, Integer> winningCount = countLottoPrize(lottoResults);
        printLottoResult(winningCount);
        double profit = calculateProfit(lottoCount, winningCount)*100;
        printProfit(profit);
    }

    public List<List<Integer>> getAllLotto(int count) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ret.add(getLotto());
        }
        return ret;
    }

    public List<LottoResult> getCountingMatches(Lotto lotto, int bonus, List<List<Integer>> myLottos) {
        List<LottoResult> count = new ArrayList<>();
        myLottos.forEach(i -> {
            count.add(new LottoResult(i, lotto, bonus));
        });
        return count;
    }

    public TreeMap<LottoPrize, Integer> countLottoPrize(List<LottoResult> lottoResults) {
        TreeMap<LottoPrize, Integer> count = new TreeMap<>();
        lottoResults.forEach(lottoResult -> {
            LottoPrize item = lottoResult.lottoResultToLottoPrize();
            count.put(item, count.getOrDefault(item, 0)+1);
        });
        return count;
    }

    public double calculateProfit(int lottoCount, TreeMap<LottoPrize, Integer> winningCount) {
        double totalPrice = lottoCount*LOTTO_PRICE;
        double ret = 0;
        for (LottoPrize lottoPrize: LottoPrize.values()) {
            long prizeMoney = lottoPrize.getPrizeMoney();
            ret += winningCount.getOrDefault(lottoPrize, 0)*prizeMoney;
        }
        return ret/totalPrice;
    }

    private int setLottoCount() {
        printRequestPurchaseAmount();
        while (true) {
            try {
                int number = getNaturalNumber();
                Validation.validateMultipleOfLottoPrice(number);
                return number/LOTTO_PRICE;
            } catch (IllegalArgumentException e) {
                printExceptionMessage(e);
            }
        }
    }

    private Lotto setWinningNumbers() {
        printRequestWinningNumbers();
        while (true) {
            try {
                List<Integer> numbers = getSplitNaturalNumberList(",");
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                printExceptionMessage(e);
            }
        }
    }

    private int setBonusNumber(Lotto lotto) {
        printRequestBonusNumber();
        while (true) {
            try {
                int number = getNaturalNumber();
                Validation.validateDuplicate(lotto, number);
                return number;
            } catch (IllegalArgumentException e){
                printExceptionMessage(e);
            }
        }
    }

    private List<Integer> getLotto() {
        List<Integer> ret = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, NUMBERS_PER_LOTTO);
        return ret.stream().sorted().toList();
    }
}
