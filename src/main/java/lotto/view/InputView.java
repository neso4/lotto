package lotto.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import camp.nextstep.edu.missionutils.Console;
import lotto.util.Constant;





public class InputView {

    public int inputBudget() { // 기능 나누기 -> validator
        int intBudget;
        String budget = Console.readLine();
        try {
            intBudget = Integer.parseInt(budget);
            if (intBudget % Constant.LOTTO_PRICE != Constant.ZERO && intBudget > Constant.ZERO) {
                throw new IllegalArgumentException("1000원 단위 정수로 입력해 주세요."); // 하드코딩 제거
            }
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("1000원 단위 정수로 입력해 주세요."); // 하드코딩 제거
        }
        return intBudget / Constant.LOTTO_PRICE;
    }

    public ArrayList<Integer> inputWinningNumber() { // 기능 나누기 -> validator
        ArrayList<Integer> winningNumber = new ArrayList<>();
        String input = Console.readLine();
        int intNumber;

        if (!input.contains(Constant.COMMA)) { // 입력에 ","가 존재하는지?
            throw new IllegalArgumentException("콤마(,)로 구분하여 1~45 사이의 수를 중복 없이 6개 입력해 주세요."); // 하드 코딩 제거
        }
        String[] splitInput = input.split(Constant.COMMA); // 하드 코딩 제거
        for (String number : splitInput) {
            try {
                intNumber = Integer.parseInt(number);
                if (!(intNumber >= Constant.LOTTO_MINIMUM_NUMBER && intNumber <= Constant.LOTTO_MAXIMUM_NUMBER)) { // 1~45 사이가 아니라면 예외 처리
                    throw new IllegalArgumentException("콤마(,)로 구분하여 1~45 사이의 수를 중복 없이 6개 입력해 주세요."); // 하드 코딩 제거
                }
                winningNumber.add(intNumber);
            } catch (NumberFormatException exception) { // 정수가 아니라면 예외 처리
                throw new IllegalArgumentException("콤마(,)로 구분하여 1~45 사이의 수를 중복 없이 6개 입력해 주세요."); // 하드 코딩 제거
            }
        }

        Set<Integer> SetNumber = new HashSet<>(winningNumber);
        if (SetNumber.size() != Constant.LOTTO_WINNING_NUMBER_LENGTH) { // 중복 유무 검증 및 6개인지 검증
            throw new IllegalArgumentException("콤마(,)로 구분하여 1~45 사이의 수를 중복 없이 6개 입력해 주세요."); // 하드 코딩 제거)
        }

        return winningNumber;

    }

    public int inputBonusNumber(ArrayList<Integer> number) { //기능 나누기 -> validator
        String input = Console.readLine();
        int bonusNumber;
        try {
            bonusNumber = Integer.parseInt(input);
        } catch (NumberFormatException exception) { // 정수인지 검증
            throw new IllegalArgumentException("1~45 사이의 정수를 기존 당첨 번호와 중복없이 입력해 주세요.");
        }

        if (number.contains(bonusNumber)) { // 기존 당첨번호와 중복인지 검증
            throw new IllegalArgumentException("1~45 사이의 정수를 기존 당첨 번호와 중복없이 입력해 주세요.");
        }

        if (!(bonusNumber >= Constant.LOTTO_MINIMUM_NUMBER && bonusNumber <= Constant.LOTTO_MAXIMUM_NUMBER)) { // 1~45 사아인지 검증
            throw new IllegalArgumentException("1~45 사이의 정수를 기존 당첨 번호와 중복없이 입력해 주세요.");
        }

        return bonusNumber;
    }


}
