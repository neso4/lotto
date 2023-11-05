package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.stream.Stream;

class User {

    public int inputPurchasingVolume(){
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
//        입력값 검증 함수
        int volume = Integer.parseInt(input);
        return volume/1000;
    }

    public List<Integer> inputWinningNumbers(){
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
//       입력값 검증 함수
        Integer[] inputNums = Stream.of(input.split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(inputNums));
        return numbers;
    }

    public int inputBonusNumber(){
        String input = Console.readLine();
//        입력값 검증 함수
        int number = Integer.parseInt(input);
        return number;
    }
}