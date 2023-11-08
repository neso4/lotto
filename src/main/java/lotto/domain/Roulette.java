package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Roulette {
    private WinningLotto winningLotto;
    private Lottos lottos;
    private Map<RouletteResult, Integer> result;

    public Roulette(WinningLotto winningLotto, Lottos lottos) {
        this.winningLotto = winningLotto;
        this.lottos = lottos;
        result = createResult();
    }

    public Map<RouletteResult, Integer> createResult() {
        result = new HashMap<>();
        Arrays.stream(RouletteResult.values()).forEach(rouletteResult -> result.put(rouletteResult, 0));
        return result;
    }

    public static Roulette of(WinningLotto winningLotto, Lottos lottos) {
        return new Roulette(winningLotto, lottos);
    }

    public RouletteResult matchWinningNumbers(Lotto lotto) {
        int count = lotto.matchCount(winningLotto.getNumbers());
        boolean isBonusNumber = lotto.isContainNumber(winningLotto.getBonusNumber());
        return RouletteResult.findLottoCompensation(count, isBonusNumber);
    }

    public void getRouletteLottos() {
        for (Lotto lotto : lottos.getLottos()) {
            RouletteResult rouletteResult = matchWinningNumbers(lotto);
            getRouletteResultCount(rouletteResult);
        }
    }

    public void getRouletteResultCount(RouletteResult rouletteCompensation) {
        result.put(rouletteCompensation, result.get(rouletteCompensation) + 1);
    }

    public int getWinningMoney() {
        return result.keySet().stream()
                .mapToInt(rouletteResult -> rouletteResult.getPrice() * result.get(rouletteResult))
                .sum();
    }

    public double getRateOfResult(LottoPurchase lottoPurchase) {
        int winningMoney = getWinningMoney();
        return lottoPurchase.getRate(winningMoney);
    }

    public Map<RouletteResult, Integer> getResult() {
        return result;
    }
}
