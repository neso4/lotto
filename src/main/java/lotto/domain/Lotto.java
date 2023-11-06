package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.constants.LottoConstants;

public class Lotto {

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public static Lotto from(List<Integer> numbers) {
        validate(numbers);
        return new Lotto(numbers);
    }

    private static void validate(List<Integer> numbers) {
        if (isSameLength(numbers)) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] %d개로 로또를 생성하려 했습니다. 로또의 번호는 6개 입니다", numbers.size()));
        }
        if (!isDupplicated(numbers)) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또의 번호는 중복될 수 없습니다."));
        }
        if (!isBoundary(numbers)) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 로또 번호는 %d 부터 %d 이내입니다.", LottoConstants.MIN_NUMBER.getConstants(),
                            LottoConstants.MAX_NUMBER.getConstants()));
        }
    }

    private static boolean isBoundary(List<Integer> numbers) {
        return numbers.stream().allMatch(number -> IsRangeNumber(number));
    }

    private static boolean IsRangeNumber(int number) {
        return LottoConstants.MIN_NUMBER.getConstants() <= number && number <= LottoConstants.MAX_NUMBER.getConstants();

    }

    private static boolean isDupplicated(List<Integer> numbers) {
        List<Integer> duplicated = numbers.stream().distinct().toList();
        return duplicated.size() == numbers.size();
    }

    private static boolean isSameLength(List<Integer> numbers) {
        return numbers.size() != LottoConstants.LENGTH.getConstants();
    }

    public static Lotto createAutoLottoNumbers(Generator generator) {
        return new Lotto(generator.generate());
    }

    public static int matchCount(Lotto given, List<Integer> winnings) {
        return (int) given.getLotto().stream().filter(n -> isInWinning(n, winnings)).count();
    }

    private static boolean isInWinning(int number, List<Integer> winnings) {
        return winnings.stream().anyMatch(n -> n == number);
    }

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(numbers);
    }

}
