package lotto.domain.strategy;

import java.util.List;

@FunctionalInterface
public interface LottoNumberStrategy {

    List<Integer> createNumber();
}
