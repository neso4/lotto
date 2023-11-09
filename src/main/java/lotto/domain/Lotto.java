package lotto.domain;

import lotto.constants.ErrorMessage;
import lotto.constants.NumberType;
import java.util.List;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLotto(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public String getGeneratedLottoString() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .toList().toString();
    }

    public boolean hasNumber(int number) {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .anyMatch(member -> member == number);
    }

    public int countMatchNumber(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .filter(lotto::hasNumber)
                .count();
    }

    private void validateLotto(List<LottoNumber> lottoNumbers) {
        validateLength(lottoNumbers);
        validateDuplicateMember(lottoNumbers);
    }

    private void validateLength(List<LottoNumber> lottoNumbers) {
        if (checkLength(lottoNumbers)) {
            throw new IllegalArgumentException(
                    String.format(ErrorMessage.LOTTO_LENGTH_ERROR.getMessage(),
                            NumberType.LOTTO_LENGTH.getValue()));
        }
    }

    private boolean checkLength(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != NumberType.LOTTO_LENGTH.getValue();
    }

    private void validateDuplicateMember(List<LottoNumber> lottoNumbers) {
        if (checkHasDuplicateMember(lottoNumbers)) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_HAS_DUPLICATE_NUMBER_ERROR.getMessage());
        }
    }

    private boolean checkHasDuplicateMember(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .distinct()
                .count() != lottoNumbers.size();
    }
}
