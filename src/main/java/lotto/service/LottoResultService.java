package lotto.service;

import lotto.constants.Value;
import lotto.domain.*;

import java.text.DecimalFormat;
import java.util.*;

public class LottoResultService {

    private WinningLotto winningLotto;
    private Map<Rank, Integer> lottoResults = new LinkedHashMap<>();

    public LottoResultService() {
        lottoResults.put(Rank.FIFTH, 0);
        lottoResults.put(Rank.FOURTH, 0);
        lottoResults.put(Rank.THIRD, 0);
        lottoResults.put(Rank.SECOND, 0);
        lottoResults.put(Rank.FIRST, 0);
    }

    public void test() {
        Rank[] ranks = Rank.values();
        for (Rank rank : ranks) {
            System.out.println("카운트 : " + rank.getCount());
            System.out.println("금액 : " + rank.getPrize());
            System.out.println(lottoResults.get(rank));
        }
    }

    public void showResult() {
        List<Rank> ranks = new ArrayList<>(lottoResults.keySet().stream().toList());

        for (Rank rank : ranks) {
            System.out.println(getResult(rank));
        }
    }

    private String getResult(Rank rank) {
        DecimalFormat format = new DecimalFormat("#,###");

        if (rank == Rank.SECOND) {
            return rank.getCount() + "개 일치, 보너스 볼 일치 (" + format.format(Value.LOTTO_2_RANK_PRIZE_MONEY) + "원) - " +
                    lottoResults.get(rank) + "개";
        }
        return rank.getCount() + "개 일치 (" + format.format(rank.getPrize()) + "원) - " +
                lottoResults.get(rank) + "개";
    }

    public void setWinningLotto(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public void calculateLottoResult(List<Lotto> userLottos) {
        for (Lotto userLotto : userLottos) {
            Rank rank = compareUserLottoAndWinningLotto(userLotto.getNumbers());
            if (lottoResults.get(rank) != null) {
                lottoResults.put(rank, lottoResults.get(rank) + 1);
            }
        }
    }

    private Rank compareUserLottoAndWinningLotto(List<Integer> userLottoNumbers) {

        int count = getSameNumberCount(userLottoNumbers);

        if (count == 5 && isContainBonusNumber(userLottoNumbers)) {
            return Rank.SECOND;
        }

        return Rank.values()[count];
    }

    private int getSameNumberCount(List<Integer> userLottoNumbers) {
        List<Integer> winningLottoNumbers = winningLotto.getNumbers();

        int count = 0;
        for (int userLottoNumber : userLottoNumbers) {
            if (winningLottoNumbers.contains(userLottoNumber)) {
                count++;
            }
        }

        return count;
    }

    private boolean isContainBonusNumber(List<Integer> userLottoNumbers) {
        int winningLottoBonusNumber = winningLotto.getBonusNumber();

        return userLottoNumbers.contains(winningLottoBonusNumber);
    }
}
