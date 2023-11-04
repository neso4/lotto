package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbersGenerator implements RandomNumbersGenerator {

    private static final Integer LOTTO_LIST_SIZE = 6;
    private static final Integer MIN_LOTTO_NUMBER = 1;
    private static final Integer MAX_LOTTO_NUMBER = 45;

    @Override
    public List<Integer> createUniqueElementList() {
        Set<Integer> lottoNumbers = getLottoNumberSet();
        return convertToList(lottoNumbers);
    }

    private Set<Integer> getLottoNumberSet() {
        Set<Integer> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_LIST_SIZE) {
            lottoNumbers.add(Randoms.pickNumberInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
        return lottoNumbers;
    }

    private List<Integer> convertToList(Set<Integer> lottoNumberSet) {
        return new ArrayList<>(lottoNumberSet);
    }
}