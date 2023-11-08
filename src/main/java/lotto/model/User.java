package lotto.model;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.stream.Collectors;

public class User {
//    사용자는 구입 금액과 당첨 번호, 보너스 번호를 각각 입력해야 한다.
    public int inputPurchaseAmount() {
        String input = Console.readLine();
        try {
            int money = Integer.parseInt(input);
            validatePurchaseAmount(money);
            return money;
        }catch (NumberFormatException e){ // 숫자인지 확인 가능
            System.out.println("[ERROR] 1000원 단위의 숫자를 입력해주세요");
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
        return inputPurchaseAmount();
    }

    public List<Integer> inputLottoNumber() {
        try {
            List<Integer> numbers = List.of(Console.readLine().split(",")).stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return numbers;
        }catch (NumberFormatException e){
            System.out.println("[ERROR] 숫자로만 이루어진 6자리를 입력하세요");
        }
        return inputLottoNumber();
    }

    private void validatePurchaseAmount(int money){
        if(money<1000||money>100000){// 1000원 미만, 100000원 초과의 값 입력시의 예외 처리
            throw new IllegalArgumentException("[ERROR] 1000원 이상 100000원 이하의 값을 입력해주세요");
        }
        if(money%1000!=0){// 1000원 단위의 값 외의 값 입력시의 예외 처리
            throw new IllegalArgumentException("[ERROR] 1000원 단위의 값을 입력해주세요");
        }
    }
}
