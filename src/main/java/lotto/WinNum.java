package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

public class WinNum {
    public void showWin(List<Integer>[] lotto) {
        List<Integer> win = selectResult();
        int bonus = getBonus();

        Result result = new Result();
        result.showResult(lotto, win, bonus);
    }

    // 당첨 숫자 입력받기
    private List<Integer> selectResult() {
        List<Integer> result = new ArrayList<>();
        try {
            System.out.println("\n당첨 번호를 입력해 주세요.");
            String[] num = Console.readLine().split(",");
            result = toList(num);
            new Lotto(result);
        } catch (IllegalArgumentException | MyException e) {
            System.out.println(e.getMessage());
            selectResult();
        }
        return result;
    }

    // 문자열 정수형으로 변환하며, Lotto에서 확인
    private List<Integer> toList(String[] s) {
        List<Integer> result = new ArrayList<>(s.length);
        try {
            for (String string : s) {
                result.add(Integer.parseInt(string));
            }
            Collections.sort(result);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자가 아닙니다.");
            selectResult();
        }
        return result;
    }

    // 보너스 번호 입력받기
    private int getBonus() {
        try {
            System.out.println("\n보너스 번호를 입력해 주세요.");
            int num = Integer.parseInt(Console.readLine());
            validate(num);
            return num;
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            getBonus();
        }
        return 0;
    }

    private void validate(int num) {
        if (num < 1 || num > 45) {
            throw new IllegalArgumentException("보너스 번호는 1~45 사이의 숫자여야 합니다.");
        }
    }
}
