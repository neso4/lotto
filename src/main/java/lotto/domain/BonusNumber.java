package lotto.domain;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class BonusNumber {
    WinningNumber winningNumbers;
    //상수(static final) 또는 클래스 변수
    private static int BONUS_NUMBER;

    public void createBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        int bonusNumberInput;

        while(true) {
            try {
                bonusNumberInput = validateFormat(readLine());
                validateRange(bonusNumberInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        compareWinningAndBonus();
        BONUS_NUMBER = bonusNumberInput;
    }

    private int validateFormat(String bonusNumberInput) {
        int bonusNumber;
        try {
            bonusNumber = Integer.parseInt(bonusNumberInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자 1개를 입력해주세요.");
        }
        return bonusNumber;
    }

    private void validateRange(int bonusNumberInput) {
        if (bonusNumberInput < 0 || 45 < bonusNumberInput) {
            throw new IllegalArgumentException("[ERROR] 1~45 범위의 숫자를 입력해주세요.");
        }
    }

    private void compareWinningAndBonus(List<Integer> winningNumbers, int bonusNumberInput) {
        if (winningNumbers.contains(bonusNumberInput)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 중복되지 않는 숫자를 입력해주세요.");
        }
    }

    public int getBONUS_NUMBER() {
        return BONUS_NUMBER;
    }
}
