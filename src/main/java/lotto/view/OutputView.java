package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Result;
import lotto.domain.WinningCriteria;
import lotto.util.Constant;

import java.text.MessageFormat;
import java.util.List;

public class OutputView {

    public void printResult(Integer lottoNum){
        System.out.println(MessageFormat.format(Constant.LOTTO_AMOUNT, lottoNum));
    }

    public void printLottos(List<Lotto> lottos){
        lottos.forEach(lotto ->
            System.out.println(lotto.getNumbers().toString()));
    }

    public void printStatistics(Result result){
        WinningCriteria[] w = WinningCriteria.values();
        Integer[] rankNum = result.getRankNum();
        for(int i=0;i<5;i++){
            if(i==3){
                System.out.println(MessageFormat.format(Constant.WINNING_STATISTICS_BONUS, w[i].getMatch(), w[i].getReward(), rankNum[i]));
                continue;
            }
            System.out.println(MessageFormat.format(Constant.WINNING_STATISTICS, w[i].getMatch(), w[i].getReward(), rankNum[i]));
        }
    }
}
