package lotto.service;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.ErrorMessage;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;

public class LottoService {
    private static final int LIMIT_NUMBER = 45;
    private static final int PURCHASE_STANDARD = 1000;

    public LottoResult lotteryStatistics(List<Lotto> lottos, Lotto winningNumbers, int bonusNumber) {
        LottoResult result = new LottoResult();
        for (Lotto lotto : lottos) {
            int correctCnt = count(lotto.getNumbers(), winningNumbers.getNumbers());
            boolean bonus = false;
            if (correctCnt == 5) {
                bonus = isBonusNumberCorrect(lotto.getNumbers(), bonusNumber);
            }
            if (correctCnt >= 3) {
                result.calculate(correctCnt, bonus);
            }
        }
        return result;
    }

    public int count(List<Integer> numbers, List<Integer> winningNumbers) {
        int cnt = 0;
        for (int number : numbers) {
            if (winningNumbers.contains(number)) {
                cnt++;
            }
        }
        return cnt;
    }

    public boolean isBonusNumberCorrect(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            return true;
        }
        return false;
    }

    public double getROI(double purchaseAmount, double total) {
        return (total / purchaseAmount) * 100.0;
    }

    public int inputPurchaseAmount() {
        while (true) {
            String input = Console.readLine();
            try {
                containsNonNumericCharacters(input);
                isPurchaseAmountDivideBy1000(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Lotto> provideLotto(int lottoCnt) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCnt; i++) {
            lottos.add(new Lotto(new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6))));
        }
        return lottos;
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public Lotto inputWinningNumbers() {
        while (true) {
            String input = Console.readLine().trim().replaceAll("\\s", "");
            List<Integer> winningNumbers = new ArrayList<>();
            try {
                for (String number : input.split(",")) {
                    validateOneNumber(number);
                    winningNumbers.add(Integer.parseInt(number));
                }
                return new Lotto(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int inputBonusNumber(Lotto winningNumbers) {
        while (true) {
            String input = Console.readLine();
            try {
                validateOneNumber(input);
                isWinningNumbersContainBonusNumber(winningNumbers.getNumbers(), input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void validateOneNumber(String input) {
        containsNonNumericCharacters(input);
        isNumberOverLimit(input);
        isNumberBelowZero(input);
    }

    public void isNumberBelowZero(String input) {
        if (Integer.parseInt(input) <= 0) {
            throw new IllegalArgumentException(ErrorMessage.CONTAINS_ZERO.getMessage());
        }
    }

    public void isWinningNumbersContainBonusNumber(List<Integer> winningNumbers, String bonusNumber) {
        if (winningNumbers.contains(Integer.parseInt(bonusNumber))) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_WITH_WINNING_NUMBERS.getMessage());
        }
    }

    public void containsNonNumericCharacters(String input) {
        if (!input.matches("[0-9\\s]+")) {
            throw new IllegalArgumentException(ErrorMessage.CONTAINS_NON_NUMERIC_CHARACTERS.getMessage());
        }
    }

    public void isPurchaseAmountDivideBy1000(String input) {
        int inputPurchaseAmount = Integer.parseInt(input);
        if (inputPurchaseAmount % PURCHASE_STANDARD > 0 || inputPurchaseAmount == 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_NOT_DIVIDE_BY_1000.getMessage());
        }
    }

    public void isNumberOverLimit(String input) {
        if (Integer.parseInt(input) > LIMIT_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OVER_LIMIT.getMessage());
        }
    }
}
