package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Winning extends Input {
    private List<Integer> numbers;

    public Winning() {
        numbers = new ArrayList<>();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void save(String readLine) {
        List<Integer> numbers = validate(readLine);
        this.numbers = numbers;
    }

    protected List<Integer> validate(String readLine) {
        String noEmptyReadLine = removeEmpty(readLine);
        checkOtherCharacter(noEmptyReadLine);
        List<Integer> numbers = translateToValueType(noEmptyReadLine);
        numbers.forEach(this::checkBoundary);
        checkDuplication(numbers);
        return numbers;
    }

    protected String removeEmpty(String readLine) {
        return readLine.replaceAll("\\s", "");
    }

    private void checkOtherCharacter(String noEmptyReadLine) {
        String regex = "^[0-9,]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(noEmptyReadLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자와 쉼표(,) 이외에 입력될 수 없습니다.");
        }
    }

    protected List<Integer> translateToValueType(String noEmptyReadLine) {
        String[] split = noEmptyReadLine.split(",");
        return Arrays.stream(split)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void checkDuplication(List<Integer> numbers) {
        Set<Integer> noDuplicationNumbers = new HashSet<>(numbers);
        if (numbers.size() != noDuplicationNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복되지 않는 숫자여야 합니다.");
        }
    }
}
