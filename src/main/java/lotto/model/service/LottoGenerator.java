package lotto.model.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.configuration.LottoConstants;
import lotto.model.domain.Lotto;
import lotto.model.dto.LottoWallet;
import lotto.model.dto.PurchaseMoney;

public class LottoGenerator {

    public LottoWallet purchaseLotto(PurchaseMoney purchaseMoney) {
        int lottoCount = purchaseMoney.getValue() / LottoConstants.LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generateLotto());
        }

        return new LottoWallet(lottos);
    }

    private Lotto generateLotto() {
        List<Integer> lottoNumbers = getUniqueLottoNumber();

        sortNumbers(lottoNumbers);

        return new Lotto(lottoNumbers);
    }

    private List<Integer> getUniqueLottoNumber() {
        List<Integer> lottoNumber = Randoms.pickUniqueNumbersInRange(LottoConstants.MIN_LOTTO_NUM,
                LottoConstants.MAX_LOTTO_NUM,
                LottoConstants.LOTTO_NUMBER_LENGTH);

        return new ArrayList<>(lottoNumber);
    }

    private void sortNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
    }

}
