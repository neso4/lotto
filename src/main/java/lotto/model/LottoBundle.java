package lotto.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lotto.util.enums.LottoResult;

public class LottoBundle {
    private static final int DEFAULT_VALUE = 0;
    private static final int INCREMENT_VALUE = 1;

    private final Map<Lotto, Integer> bundles;

    public LottoBundle(Map<Lotto, Integer> bundles) {
        this.bundles = bundles;
    }

    public List<LottoDTO> generateLottoTicketReport() {
        List<LottoDTO> lottoDTO = new ArrayList<>();
        for (Map.Entry<Lotto, Integer> entry : bundles.entrySet()) {
            Lotto lotto = entry.getKey();
            lottoDTO.add(lotto.toDTO());
        }
        return lottoDTO;
    }

    public Map<String, Integer> compareLotto(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        Map<String, Integer> winningResult = initResult();
        ResultComparator resultComparator = new ResultComparator(winningNumbers, bonusNumber);

        for (Map.Entry<Lotto, Integer> entry : bundles.entrySet()) {
            Lotto lotto = entry.getKey();
            Optional<String> point = resultComparator.result(lotto);
            updateWinningResult(point, winningResult);
        }
        return winningResult;
    }

    private Map<String, Integer> initResult() {
        Map<String, Integer> initResult = new LinkedHashMap<>();
        for (LottoResult result : LottoResult.values()) {
            initResult.put(result.getDescription(), 0);
        }
        return initResult;
    }

    private void updateWinningResult(Optional<String> point, Map<String, Integer> winningResult) {
        point.ifPresent(s ->
                winningResult.put(s, winningResult.getOrDefault(s, DEFAULT_VALUE) + INCREMENT_VALUE));
    }
}
