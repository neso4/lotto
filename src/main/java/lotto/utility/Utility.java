package lotto.utility;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public int parseInt(String input) {
        try {
            int number = Integer.parseInt(input);
            return number;
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 입력값이 정수가 아닙니다\n");
            throw new IllegalArgumentException("[ERROR] 입력값이 정수가 아닙니다");
        }
    }

    public List<Integer> getRandomNumbers(int length) {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, length);
        return numbers;
    }

    public String[] splitInput (String input) {
        String[] splitResult = input.split(",");
        return splitResult;
    }

    public List<Integer> getIntList (String[] inputs) {
        List<Integer> numbers = new ArrayList<>();

        for(String input : inputs) {
            int number = parseInt(input);
            numbers.add(number);
        }

        return numbers;
    }

    public double getReturnRate(int payment, long result) {
        double returnRate = (double) result / (double) payment;
        return returnRate;
    }
}
