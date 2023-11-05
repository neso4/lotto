package lotto.domain;

import java.util.List;

public class AnswerLotto {

    private static final String ANSWER_LOTTO_LENGTH_ERROR_MESSAGE = "[ERROR] 로또 번호는 6개이어야 합니다.";
    private static final String ANSWER_LOTTO_DUPLICATE_ERROR_MESSAGE = "[ERROR] 로또 번호는 중복될 수 없습니다.";
    private static final String BONUS_NUMBER_DUPLICATE_ERROR_MESSAGE = "[ERROR] 보너스 볼은 로또 번호와 중복될 수 없습니다.";
    private final Lotto lotto;
    private final BonusNumber bonusNumber;

    public AnswerLotto(List<Integer> numbers, int bonusNumber) {
        validateNumberCount(numbers);
        validateDuplicateNumbers(numbers, bonusNumber);
        this.lotto = new Lotto(numbers);
        this.bonusNumber = new BonusNumber(bonusNumber);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ANSWER_LOTTO_LENGTH_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateNumbers(List<Integer> numbers, int bonusNumber) {
        validateNumbers(numbers);
        validateWithBonusNumber(numbers, bonusNumber);
    }

    private void validateNumbers(List<Integer> numbers) {
        int nonDuplicatedCount = (int) numbers.stream().distinct().count();
        if (nonDuplicatedCount != numbers.size()) {
            throw new IllegalArgumentException(ANSWER_LOTTO_DUPLICATE_ERROR_MESSAGE);
        }
    }

    private void validateWithBonusNumber(List<Integer> numbers, int bonusNumber) {
        boolean isDuplicated = numbers.stream()
                .filter(number -> number.equals(bonusNumber))
                .findFirst().isPresent();
        if (isDuplicated) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public Ranking calculateWinningResult(Lotto userLotto) {
        // 당첨 번호에서 비교
        int matchCount = userLotto.compareToAnswerLotto(lotto);

        // 보너스 번호 비교
        boolean containBonusBall = userLotto.compareToBonusNumber(bonusNumber.getBonusNumber());

        return Ranking.getRanking(matchCount, containBonusBall);
    }
}
