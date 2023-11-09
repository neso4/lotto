package output;

import constant.Reward;
import lotto.Lotto;
import lotto.WinningNumbersFactory;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static constant.LottoValue.*;
import static constant.Reward.*;
import static constant.Reward.REWARDS;
import static output.OutputMessage.*;

public class Performance {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
    private List<Lotto> lottos;
    private Lotto winningLotto;

    public Performance(List<Lotto> lottos, Lotto winningLotto){
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public void result(){
        List<Integer> performance = match(lottos, winningLotto);
        statistics(performance);
        rateOfReturn(setResult(performance));
    }

    private List<Integer> match(List<Lotto> lottos, Lotto winningLotto){
        List<Integer> performance = new ArrayList<>();
        WinningNumbersFactory setter = new WinningNumbersFactory();
        for (Lotto lotto:lottos) {
            int matchCount = lotto.match(winningLotto);
            if(matchCount == STATISTICS_FIVE_SAME_WITHOUT_BONUS.value()){
                bonuscheck(lotto, setter.getBonus(), performance);
                continue;
            }
            if(matchCount == SIX.getSameCount()){
                performance.add(STATISTICS_SIX_SAME.value());
                continue;
            }
            performance.add(matchCount);
        }
        return performance;
    }

    private void bonuscheck(Lotto lotto, int bonus, List<Integer> performance) {
        if(lotto.containSame(bonus)) {
            performance.add(SIX.getSameCount());
            return;
        }
        performance.add(FIVE.getSameCount());
    }

    private void statistics(List<Integer> performance) {
        System.out.println(OUTPUT_STATISTICS_START);
        AtomicInteger num = new AtomicInteger(THREE.getSameCount());
        Arrays.stream(REWARDS)
                .forEach(reward -> printOutput(num.getAndIncrement(), reward, performance));
    }


    private void printOutput(int num, Reward reward, List<Integer> performance){
        //5+보너스
        if(num == STATISTICS_FIVE_SAME_WITH_BONUS.value()) {
            System.out.printf(OUTPUT_MESSAGE_WITH_BONUS.message(), reward.getSameCount(), DECIMAL_FORMAT.format(reward.getReward()), Collections.frequency(performance, num));
            return;
        }
        System.out.printf(OUTPUT_MESSAGE_WITHOUT_BONUS.message(), reward.getSameCount(), DECIMAL_FORMAT.format(reward.getReward()), Collections.frequency(performance, num));
    }

    private int setResult(List<Integer> performance) {
        int totalRevenue = 0;
        int i=3;
        for(Reward reward : REWARDS){
            if(reward == FIVE_WITH_BONUS || reward == SIX){
                totalRevenue += reward.getReward() * Collections.frequency(performance, i);
                i++;
                continue;
            }
            totalRevenue += reward.getReward() * Collections.frequency(performance, i);
            i++;
        }
        return totalRevenue;
    }

    private void rateOfReturn(int totalRevenue) {
        float rate = (float)totalRevenue / lottos.size() / 10;
        System.out.printf(PRINT_RATE_OF_RETURN.message(), String.format(OUTPUT_RATE_OF_RETURN.message(),rate));
    }
}
