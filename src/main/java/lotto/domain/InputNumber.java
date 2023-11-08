package lotto.domain;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.validator.Validate;

public class InputNumber {

    // 기능 분리 예정
    public List<Integer> InputLottos() {
        String userInput = Console.readLine();
        String[] lotto = userInput.split(",");
        List<Integer> lottos = Arrays.stream(lotto)
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        System.out.println(lottos);
        return lottos;
    }

    public int InputBonus() {
        String BonusNumber = Console.readLine();
        return Integer.parseInt(BonusNumber);
    }
}
