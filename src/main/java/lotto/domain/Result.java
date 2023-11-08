package lotto.domain;

import lotto.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result {
    List<Integer> winningLotto;
    int bonusNumber;
    List<Lotto> purchasedLottos;
    List<Integer> matchCount;
    double profit;
    double profitability;

    public Result(Lotto lotto, int bonusNumber, List<Lotto> purchasedLottos) {
        this.winningLotto = lotto.getNumbers();
        this.bonusNumber = bonusNumber;
        this.purchasedLottos = purchasedLottos;
        countAllMatches();
        calculateProfit();
        calculateProfitMargin();
    }

    private void countAllMatches() {
        matchCount = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        for (Lotto lotto: purchasedLottos) {
            countMatches(lotto);
        }
    }

    private void countMatches(Lotto lotto) {
        List<Integer> purchased = lotto.getNumbers();
        int count = 0;
        for (int i = 0; i < purchased.size(); i++) {
            if (winningLotto.contains(purchased.get(i))) {
                count++;
            }
        }
        setMatchCount(count, purchased);
    }

    private void setMatchCount(int count, List<Integer> purchased) {
        if (count == 6) {
            matchCount.set(4, matchCount.get(4) + 1);
        }
        if (count == 5 && purchased.contains(bonusNumber)) {
            matchCount.set(3, matchCount.get(3) + 1);
        }
        if (count == 5 && !purchased.contains(bonusNumber)) {
            matchCount.set(2, matchCount.get(2) + 1);
        }
        if (count == 4) {
            matchCount.set(1, matchCount.get(1) + 1);
        }
        if (count == 3) {
            matchCount.set(0, matchCount.get(0) + 1);
        }
    }

    private void calculateProfit() {
        PrizeMoney[] prizeMonies = PrizeMoney.values();
        profit = 0;
        for (int i = 0; i < 5; i++) {
            profit += matchCount.get(i) * prizeMonies[i].getPrizeValue();
        }
    }

    private void calculateProfitMargin() {
        double payment = purchasedLottos.size() * Constants.LOTTO_TICKET_PRICE.getValue();
        profitability =  profit * 100 / payment;
    }

    public List<Integer> getMatchCount() {
        return matchCount;
    }

    public Double getProfitability() {
        return profitability;
    }
}
