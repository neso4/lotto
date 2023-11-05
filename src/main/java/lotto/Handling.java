package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;

public class Handling {

    // 자료형 조작, 클래스 변경?, 위치 변경 필요함
    public static List<Integer> parseNumbers(List<String> lotto) {
        List<Integer> inputNumber = new ArrayList<>();

        for (String lottoNumber : lotto) {
            Lotto.checkLottoNumber(lottoNumber);
            inputNumber.add(Integer.parseInt(lottoNumber));
        }

        return inputNumber;
    }



    public static List<String> split(String lotto) {
        return Arrays.asList(lotto.split(","));
    }
}
