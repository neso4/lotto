package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;
import org.assertj.core.api.ThrowableAssert;

import java.util.*;

public class Money {
    public void showMain() {
        getMoney();
    }

    // 구입금액 입력받기
    public ThrowableAssert.ThrowingCallable getMoney() {
        try {
            System.out.println("구입금액을 입력해주세요.");
            int money = Integer.parseInt(Console.readLine());
            getModulo(money);
            showNum(money / 1000);
        } catch (MyException e) { // 예외처리 반복 https://woojin.tistory.com/74 참고
            System.out.println("[ERROR] 금액이 1000으로 나누어 떨어지지 않습니다.");
            getMoney();
        } catch (NumberFormatException e) { // 숫자가 아닐 경우도 예외처리
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            getMoney();
        }
        return null;
    }

    // 1000으로 나누어 떨어지지 않으면 exception
    public ThrowableAssert.ThrowingCallable getModulo(int money) throws MyException {
        if (money % 1000 != 0) {
            throw new MyException();
        }
        return null;
    }

    // 로또 번호 셀렉
    public ThrowableAssert.ThrowingCallable showNum(int money) throws MyException {
        System.out.println();
        System.out.println(money + "개를 구매했습니다.");
        List<Integer>[] lotto = new List[money * 6];

        for (int i = 0; i < money; i++) {
            List<Integer> list = selectNum();
            lotto[i] = list;
            System.out.println(lotto[i]);
        }

        WinNum win = new WinNum();
        win.showWin(lotto);
        return null;
    }

    // 랜덤 숫자 고르고 Lotto로 넘김
    private List<Integer> selectNum() throws MyException {
        // https://qh5944.tistory.com/152 참고 -, UnsupportedOperationException 해결
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        List<Integer> result = new ArrayList<>();
        result.addAll(numbers);
        Collections.sort(result);
        try {
            new Lotto(result); // Lotto에서 6개, 범위, 중복까지 확인
        } catch (MyException | IllegalArgumentException e) {
            selectNum();
        }
        return result;
    }
}
