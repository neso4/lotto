package lotto.views;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Prize;
import lotto.global.Utils;

public class OutputView {
    public static final String PURCHASE_LOTTO_OUTPUT = "개를 구매했습니다.";

    public void lottoQuantityAndNumber(List<Lotto> publishedLotto) {
        System.out.println(publishedLotto.size()+PURCHASE_LOTTO_OUTPUT);
        for (Lotto lotto : publishedLotto) {
            List<Integer> sortedNumbers = lotto.getNumbers();
            StringBuilder stringBuilder = new StringBuilder("[");
            for (Integer number : sortedNumbers) {
                stringBuilder.append(number);
                stringBuilder.append(", ");
            }
            int length = stringBuilder.length();
            stringBuilder.delete(length-2,length);
            stringBuilder.append("]");
            System.out.println(stringBuilder.toString());
        }
    }

    public void winningRecord(Map<Prize,Integer> lotteryResult){
        StringBuilder outputBuilder = new StringBuilder("당첨 통계\n"+"---\n");
        for (Prize prize : lotteryResult.keySet()) {
            int matchCount = prize.getMatchCount();
            String reward = Utils.NUMBER_FORMAT_US.format(prize.getReward());
            int count = lotteryResult.get(prize);
            outputBuilder.append(matchCount);
            outputBuilder.append("개 일치");
            if(Prize.SECOND_REWARD.equals(prize)){
                outputBuilder.append(", 보너스 볼 일치");
            }
            outputBuilder.append(" (");
            outputBuilder.append(reward);
            outputBuilder.append("원) - ");
            outputBuilder.append(count);
            outputBuilder.append("개\n");
        }
        System.out.println(outputBuilder.toString());
    }

    public void rewardRatioRecord(double rewardRatio){
        StringBuilder outputBuilder = new StringBuilder("총 수익률은 ");
        outputBuilder.append(rewardRatio);
        outputBuilder.append("%입니다.");
        System.out.println(outputBuilder.toString());
    }
}
