package lotto.lotto;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lotto.utils.StringSplitParser;

public class WinningLotto {

    private static final String NOT_NUMBERS_MESSAGE = "당첨 번호는 숫자로 입력해야 합니다.";
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^\\d+(,\\d+)*");
    private final Lotto lotto;

    public WinningLotto(String numbers) {
        validate(numbers);
        this.lotto = splitStringToLotto(numbers);
    }

    private Lotto splitStringToLotto(String numbers) {
        return StringSplitParser.splitStringToNumbers(numbers).stream().map(Integer::valueOf)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Lotto::new));
    }

    private void validate(String numbers) {
        if (!NUMERIC_PATTERN.matcher(numbers).matches()) {
            throw new IllegalArgumentException(NOT_NUMBERS_MESSAGE);
        }
    }
}
