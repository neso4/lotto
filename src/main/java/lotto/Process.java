package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Process {
    private static final int LOTTO_NUMEBER_RANGE_START = 1;
    private static final int LOTTO_NUMEBER_RANGE_END = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private List<Integer> lottoNumbers;


    //로또 개수 환산하기
    public int countLottos(int purchaseAmount) {
        int purchaseCount;

        purchaseCount = purchaseAmount / 1000;

        return purchaseCount;
    }


    public List<List<Integer>> saveLottos(int purchaseCount) {
        List<Integer> lottoNumbers = null;
        List<Integer> eachLottoNumber = null;
        List<List<Integer>> lottos = new ArrayList<>();


        for (int i = 0; i < purchaseCount; i++) {
            lottoNumbers = generateLottoNumbers();

            sortLottoNumbers(lottoNumbers);

            Lotto lotto = new Lotto(lottoNumbers);
            eachLottoNumber = lotto.getLottoNumbers();

            lottos.add(eachLottoNumber);
        }

        return lottos;
    }


    //로또 개수만큼 로또 번호 생성
    private List<Integer> generateLottoNumbers() {

        //값을 하드 코딩하지 않는다 - 2주차 피드백
        lottoNumbers = Randoms.pickUniqueNumbersInRange(LOTTO_NUMEBER_RANGE_START, LOTTO_NUMEBER_RANGE_END, LOTTO_NUMBER_COUNT);

        return lottoNumbers;
    }


    //각 로또 번호들 오름차순으로 정리
    private void sortLottoNumbers(List<Integer> lottoNumbers) {
        Collections.sort(lottoNumbers);
    }


    public void recordWins(List<List<Integer>> lottos, List<Integer> winningNumbers, int bonusNumber) {

        for (var lottoNumber : lottos) {
            List<Object> result = compareLottos(lottoNumber, winningNumbers, bonusNumber);

            int mainMatchNumber = (int) result.get(0);
            boolean matchedBonusNumber = (boolean) result.get(1);

            if (mainMatchNumber == 3) {
                WinningRankPrize.FIFTH.increaseWinCount();
            }
            if (mainMatchNumber == 4) {
                WinningRankPrize.FOURTH.increaseWinCount();
            }
            if (mainMatchNumber == 5 && !matchedBonusNumber) {
                WinningRankPrize.THIRD.increaseWinCount();
            }

            if (mainMatchNumber == 5 && matchedBonusNumber) {
                WinningRankPrize.SECOND.increaseWinCount();
            }

            if (mainMatchNumber == 6) {
                WinningRankPrize.FIRST.increaseWinCount();
            }
        }

    }


    private List<Object> compareLottos(List<Integer> lottoNumber, List<Integer> winningNumbers, int bonusNumber) {
        int mainNumberMatchCount = 0;
        boolean matchedBonusNumber = false;
        List<Object> result = new ArrayList<>();

        for (var element : lottoNumber) {
            if (winningNumbers.contains(element)) {
                mainNumberMatchCount++;
            }
            if (element == bonusNumber) {
                matchedBonusNumber = true;
            }
        }

        result.add(mainNumberMatchCount);
        result.add(matchedBonusNumber);

        return result;
    }


    //수익률 계산
    public float calculateRateOfReturn(int purchaseAmount) {
        float totalPrizeAmount = 0;
        float rateOfReturn;

        for (WinningRankPrize rankPlace : WinningRankPrize.values()) {
            totalPrizeAmount += Integer.parseInt(rankPlace.getPrizeAmount().replace(",", "")) * rankPlace.getWinCount();
        }

        rateOfReturn = (totalPrizeAmount / (float) purchaseAmount) * 100;

        return rateOfReturn;
    }

}
