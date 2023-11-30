package lotto.lottomarket;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.lottomarket.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LottoMarketService {
    static private final int LOTTO_NUM_SIZE = 6;
    static private final int LOTTO_PRICE = 1000;
    private final static int MIN_LOTTO_NUM = 1;
    private final static int MAX_LOTTO_NUM = 45;

    public List<Lotto> issueLottoes(int lottoCount) {
        List<Lotto> lottoes = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> lottoNums = new ArrayList<>(generateNonDuplicatedNums());
            Collections.sort(lottoNums);
            lottoes.add(new Lotto(lottoNums));
        }
        return lottoes;
    }

    public List<Integer> generateNonDuplicatedNums() {
        List<Integer> lottoesNums = Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUM, MAX_LOTTO_NUM, LOTTO_NUM_SIZE);
        return lottoesNums;
    }

    public int calculateLottoCount(int money) {
        int lottoCount = money / LOTTO_PRICE;
        return lottoCount;
    }
}
