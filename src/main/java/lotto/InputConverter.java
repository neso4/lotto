package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputConverter {

    public int convertToInteger(String input) {
        return Integer.parseInt(input);
    }

    public List<Integer> convertToListOfInteger(String input) {
        List<Integer> numbers = new ArrayList<>();

        String[] splitInput = input.split(",");
        int[] convertedNums = new int[splitInput.length];

        for (int i = 0; i < splitInput.length; i++) {
            int digit = Integer.parseInt(splitInput[i]);
            convertedNums[i] = digit;
        }

        Arrays.sort(convertedNums);

        for (int num : convertedNums) {
            numbers.add(num);
        }

        return numbers;
    }
}
