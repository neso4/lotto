package lotto.controller.calculation;

import lotto.lottoenum.LottoRanking;

import java.util.List;

public interface Calculation {
    public float getCalculation(List<LottoRanking> lottoRankings);
}
