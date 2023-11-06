package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;
    private int bonusNumber;
    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public static Lotto generateRandomLotto(){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
    public List<Integer> getNumbers() {
        return numbers;
    }
    private static int countMatchingNumbers(List<Integer> winnerNumbers, List<Integer> ticketNumbers) {
        Set<Integer> uniqueWinnerNumbers = new HashSet<>(winnerNumbers);
        Set<Integer> uniqueTicketNumbers = new HashSet<>(ticketNumbers);
        uniqueTicketNumbers.retainAll(uniqueWinnerNumbers);

        return uniqueTicketNumbers.size();
    }

    public boolean hasBonusNumber() {
        return numbers.contains(bonusNumber);
    }
}
