package lotto.model.Lotto;

import java.util.ArrayList;
import java.util.List;

public class WinningLotto {
    private final List<Integer> winningLottoNumbers;


    WinningLotto(List<Integer> winningLottoNumbers) {
        this.winningLottoNumbers = winningLottoNumbers;
    }

    public static WinningLotto create(String winningLottoNumbers) {
        return new WinningLotto(splitWinningLottoNumbers(winningLottoNumbers));
    }

    private static List<Integer> splitWinningLottoNumbers(String winningLottoNumbers) {
        List<Integer> inputWinningLottoNumbers = new ArrayList<>();
        for (String winningLottoNumber : winningLottoNumbers.split(",")) {
            inputWinningLottoNumbers.add(Integer.parseInt(winningLottoNumber));
        }
        return inputWinningLottoNumbers;
    }

    public List<Integer> getWinningLottoNumbers() {
        return winningLottoNumbers;
    }
}
