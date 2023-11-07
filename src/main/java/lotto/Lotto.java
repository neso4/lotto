package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_LENGTH = 6;
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateLength(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public boolean isMatch(Integer number) {
        return lottoNumbers.contains(number);
    }
    public Integer getMatchCount(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::isMatch)
                .count();
    }

    private void validateLength(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException("로또의 숫자는 6개여야 합니다!");
        }
    }

    private void validateDuplicate(List<Integer> lottoNumbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(lottoNumbers);
        if(uniqueNumbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다!");
        }
    }
}
