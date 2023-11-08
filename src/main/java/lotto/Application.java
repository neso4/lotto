package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    /* main 파트 */
    public static void main(String[] args) {
        int lotto_num = integ_input_money();      // 돈 입력
        Lotto_Wallet Wallet = new Lotto_Wallet(); // 로또 지갑 객체 생성
        makeManyLottos(Wallet, lotto_num);        // 돈으로 로또를 싸서 지갑에 넣기

        final Lotto winning_lotto = integ_input_winning();          // 로또 당첨번호 입력
        final Integer bonus_num = integ_input_bonus(winning_lotto);// 보너스 번호 입력

        Wallet.Check_All_Lotto(winning_lotto, bonus_num);  // 지갑에서 모든 로또를 당첨번호(+보너스)와 비교
        Wallet.Result_Print();                             // 당첨 결과를 프린트
        Wallet.print_Return(lotto_num);                    // 로또 수익률 프린트
    }


    /* 돈 입력 통합 부분 */
    // integ_input_money에 input_money, String_to_Int, Affordable_LottoNum 사용
    private static int integ_input_money() {                // input 통합 부분, 재귀함수 처리
        String input = input_money();
        try {
            int money = String_to_Int(input);
            int lotto_num = Affordable_LottoNum(money);
            return lotto_num;
        } catch (IllegalArgumentException e) {
            return integ_input_money();
        }
    }

    private static String input_money() {                   // input을 String으로 받아 반환
        System.out.print("구입금액을 입력해 주세요.\n");
        String input = Console.readLine();
        return input;
    }

    private static int String_to_Int(String str) {      // Input을 String으로 받아서 Int로 반환
        try {
            str = str.trim();                          // 좌우 공백 삭제
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.print("[ERROR] 입력 값은 정수여야 합니다.\n\n");
            throw new IllegalArgumentException();
        }
    }

    private static int Affordable_LottoNum(Integer money) { // money를 받아 구매할 로또 개수 반환
        if (money % 1000 != 0 || money < 0) {
            System.out.print("[ERROR] 로또 구입 금액은 1,000원으로 나누어 떨어지는 양의 정수여야 합니다.\n\n");
            throw new IllegalArgumentException();
        }
        return money / 1000;
    }


    /* 입력 받은 돈으로 로또 번호 랜덤 생성 부분*/
    // makeManyLottos에 makeOneLotto를 사용
    private static void makeManyLottos(Lotto_Wallet Wallet, int n) { // 로또 n개 생성 후 지갑에 넣기
        System.out.print("\n" + n + "개를 구매했습니다.\n");
        for (int i = 0; i < n; i++) {
            Wallet.setLottos(makeOneLotto());
        }
    }

    private static Lotto makeOneLotto() {                            // 로또 하나 생성
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        String numbersString = Arrays.toString(numbers.toArray());
        System.out.print(numbersString + "\n");
        return new Lotto(numbers);
    }


    /* 당첨 복권 번호 입력 부분 */
    // integ_input_winning에 input_winning, Split_to_Int사용
    private static Lotto integ_input_winning() {       // 당첨 복권 입력
        String input = input_winning();
        try {
            List<Integer> winning_nums = Split_to_Int(input);
            Lotto winning_lotto = new Lotto(winning_nums);
            return winning_lotto;
        } catch (IllegalArgumentException e) {
            return integ_input_winning();
        }
    }

    private static String input_winning() {       // 당첨 복권 입력
        System.out.print("\n당첨 번호를 입력해 주세요.\n");
        String input = Console.readLine();
        return input;
    }

    private static List<Integer> Split_to_Int(String input) {       // 당첨 복권 입력
        String[] inputs = input.split(",");
        try {                                           // 일단 숫자가 아닌 경우만 예외처리
            List<Integer> winning_nums = new ArrayList<>();
            for (String numStr : inputs) {
                numStr = numStr.trim();                 // 좌우 공백 삭제
                winning_nums.add(Integer.parseInt(numStr));
            }
            return winning_nums;
        } catch (NumberFormatException e) {
            System.out.print("[ERROR] 로또 번호는 정수여야 합니다.\n");
            throw new IllegalArgumentException();
        }
    }


    /* 보너스 복권 번호 입력 파트 */
    // integ_input_bonus에 input_bonus, String_to_Int, duplicate_check 사용
    private static Integer integ_input_bonus(Lotto lotto) {        // 보너스 번호 입력
        String input = input_bonus();
        try {
            int bonus = String_to_Int(input);
            Lotto.range_check(bonus);
            duplicate_check(lotto, bonus);
            return bonus;
        } catch (IllegalArgumentException e) {
            return integ_input_bonus(lotto);
        }
    }

    private static String input_bonus() {       // 당첨 복권 입력
        System.out.print("\n보너스 번호를 입력해 주세요.\n");
        String input = Console.readLine();
        return input;
    }

    private static void duplicate_check(Lotto lotto, int bonus) {
        List<Integer> numbers = lotto.getNumbers();
        if (numbers.contains(bonus)) {
            System.out.print("[ERROR] 보너스 번호는 로또 번호와 중복이 되어선 안됩니다.\n");
            throw new IllegalArgumentException();
        }
    }
}
