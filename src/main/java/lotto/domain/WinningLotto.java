package lotto.domain;

import static lotto.Error.Domain.BONUS_NUMBER_ALREADY_EXIST;
import static lotto.Error.Domain.WRONG_LOTTO_NUMBER_RANGE;

public final class WinningLotto {
    private final Lotto answerLotto;
    private final int bonus;

    public WinningLotto(Lotto answerLotto, int bonus) {
        this.answerLotto = answerLotto;
        validate(bonus);
        this.bonus = bonus;
    }

    private void validate(int bonus) {
        validateNumberSize(bonus);
        validateSameWithLottoNumbers(bonus);
    }

    private void validateNumberSize(int bonus) {
        if (bonus < Lotto.MIN_LOTTO_NUMBER_SIZE || bonus > Lotto.MAX_LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(WRONG_LOTTO_NUMBER_RANGE.getText());
        }
    }

    private void validateSameWithLottoNumbers(int bonus) {
        boolean isDuplicated = answerLotto.getNumbers()
                .stream()
                .anyMatch(number -> number == bonus);

        if (isDuplicated) {
            throw new IllegalArgumentException(BONUS_NUMBER_ALREADY_EXIST.getText());
        }
    }
}
