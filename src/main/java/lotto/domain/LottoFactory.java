package lotto.domain;

import java.util.stream.IntStream;

final class LottoFactory {

    private final LottoGenerator generator;

    public LottoFactory(LottoGenerator generator) {
        this.generator = generator;
    }

    Lottos createLottos(int size) {
        validate(size);

        Lottos lottos = new Lottos();
        IntStream.range(0, size)
                .mapToObj(i -> generator.generate())
                .forEach(lottos::addLotto);
        return lottos;
    }

    private void validate(int size) {
        if(size < 1) throw new IllegalArgumentException(DomainException.LOTTO_SIZE_LESS_THAN_1.getMessage());
    }
}