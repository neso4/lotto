package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.CustomIllegalArgumentException;
import util.GameProperty;
import util.InputException;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public static void validate(List<Integer> numbers) {
        lengthVlalidator(numbers);
        hasDupleNum(numbers);
    }

    private static void lengthVlalidator(List<Integer> numbers) {
        if (numbers.size() != GameProperty.LOTTO_NUMBERS_LENGTH) {
            throw new CustomIllegalArgumentException(InputException.NUMBERS_LENGTH_EXCEPTION);
        }
    }

    private static void hasDupleNum(List<Integer> numbers) {
        Set<Integer> nonDupleNumbers = new HashSet<>(numbers);
        if (nonDupleNumbers.size() != numbers.size()) {
            throw new CustomIllegalArgumentException(InputException.DUPLE_NUMBER_EXCEPTION);
        }
    }

    // TODO: 추가 기능 구현
}
