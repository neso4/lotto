package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.*;
import java.util.stream.Collectors;

public class Judgment{
    private int bonus;
    private Lotto lotto;

    public void userInput(){
        while(true) {
            try {
                lotto = new Lotto(inputNumbers());
                inputBonusNumber();
                break;
            }
            catch (IllegalArgumentException e){
            }
        }
    }

    public List<Integer> inputNumbers(){
        List<Integer> numbers;
        while (true) {
            try {
                System.out.println("\n당첨 번호를 입력해 주세요.");
                numbers = splitNumbers(Console.readLine());
                isInRange(numbers);
                break;
            } catch (IllegalArgumentException e) {
            }
        }
        return numbers;
    }

    public void inputBonusNumber(){
        while (true) {
            try {
                System.out.println("\n보너스 번호를 입력해 주세요.");
                bonus = duplicate(Console.readLine());
                isInRange(bonus);
                break;
            } catch (IllegalArgumentException e) {
            }
        }
    }

    public int duplicate(String input){
        int bonus = Integer.parseInt(input);

        if(lotto.getNumbers().contains(bonus)){
            System.out.println("[ERROR] 중복된 번호입니다. 다시입력해주세요. ");
            throw new IllegalArgumentException();
        }
        return bonus;
    }

    public List<Integer> splitNumbers(String input){
        String[] splitComma = input.split(",");

        List<Integer> numbers = new ArrayList<>();
        numbers = Arrays.stream(splitComma)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Set<Integer> numbersSet = new HashSet<>(numbers);

        if(numbersSet.size()!= numbers.size()){ // 입력된 로또 번호에 중복값이 있는지 확인
            System.out.println("[ERROR] 중복된 번호입니다. 다시입력해주세요. ");
            throw new IllegalArgumentException();
        }
        return numbers;
    }

    public void isInRange(List<Integer> numbers){
        for(int number : numbers){
            if(number < 1 || number > 45) {
                System.out.println("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
                throw new IllegalArgumentException();
            }
        }
    }
    public void isInRange(int number){
            if(number < 1 || number > 45) {
                System.out.println("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
                throw new IllegalArgumentException();
            }
    }

    public static int divisibleByThousand(int amount){
        int remain = amount % 1000;
        int numberOfTickets = amount / 1000;

        if(numberOfTickets < 1){
            System.out.println("[ERROR] 1,000원 보다 작은 숫자가 입력되었습니다. ");
            throw new IllegalArgumentException();
        }

        if(remain != 0){
            System.out.println("[ERROR] 1,000원 단위로 숫자를 입력해주세요. ");
            throw new IllegalArgumentException();
        }


        return numberOfTickets;
    }

    public int getBonus() {
        return bonus;
    }

    public Lotto getLotto() {
        return lotto;
    }
}
