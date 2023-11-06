package lotto.domain.User;

import lotto.domain.Lotto.Lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.WinningNumber.WinningNumber;
import lotto.validator.InputValidator;

public class User {
    private int lottoCount;
    private List<Lotto> lotties = new ArrayList<>();
    InputValidator inputValidator = new InputValidator();
    WinningNumber winningNumber = new WinningNumber();

    public User() {
        inputLottoAmount();
        createLotties();
        printLotties();
        //validate
        winningNumber.setWinningNumbers();
    }

    private void inputLottoAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String lottoAmountInput = Console.readLine();
        //validate
        lottoCount = Integer.parseInt(lottoAmountInput) / 1000;
    }

    public void createLotties() {
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> lottoNumber = createLottoNumber();
            Lotto lotto = new Lotto(lottoNumber);
            lotties.add(lotto);
        }
    }

    private void printLotties() {
        System.out.println();
        System.out.println(lottoCount + "개를 구매했습니다.");
        for (int i = 0; i < lottoCount; i++) {
            System.out.println(lotties.get(i).getNumbers());
        }
    }

    private List<Integer> createLottoNumber() {
        List<Integer> lottoNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return lottoNumber;
    }

}
