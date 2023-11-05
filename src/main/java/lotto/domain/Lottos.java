package lotto.domain;

import lotto.domain.dto.StatisticsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Lottos {

    private List<Lotto> lottos;

    void addLotto(List<Integer> lotto) {
        lottos.add(new Lotto(lotto));
    }

    List<Result> calculateResult(Lotto winningNumber, BonusNumber bonusNumber) {
        List<Result> results = new ArrayList<>();
        for (Lotto lotto : lottos) {
            results.add(lotto.countMatch(winningNumber, bonusNumber));
        }
        return results;
    }

    List<List<Integer>> getLottos() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .collect(Collectors.toList());
    }
}
