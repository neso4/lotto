package lotto.model.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.utils.LottoCount;
import lotto.utils.LottoCreator;

public class LottoServiceImpl implements LottoService {
    private final int LOTTO_MIN_COUNT = 3;

    @Override
    public List<List<Integer>> createLotto(int lottoCount) {
        LottoCreator creator = new LottoCreator();
        creator.createLotto(lottoCount);
        return creator.getCreatedLottos();
    }

    @Override
    public Map<LottoCount, Integer> compareLottoToWinningAndBonus(List<List<Integer>> createdLottos,
                                                                  List<Integer> winningNumbers,
                                                                  int bonusNumber) {
        Map<LottoCount, Integer> resultTable = new HashMap<>();
        for (List<Integer> createdLotto : createdLottos) {
            LottoCount lottoCount = findLottoCount(createdLotto, winningNumbers, bonusNumber);

            Integer duplicatedValue = resultTable.getOrDefault(lottoCount, 0);
            resultTable.put(lottoCount, duplicatedValue + 1);
        }
        return Collections.unmodifiableMap(resultTable);
    }

    private LottoCount findLottoCount(List<Integer> createdLotto,
                                      List<Integer> winningNumbers,
                                      int bonusNumber) {
        long duplicatedCount = createdLotto.stream()
                .filter(winningNumbers::contains)
                .count();
        boolean hasBonus = createdLotto.contains(bonusNumber);
        // 5개 일치 + 보너스 번호 일치하면 BONUS 상수로 구분
        if (duplicatedCount == 5 && hasBonus) {
            return LottoCount.BONUS;
        }
        return LottoCount.findByCount(duplicatedCount);
    }
}
