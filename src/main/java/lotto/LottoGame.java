package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {
    public void start() {
        OutputView.print(Message.PAYMENT_REQUEST);
        OutputView.print(Message.LINE_BREAK);

        String money = InputView.read();
        validateNumber(money);
        validateMultiple(money);
        int payment = Integer.parseInt(money);

        OutputView.print(Message.LINE_BREAK);
        int countOfLotto = payment / 1000;
        OutputView.print(String.valueOf(countOfLotto));
        OutputView.print(Message.PAYMENT_COMPLETE);
        OutputView.print(Message.LINE_BREAK);

        List<Lotto> lottoTicket = new ArrayList<>();
        for (int count = 0; count < countOfLotto; count++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto attempt = new Lotto(numbers);
            lottoTicket.add(attempt);
        }

        for (Lotto lotto : lottoTicket) {
            lotto.sort();
            OutputView.print(lotto.toString());
            OutputView.print(Message.LINE_BREAK);
        }

        OutputView.print(Message.LINE_BREAK);
        OutputView.print(Message.WINNING_NUMBER_REQUEST);
        OutputView.print(Message.LINE_BREAK);
        String input = InputView.read();
        validateInputRequirement(input);

        List<Integer> winingNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        validateRange(winingNumbers);
        validateSize(winingNumbers);
        validateDuplicate(winingNumbers);

        OutputView.print(Message.LINE_BREAK);
        OutputView.print(Message.BONUS_NUMBER_REQUEST);
        OutputView.print(Message.LINE_BREAK);
        input = InputView.read();
        validateNumber(input);
        validateRangeOne(Integer.parseInt(input));


    }

    public void validateNumber(String input) {
        if (!input.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력하세요");
        }
    }

    public void validateMultiple(String input) {
        if (Stream.of(input)
                .map(Integer::parseInt)
                .anyMatch(number -> number % 1000 != 0)) {
            throw new IllegalArgumentException("[ERROR] 로또 한장이 1000원이므로 구매금액은 1000원 단위로 입력해야 합니다.");
        }
    }

    public void validateInputRequirement(String input) {
        if (!input.matches("^[0-9]+(,[0-9]+)*$")) {
            throw new IllegalArgumentException("[ERROR] 숫자와 쉼표(,)만 입력하세요");
        }
    }

    public void validateRange(List<Integer> numbers) {
        for (int num : numbers) {
            if (num < 1 || num > 45) {
                throw new IllegalArgumentException("[ERROR] 1과 45 사이의 수를 입력하세요");
            }
        }
    }

    public void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 숫자 6개를 입력하지 않았습나다.");
        }
    }

    public void validateDuplicate(List<Integer> numbers) {
        if (numbers.stream()
                .distinct()
                .count() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 숫자가 존재합니다.");
        }
    }

    public void validateRangeOne(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 1과 45 사이의 수를 입력하세요");
        }
    }

    public void validateBonusDuplication(int num, List<Integer> numbers) {
        if (numbers.contains(num)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 서로 중복된 숫자가 없어야 합니다.");
        }
    }
}

class OutputView {
    public static void print(String message) {
        System.out.print(message);
    }
}

class InputView {
    public static String read() {
        return Console.readLine();
    }
}


class Message {
    public static String PAYMENT_REQUEST = "구입금액을 입력해 주세요.";
    public static String PAYMENT_COMPLETE = "개를 구매했습니다.";
    public static String WINNING_NUMBER_REQUEST = "당첨 번호를 입력해 주세요.";
    public static String BONUS_NUMBER_REQUEST = "보너스 번호를 입력해 주세요.";
    public static String WINNING_STATISTICS_RESULT = "당첨 통계";
    public static String DIVIDING_LINE = "-";
    public static String MATCH = "일치";
    public static String LEFT_PARENTHESIS = "(";
    public static String RIGHT_PARENTHESIS = ")";
    public static String WON = "원";
    public static String COUNT = "개";
    public static String COMMA = ",";
    public static String SPACE = " ";
    public static String TOTAL_RETURN = "총 수익률은";
    public static String PERCENT_SIGN = "%";
    public static String END_EXPLAINE = "입니다.";
    public static String LINE_BREAK = "\n";
}
