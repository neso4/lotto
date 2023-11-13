package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.List;

public class Raffle {
    public enum LottoRank {
        FIFTH(3, 5000,0),
        FOURTH(4, 50000, 0),
        THIRD(5, 1500000, 0),
        SECOND(5, 30000000, 1), // 보너스 볼 일치
        FIRST(6, 2000000000, 0), 미당첨(0,0, 0);
        private int matchCount;
        private int prize;
        private int bonus;
        LottoRank(int matchCount, int prize, int bonus) {
            this.matchCount = matchCount;
            this.prize = prize;
            this.bonus = bonus;
        }
        public int getMatchCount() {
            return matchCount;
        }
        public int getPrize() {
            return prize;
        }
        public int getBonus(){return bonus;}
    }

    public Lotto makeLotto() {//무작위 로또하나를 만든다
        List<Integer> nums = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(nums);
    }
    private int[][] makeLottoes(int count){//입력 횟수만큼 로또를 만든다.
        int[][] lottodeck= new int[count][6];
        while(count>0){
            lottodeck[count-1] = makeLotto().getlotto().stream().mapToInt(i->i).toArray();
            count--;
        }
        return lottodeck;
    }
    public int[][] showLottes(Customer customer){//만들어진 로또를 보여준다.
        int price = customer.getPrice();
        int count = customer.calcount(price);
        int[][] lottodeck = makeLottoes(count);
        for (int[] lottoNumbers : lottodeck) {
            System.out.println(Arrays.toString(lottoNumbers));
        }
        return lottodeck;
    }
    public LottoRank rankLotto(Lotto lotto, Lotto winningLotto, int bonusNumber) {//몇등인지 반환한다.
        int matchCount = countMatch(lotto, winningLotto);
        boolean bonusMatch = lotto.getlotto().contains(bonusNumber);
        if (matchCount == 6 && lotto.equals(winningLotto)) {return LottoRank.FIRST;}
        if (matchCount == 5 && bonusMatch) {return LottoRank.SECOND;}
        if ((matchCount == 5)) {return LottoRank.THIRD;}
        if ((matchCount == 4)) {return LottoRank.FOURTH;}
        if ((matchCount == 3)) {return LottoRank.FIFTH;}
        return LottoRank.미당첨;
    }
    private int countMatch(Lotto lotto, Lotto winningLotto) {//filter를 이용해 몇개나 로또랑 일치하는지 확인한다.
        return (int) lotto.getlotto().stream()
                .filter(winningLotto.getlotto()::contains)
                .count();
    }
    public String calculateReturnRate(List<LottoRank> ranks, int inputMoney) {//수익률을 계산한다.
        int totalPrize = ranks.stream()
                .mapToInt(LottoRank::getPrize)
                .sum();
        double returnRate = (double) totalPrize / inputMoney * 100;
        double roundedRate = Math.round(returnRate * 1000) / 1000.0;
        return "총 수익률은 " + roundedRate + "%입니다.";
    }
}
