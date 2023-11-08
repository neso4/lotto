package lotto.domain;

import lotto.constant.MagicNumber;

public class BonusLotto {
    private final int number;

    public BonusLotto(int number, Lotto lottoNumbers) {
        validate(number, lottoNumbers);
        this.number = number;
    }

    public int getBonusLotto() {
        return number;
    }

    private void validate(int number, Lotto lottoNumbers) {
        checkNumberInRange(number);
        checkBonusNumberUniqueWithLottoNumbers(number, lottoNumbers);
    }

    private void checkNumberInRange(int number) {
        if (number < MagicNumber.MIN_NUMBER.getNumber() || number > MagicNumber.MAX_NUMBER.getNumber()) {
            throw new IllegalArgumentException("[ERROR] " + MagicNumber.MIN_NUMBER.getNumber() + " ~ "
                    + MagicNumber.MAX_NUMBER.getNumber() + "사이의 숫자를 입력해 주세요.");
        }
    }

    private void checkBonusNumberUniqueWithLottoNumbers(int number, Lotto lottoNumbers) {
        if (lottoNumbers.getNumbers().contains(number)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호와 보너스 번호가 중복되었습니다.");
        }
    }
}
