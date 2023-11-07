package lotto.validator;

import java.util.List;

public class LottoValidator {

    private final List<Integer> lottoList;

    public LottoValidator(List<Integer> lottoList) {
        this.lottoList = lottoList;
    }

    public void validateAll() {
        validateSize();
        validateNumberRange();
        validateCheckUnique();
    }

    public void validateSize() throws IllegalArgumentException {
        if (lottoList.size() != 6) {
            throw new IllegalArgumentException("로또는 6개여야 합니다.");
        }
    }

    public void validateCheckUnique() throws IllegalArgumentException {
        if (getUniqueCount() != 6){
            throw new IllegalArgumentException("로또 숫자는 중복을 허락하지 않습니다.");
        }
    }

    private long getUniqueCount() {
        return lottoList.stream()
                .distinct()
                .count();
    }

    public void validateNumberRange() {
        lottoList.stream()
                .filter(this::isNumberWrongRange)
                .forEach(number -> {
                    throw new IllegalArgumentException("1 ~ 45 사이의 숫자만 가능합니다.");
                });
    }

    private boolean isNumberWrongRange(int lotto) {
        return (lotto < 1 || lotto > 45);
    }

    public void checkBonusNumber(int bonusNumber) {
        if (lottoList.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 숫자는 로또 정답 숫자와 중복될 수 없습니다.");
        }
    }

}
