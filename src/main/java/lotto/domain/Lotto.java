package lotto.domain;

import lotto.constant.LottoConstants;
import lotto.exception.LottoException;
import lotto.vo.LottoWinningBonusNumber;
import lotto.vo.LottoNumber;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<Integer> numbers) throws LottoException {
        // TreeSet : 로또 번호 오름차순 정렬 (Comparable<LottoNumber> 구현)
        Set<LottoNumber> uniqueNumbers = numbers.stream()
                .map(LottoNumber::new).collect(Collectors.toCollection(TreeSet::new));
        validate(new ArrayList<>(uniqueNumbers));
        this.lottoNumbers = new ArrayList<>(uniqueNumbers);
    }

    private void validate(final List<LottoNumber> lottoNumbers) throws LottoException {
        if (lottoNumbers.size() != LottoConstants.LOTTO_SIZE.getValue()) {
            throw new LottoException(LottoException.ErrorMessage.DUPLICATE_LOTTO_NUMBERS.getMessage());
        }
    }

    public void validateAndThrowIfBonusNumberExists(final LottoWinningBonusNumber lottoWinningBonusNumber) throws LottoException {
        if (containsBonusNumber(lottoWinningBonusNumber)) {
            throw new LottoException(LottoException.ErrorMessage.CONTAINS_ALREADY_BONUS_NUMBER.getMessage());
        }
    }

    public List<LottoNumber> getNumbers() {
        return lottoNumbers;
    }

    public boolean containsBonusNumber(LottoWinningBonusNumber lottoWinningBonusNumber) {
        return lottoNumbers.contains(lottoWinningBonusNumber.value());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lotto lotto = (Lotto) o;

        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return lottoNumbers.hashCode();
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
