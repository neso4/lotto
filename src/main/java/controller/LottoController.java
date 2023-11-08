package controller;

import java.util.ArrayList;
import java.util.List;

import lotto.Lotto;
import lotto.LottoMoney;
import lotto.LottoWinner;
import lotto.LuckeyLotto;
import view.OutputView;

public class LottoController {
    private static final int REWARD_SIZE = 5;
    private static final int LOTTO_BONUS_NUMBER_INDEX = 5;
    private static final int MINIMUM_LOTTO_NUMBER = 2;
    private static final int[] REWARD = { 5000, 50000, 1500000, 30000000, 2000000000 };

    LuckeyLotto luckeyLotto;
    LottoMoney lottoMoney;
    Lotto lotto;
    LottoWinner lottoWinner = new LottoWinner();
    OutputView outputView = new OutputView();
    int lottoWinnerCount;
    int[] lottoWinners = new int[REWARD_SIZE];

    public LottoController(Lotto lotto, LottoMoney lottoMoney, LuckeyLotto luckeyLotto) {
        this.lotto = lotto;
        this.lottoMoney = lottoMoney;
        this.luckeyLotto = luckeyLotto;
    }

    public void playGame(List<List<Integer>> lottonumbers) {
        outputView.printLottoResultMessage();
        decideLottoWinner(lottonumbers);
        infoLottoWinner();
        double revenuePercentage = decideRevenuePercentage();
        outputView.printRevenuePercentage(revenuePercentage);
    }

    private void decideLottoWinner(List<List<Integer>> randomlottoNumbers) {
        for (List<Integer> lottoNumbers : randomlottoNumbers) {
            lottoWinnerCount = getLottoWinnerCount(lottoNumbers);
            increaseLottoWinnerCount(lottoNumbers, lottoWinnerCount);
        }
    }

    private int getLottoWinnerCount(List<Integer> lottoNumbers) {
        List<Integer> lottoCounts = new ArrayList<>(lottoNumbers);
        lottoCounts.retainAll(lotto.getNumbers());

        return lottoCounts.size();
    }

    private void increaseLottoWinnerCount(List<Integer> lottoNumbers, int lottoWinnerCount) {
        if (lottoWinnerCount == LOTTO_BONUS_NUMBER_INDEX && lottoNumbers.contains(luckeyLotto.getLuckeyBonusNumber())) {
            lottoWinner.updateWinnerAndPrize(2);
        } else if (lottoWinnerCount == 6) {
            lottoWinner.updateWinnerAndPrize(1);
        } else if (lottoWinnerCount > MINIMUM_LOTTO_NUMBER) {
            lottoWinner.updateWinnerAndPrize(8 - lottoWinnerCount);
        }
    }

    private void infoLottoWinner() {
        for (int rank = 6; rank > 0; rank--) {
            lottoWinner.printLottoResult(rank);
        }
    }

    private double decideRevenuePercentage() {
        double investRevenue = getInvestRevenue();
        double revenuePercentage = investRevenue / lottoMoney.getLottoMoney() * 100;
        return revenuePercentage;
    }

    private double getInvestRevenue() {
        double InvestRevenue = 0.0;
        for (int i = 0; i < lottoWinners.length; i++) {
            InvestRevenue += lottoWinners[i] * REWARD[i];
        }
        return InvestRevenue;
    }
}
