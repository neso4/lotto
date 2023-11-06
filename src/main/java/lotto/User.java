package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.stream.Stream;

class User {

    public int inputPurchasingVolume() {
        int money = 0;
        String input = Console.readLine();
            try {
                money = Integer.parseInt(input);
                validateMoney(money);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 구매 금액은 숫자로 입력되어야 합니다. 특수 문자가 포함되어 있으면 지워주세요.");
            }
        return money / 1000;
    }

    public List<Integer> inputWinningNumbers() {
        String input = Console.readLine();
        Integer[] inputNums;
        try {
            inputNums = Stream.of(input.split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 올바른 형식의 당첨 숫자가 아닙니다.");
        }
        for (int i : inputNums) {
            if (i > 45 || i < 0) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이에 존재해야 합니다.");
            }
        }
        List<Integer> numbers = new ArrayList<>(Arrays.asList(inputNums));
        return numbers;
    }

    public int inputBonusNumber() {
        String input = Console.readLine();
        int number = 0;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 올바른 형식의 숫자가 아닙니다.");
        }
        return number;
    }

    public List<Integer> getLottoNumbers() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return numbers;
    }

    public List<Lotto> getLottoAsMuchAsVolume(int volume) {
        List<Lotto> allLotto = new ArrayList<>();
        for (int i = 0; i < volume; i++) {
            List<Integer> lottoNumbers = new ArrayList<>(getLottoNumbers());
            Collections.sort(lottoNumbers);
            Lotto lotto = new Lotto(lottoNumbers);
            allLotto.add(lotto);
        }
        return allLotto;
    }

    public void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 이미 당첨 숫자에 있는 번호는 보너스 번호가 될수 없습니다.");
        }
        if (bonusNumber > 45 || bonusNumber < 0) {
            throw new IllegalArgumentException("[ERROR] 보너스 숫자는 1~45 사이에 존재해야 합니다.");
        }
    }

    public void validateMoney(int money) {
        if (money % 1000 != 0 && money < 1000) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 반드시 1000원 단위어야만 합니다.");
        }
    }
}