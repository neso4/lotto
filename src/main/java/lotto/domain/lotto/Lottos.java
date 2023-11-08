package lotto.domain.lotto;

import lotto.ErrorMessage;
import lotto.domain.random.RandomNumberRangePicker;
import lotto.domain.result.Result;
import lotto.dto.LottoDto;
import lotto.dto.LottosDto;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.validateIsEmpty(lottos);
        this.lottos = lottos;
    }

    public static Lottos generateByAmount(LottoAmount lottoAmount, RandomNumberRangePicker randomNumberRangePicker) {
        List<Lotto> lottos = new ArrayList<>();
        int purchaseNumber = lottoAmount.getPurchaseNumber();
        for (int count = 0; count < purchaseNumber; count++) {
            List<Integer> numbers = randomNumberRangePicker.pickNumbers();
            lottos.add(new Lotto(numbers));
        }
        return new Lottos(lottos);
    }

    private void validateIsEmpty(List<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.LOTTOS_IS_EMPTY.message());
        }
    }

    public List<Result> getResults(WinningLotto winningLotto) {
        return lottos.stream()
                .map(lotto -> lotto.getResult(winningLotto))
                .toList();
    }

    public LottosDto toDto() {
        List<LottoDto> lottoDtos = lottos.stream()
                .map(Lotto::toDto)
                .toList();

        return new LottosDto(lottoDtos.size(), lottoDtos);
    }
}
