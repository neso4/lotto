package lotto;
/*
    로또 추첨결과 관련 클래스
    setResultList: 결과리스트 생성 매소드
    judge: 등수 판별 매소드
    calRerutnRate: 수익률 계산 매소드
    showResult: 결과 출력 매소드
*/
import java.text.DecimalFormat;

public class Lottery {
    private final int[] prices = { 5_000, 50_000, 1_500_000, 30_000_000, 2_000_000_000 };
    private final int[] target = { 3, 4, 5, 5, 6 };
    private final int[] Lottery_result;
    private final float returnRate;

    public Lottery(UserLotto user, int money, Lotto winLotto, int bonus) {
        this.Lottery_result = setResultList(winLotto, bonus, user);
        this.returnRate = calRerutnRate(money);
    }

    private int[] setResultList(Lotto winLotto, int bonus, UserLotto user) {
        int[] res = { 0, 0, 0, 0, 0 };
        for (Lotto L : user.getLotto()) {
            int grade = judge(winLotto, L, bonus);
            if (grade >= 0)
                res[grade]++;
        }
        return res;
    }

    private int judge(Lotto winLotto, Lotto userLotto, int bonus) {
        int count = winLotto.countMatch(userLotto) - 3;
        if (count == 2 && winLotto.lottoContains(bonus))
            return count + 1;
        if (count == 3)
            return count + 1;
        return count;
    }

    private float calRerutnRate(int money) {
        float profit = 0;
        for (int i = 0; i < Lottery_result.length; i++) {
            profit += Lottery_result[i] * prices[i];
        }
        float tmp = Math.round(profit / money * 1000);
        return tmp / 10;
    }

    public void showResult() {
        DecimalFormat df = new DecimalFormat("###,###");
        int i = 0;
        System.out.println(
                "당첨 통계"
                        + String.format("%d개 일치 (%s원) - %d개\n", target[i], df.format(prices[i]), Lottery_result[i++])
                        + String.format("%d개 일치 (%s원) - %d개\n", target[i], df.format(prices[i]), Lottery_result[i++])
                        + String.format("%d개 일치 (%s원) - %d개\n", target[i], df.format(prices[i]), Lottery_result[i++])
                        + String.format("%d개 일치, 보너스 볼 일치 (%s원) - %d개\n", target[i], df.format(prices[i]), Lottery_result[i++])
                        + String.format("%d개 일치 (%s원) - %d개", target[i], df.format(prices[i]), Lottery_result[i++]));
        System.out.println(String.format("총 수익률은 %.1f%%입니다.", returnRate));
    }
}
