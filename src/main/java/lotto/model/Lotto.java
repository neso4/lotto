package lotto.model;

import lotto.utils.Constants;
import lotto.utils.LottoExceptions;
import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        ArrayList<Integer> mutableNumbers = new ArrayList<>(numbers);
        mutableNumbers.sort(Comparator.naturalOrder());
        this.numbers = mutableNumbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != Constants.DRAW_COUNT) {
            throw new IllegalArgumentException(LottoExceptions.InvalidCountError.getErrorMessage());
        }
        checkDuplicate(numbers);
    }

    private void checkDuplicate(List<Integer> numbers){
        Set<Integer> duplicateNotAllowed = new HashSet<>(numbers);
        if (duplicateNotAllowed.size() < numbers.size()){
            throw new IllegalArgumentException(LottoExceptions.DuplicateError.getErrorMessage());
        }
    }

    public int match(int winnerNumber){
        return Collections.binarySearch(numbers, winnerNumber);
    }

    public String printLotto() {
       return Arrays.toString(numbers.toArray());
    }

}
