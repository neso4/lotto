package lotto;

import java.util.*;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static int amount;
    private static int bonusNum;
    private static Lotto answer;

    public static void main(String[] args) {
        getAmount();

        getAnswer();

        getBonusNum();

        printResult();
    }

    private static void printResult(){
        System.out.println("당첨 통계");
        System.out.println("---");
        for (WinningResult result : WinningResult.values()) {
            System.out.println(result.getPhrase() + " - " + result.getCount() + "개");
        }
    }

    private static List<Integer> generateRandomNumbers(){
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_NUMBER_COUNT) {
            int randomNumber = Randoms.pickNumberInRange(LOTTO_START_NUMBER, LOTTO_END_NUMBER);
            numbers.add(randomNumber);
        }
        return new ArrayList<>(numbers);
    }

    private static void getAmount(){
        while (true) {
            try{
                System.out.println("구입금액을 입력해 주세요.");
                String amountInput = Console.readLine();
                validateAmount(amountInput);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void validateAmount(String amountInput) {
        if (!amountInput.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
        }
        amount = Integer.parseInt(amountInput);
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다.");
        }
    }

    private static void getBonusNum(){
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String bonusNumInput = Console.readLine();
                validateBonusNum(bonusNumInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void validateBonusNum(String bonusNumInput) {
        if (!bonusNumInput.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.");
        }
        bonusNum = Integer.parseInt(bonusNumInput);
        if (bonusNum < LOTTO_START_NUMBER || bonusNum > LOTTO_END_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이의 정수로 입력해야 합니다.");
        }
    }

    private static void getAnswer(){
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String answerInput = Console.readLine();
                List<Integer> answerNums = Arrays.stream(answerInput.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                answer = new Lotto(answerNums);
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자를 입력해 주세요.");
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
