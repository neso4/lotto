package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchingMachine {

    public List<Integer> getLottoNumbers() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return numbers;
    }

    public List<Lotto> getLottoAsMuchAsVolume(int volume) {
        List<Lotto> allLotto = new ArrayList<>();
        for (int i = 0; i < volume; i++) {
            List<Integer> lottoNumbers = getLottoNumbers();
            Collections.sort(lottoNumbers);
            Lotto lotto = new Lotto(lottoNumbers);
            allLotto.add(lotto);
        }
        return allLotto;
    }

    public int countSingleLottoMatchingNumbers(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        int count = 0;
        for (int lottoNum : lottoNumbers) {
            if (winningNumbers.contains(lottoNum)) {
                count++;
            }
        }
        return count;
    }

    public boolean checkBonusNumber(Lotto lotto, int bonusNumber) {
        List<Integer> singleLotto = lotto.getNumbers();
        if (singleLotto.contains(bonusNumber)) {
            return true;
        }
        return false;
    }

    public List<MatchingCount> countAllLottoMatchingNumbers(List<Lotto> allLotto, List<Integer> winningNumbers, int bonusNumber) {
        int count = 0;
        boolean hasBonus = false;
        List<MatchingCount> matchingCounts = new ArrayList<>();
        for (Lotto singleLotto : allLotto) {
            count = countSingleLottoMatchingNumbers(singleLotto.getNumbers(), winningNumbers);
            hasBonus = checkBonusNumber(singleLotto, bonusNumber);
            MatchingCount matchingCount = MatchingCount.findByMatchCount(count, hasBonus);
            matchingCounts.add(matchingCount);
        }
        return matchingCounts;
    }
}
