package lotto.domain.util.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.domain.config.LottoConfig;

public class LottoNumberGenerator implements ILottoNumberGenerator {
    private static final int LOTTO_MINIMUM_NUMBER = LottoConfig.MINIMUM_NUMBER.getValue();
    private static final int LOTTO_MAXIMUM_NUMBER = LottoConfig.MAXIMUM_NUMBER.getValue();
    private static final int LOTTO_SIZE = LottoConfig.SIZE.getValue();

    public LottoNumberGenerator() {
    }

    @Override
    public List<Integer> createRandom() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_MINIMUM_NUMBER, LOTTO_MAXIMUM_NUMBER,
                LOTTO_SIZE);
    }
}
