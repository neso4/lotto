package lotto;

import camp.nextstep.edu.missionutils.Console;
import util.Validate;

import java.util.ArrayList;
import java.util.List;

public class User {
    public int inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        int price;

        while(true) {
            try {
                String input = Console.readLine();
                Validate.isValidInput(input);
                price = Integer.parseInt(input);
                Validate.isValidPrice(price);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return price;
    }

    public List<Integer> winningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        List<Integer> numbers = new ArrayList<>();
        String inputNumbers = Console.readLine();
        String[] inputArray = inputNumbers.split(",");
        for (String s : inputArray) {
            numbers.add(Integer.valueOf(s));
        }

        try {
            Validate.isValidLotto(numbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            winningNumbers();
        }
        return numbers;
    }

    public int bonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonusNumber = Integer.parseInt(Console.readLine());

        try {
            Validate.isValidBonus(bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            bonusNumber();
        }

        return bonusNumber;
    }
}
