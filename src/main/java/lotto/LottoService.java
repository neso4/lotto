package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoService {

    /**
     * 로또 번호 생성
     */
    public List<Integer> generateLottoNumbers() {
        List<Integer> lotto = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        Collections.sort(lotto);
        return lotto;
    }

    /**
     * 로또 당첨 번호 생성
     */
    public List<Integer> generateLottoWinningNumbers(String winningNumbers) {
        String[] winningNumber = winningNumbers.split(",");
        List<Integer> lottoWinningNumbers = new ArrayList<>();
        try {
            lottoWinningNumbers = Arrays.stream(winningNumber)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 입력값이 숫자가 아닙니다.");
            System.exit(0);
        }
        return lottoWinningNumbers;
    }

    /**
     * 당첨 내역 계산하기
     */
    public Rank[] calculateWinningDetails(LottoRank[] lottoRanks, List<Integer> winningNumbers, int bonusNumber) {
        Rank[] ranks = {Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST};
        for (int i = 0; i < lottoRanks.length; ++i) {
            lottoRanks[i].setRank(winningNumbers, bonusNumber);
            checkRank(ranks, lottoRanks[i]);
        }
        return ranks;
    }

    /**
     * 몇 번째 rank인지 검사
     */
    public void checkRank(Rank[] ranks, LottoRank lottoRanks) {
        for (int i = 0; i < ranks.length; ++i) {
            if (ranks[i].getRank().equals(lottoRanks.getRank().getRank())) {
                ranks[i].setAmount();
            }
        }
    }

    /**
     * 수익률 계산
     */
    public double calculateProfitRate(Rank[] ranks, int amount) {
        double profitRate = 0.0;
        double sum = 0.0;
        for (Rank rank : ranks) {
            sum += rank.getMoney() * rank.getAmount();
        }
        profitRate = sum / amount;
        return profitRate * 100;
    }

}
