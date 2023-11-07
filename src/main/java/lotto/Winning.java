package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Winning {
    private List<Integer> numbers;
    private Integer bonusNumber;

    public Winning() {
        numbers = new ArrayList<>();
        bonusNumber = 0;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public void saveNumbers(String readLine) {
        List<Integer> numbers = validateNumbers(readLine);
        this.numbers = numbers;
    }

    public void saveBonusNumber(String readLine) {
        validateBonusNumber(readLine);
        this.bonusNumber = bonusNumber;
    }

    private List<Integer> validateNumbers(String readLine) {
        String noEmptyReadLine = removeEmpty(readLine);
        checkOtherCharacter(noEmptyReadLine);
        List<Integer> numbers = createNumbers(noEmptyReadLine);
        numbers.forEach(this::checkBoundary);
        checkDuplication(numbers);
        return numbers;
    }

    private List<Integer> createNumbers(String noEmptyReadLine) {
        String[] split = noEmptyReadLine.split(",");
        return Arrays.stream(split)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void checkOtherCharacter(String noEmptyReadLine) {
        String regex = "^[0-9,]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(noEmptyReadLine);
        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
    }

    private String removeEmpty(String readLine) {
        return readLine.replaceAll("\\s", "");
    }

    private void checkDuplication(List<Integer> numbers) {
        Set<Integer> noDuplicationNumbers = new HashSet<>(numbers);
        if (numbers.size() != noDuplicationNumbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateBonusNumber(String readLine) {
        String noEmptyReadLine = removeEmpty(readLine);
        Integer bonusNumber = translateToNumber(noEmptyReadLine);
        checkBoundary(bonusNumber);
        checkExist(bonusNumber);
    }

    private Integer translateToNumber(String readLine) {
        Integer result = 0;
        try {
            result = Integer.parseInt(readLine);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    private void checkBoundary(Integer bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException();
        }
    }

    private void checkExist(Integer bonusNumber) {
        if (this.numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }
}
