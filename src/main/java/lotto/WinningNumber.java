package lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.ArrayList;
import java.util.List;

public class WinningNumber {
    private static final String WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final String ERROR_NUMBER_RANGE = "[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String ERROR_NUMBER_SIZE = "[ERROR] 당첨 번호는 6개를 입력해야 합니다.";
    private static final String ERROR_NUMBER_DUPLICATION = "[ERROR] 당첨 번호는 서로 다른 번호를 입력해야 합니다.";
    private static final String ERROR_BONUS_RANGE = "[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String ERROR_DUPLICATION_NUMBER = "[ERROR] 보너스 번호는 당첨 번호와 다른 숫자여야 합니다.";


    public static List<Integer> inputWinningNumber() {
        List<Integer> winningNumbers;
        System.out.println();
        while (true) {
            try {
                System.out.println(WINNING_NUMBER);
                winningNumbers = toWinningNumberList(readLine());
                checkWinningNumber(winningNumbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();
        return winningNumbers;
    }


    public static void checkWinningNumber(List<Integer> winningNumbers) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_NUMBER_SIZE);
        }

        for (int i : winningNumbers) {
            if (i < 1 || i > 45) {
                throw new IllegalArgumentException(ERROR_NUMBER_RANGE);
            }
        }

        if (winningNumbers.size() != winningNumbers.stream().distinct().count()) {
            throw new IllegalArgumentException(ERROR_NUMBER_DUPLICATION);
        }

    }

    public static int inputBonusNumber(List<Integer> winningNumbers) {
        int bonusNumber = 0;
        while (true) {
            try {
                System.out.println(BONUS_NUMBER);
                bonusNumber = Integer.parseInt(readLine());
                checkBonusNumber(winningNumbers, bonusNumber);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();
        return bonusNumber;
    }

    public static void checkBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException(ERROR_BONUS_RANGE);
        }

        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_NUMBER);
        }
    }

    public static List<Integer> toWinningNumberList(String numbers) {
        String[] num = numbers.split(",");
        List<Integer> winningNumbers = new ArrayList<>();
        for (String number : num) {
            winningNumbers.add(Integer.parseInt(number));
        }
        return winningNumbers;
    }

    public static List<MatchNumber> compareToLotto(List<Lotto> lottos, List<Integer> winningNumbers, int bonusNumber) {
        List<MatchNumber> matchNumbers = new ArrayList<>();
        for (Lotto lotto : lottos) {
            long matchWinningNumber = winningNumbers.stream().filter(w -> lotto.getNumbers().contains(w))
                    .count();
            long matchBonusNumber = lotto.getNumbers().stream().filter(l -> l.equals(bonusNumber)).count();
            matchNumbers.add(new MatchNumber(matchWinningNumber, matchBonusNumber));
        }
        return matchNumbers;
    }
}
