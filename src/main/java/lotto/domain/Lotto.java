package lotto.domain;

import java.util.List;

public class Lotto {
    public static final String LOTTO_PRIFIX = "[";
    public static final String LOTTO_SUFFIX = "]";
    public static final String NUMBER_DELIMITER = ", ";

    private final List<Integer> numbers;

    public Lotto(LottoNumbers lottoNumbers) {
        this.numbers = lottoNumbers.getLottoNumbers();
    }

    public String getLottoMessage() {
        return LOTTO_PRIFIX + String.join(NUMBER_DELIMITER, convertToString(numbers)) + LOTTO_SUFFIX;
    }

    private List<String> convertToString(List<Integer> numbers) {
        return numbers.stream()
                .map(String::valueOf)
                .toList();
    }

    public Rank compare(WinningNumbers winningNumbers) {
        int matchWinningNumberCount = getMatchCount(winningNumbers.getNumbers());
        boolean matchBonusNumber = isMatchBonusNumber(winningNumbers.getBonusNumber());
        return Rank.of(matchWinningNumberCount, matchBonusNumber);
    }

    private int getMatchCount(List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean isMatchBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}
