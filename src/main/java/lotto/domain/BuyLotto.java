package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class BuyLotto {
    private static boolean correctInputMoney;
    public static void inputMoney(){
        correctInputMoney = false;
        //로또 구입 금액 입력
        while(!correctInputMoney){
            System.out.println("구입금액을 입력해 주세요.");
            int inputPurchaseMoney = Integer.valueOf(readLine());

            validateInputMoney(inputPurchaseMoney);
        }
    }

    public static void validateInputMoney(int inputPurchaseMoney){
        //1000원으로 나누어떨어지지 않는 경우 에러 처리
        try{
            if(inputPurchaseMoney % 1000 == 0){
                quantityLotto(inputPurchaseMoney);
                correctInputMoney = true;
            }else if(inputPurchaseMoney % 1000 != 0){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.println("[ERROR] 로또 금액인 1000원 단위로 입력해주세요.");
        }
    }


    public static void quantityLotto(int inputPurchaseMoney) {
        //발행한 로또 수량 출력
        int buyingQuantityLotto = inputPurchaseMoney / 1000;
        System.out.println();
        System.out.println(buyingQuantityLotto + "개를 구매했습니다.");
    }

    public static void numberLotto(){
        //로또 번호(오름차순, 중복 X) 출력
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
