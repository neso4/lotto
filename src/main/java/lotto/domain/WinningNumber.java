package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class WinningNumber {
    //상수(static final) 또는 클래스 변수
    private final List<Integer> winningNumbers;
    private final static int THE_NUMBER_OF_WINNING_NUMBER = 6;

    //인스턴스 변수

    //생성자
    public WinningNumber() {
        this.winningNumbers = createWinningNumber();
    }

    private List<Integer> createWinningNumber() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String winningNumber = readLine();
        validateCount(winningNumber);

        List<Integer> result = new ArrayList<>();
        for (String s : winningNumber.split(",")) {
            result.add(Integer.parseInt(s));
        }

        isDuplicate(result);
        return result;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    private void validateCount(String winningNumber) {
        if (winningNumber.split(",").length != THE_NUMBER_OF_WINNING_NUMBER) {
            throw new IllegalStateException("[ERROR] 당첨 번호 6개를 입력해주세요.");
        }
    }

    private void isDuplicate(List<Integer> winningNumber) {
       // 입력받은 당첨번호의 중복체크
       // 예외던짐
    }
}
