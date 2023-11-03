package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.dto.LottoResponseDtos;
import lotto.dto.LottoResponseDtos.LottoResponseDto;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public LottoResponseDtos toResponseDto() {
        List<LottoResponseDto> dtos = lottos.stream()
            .map(lotto -> lotto.toResponseDto())
            .collect(Collectors.toList());
        return new LottoResponseDtos(dtos);
    }
}
