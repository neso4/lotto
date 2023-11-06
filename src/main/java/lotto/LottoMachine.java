package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.Exception.LottoNumbersException;

public class LottoMachine {

    private int lottoCount;

    public void insertMoney(String input) {
        notParsableNumber(input);
        notValidNumber(Integer.parseInt(input));
        notDividedWithThousand(Integer.parseInt(input));

        lottoCount = Integer.parseInt(input) / 1000;
    }

    private void notParsableNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력한 값을 정수로 변환할 수 없습니다.");
        }
    }

    private void notValidNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("[ERROR] 입력한 값이 0 이하의 숫자입니다.");
        }
    }

    private void notDividedWithThousand(int number) {
        int remainder = number % 1000;

        if (remainder > 0) {
            throw new IllegalArgumentException("[ERROR] 1000으로 나눠 떨어지지 않습니다.");
        }
    }

    public List<Lotto> giveLottoBundle() {
        List<Lotto> lottoBundle = new ArrayList<Lotto>();

        for (int i = 0; i < lottoCount; i++) {
            Lotto processingLotto = new Lotto(generateLottoNumbers());
            lottoBundle.add(processingLotto);
        }

        return lottoBundle;
    }

    List<Integer> generateLottoNumbers() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);

        LottoNumbersException.notSixNumbers(numbers);
        LottoNumbersException.duplicateNumber(numbers);
        for (Integer number : numbers) {
            LottoNumbersException.numberNotInRange(number);
        }

        return numbers;
    }


}
