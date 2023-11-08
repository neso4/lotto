package lotto.domain;

import java.util.List;
import lotto.constant.ExceptionMessage;
import lotto.constant.LottoGame;
import lotto.util.InputValidator;

public class Lotto {
    private final List<Integer> numbers;
    private Rank rank;


    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (!InputValidator.isCorrectCount(numbers.size()) || InputValidator.isDuplicateNumber(numbers)) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBER_ERROR.getMessage());
        }
    }

    public void setRank(int count) {
        if(count >= LottoGame.WIN_MIN_COUNT) {
            rank = Rank.getRank(count);
            Rank.addRankCount(rank);
        }
    }

    public Rank getRank() {
        return rank;
    }


    public void matchBonusNumber(int bonusNumber) {
        if(rank == Rank.THIRD && numbers.contains(bonusNumber)) {
            Rank.minusThirdRankCount();
            rank = Rank.SECOND;
            Rank.addRankCount(rank);
        }
    }
}
