package lotto.domain;

import lotto.type.LottoResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinLotto {
    private Lotto winLotto;
    private int bonusNumber;
    private int winAmount;

    public WinLotto(List<String> winNumbers) {
        List<Integer> numbers = new ArrayList<>();
        for (String winNumber : winNumbers) {
            numbers.add(Integer.parseInt(winNumber));
        }

        winLotto = new Lotto(numbers);
    }

    // 당첨 개수 카운트
    public List<Integer> getWinCheck(UserLottos userLottos) {
        List<Lotto> lottos = userLottos.getLottos();
        List<Integer> winCheck = new ArrayList<>();

        for (Lotto lotto : lottos) {
            int result = 0;
            for (Integer number : lotto.getNumbers()) {
                if (winLotto.getNumbers().contains(number))
                    result += 1;
            }
            // 보너스 번호에 당첨될 경우 결과값 7증가
            if (lotto.getNumbers().contains(bonusNumber))
                result += 7;
            winCheck.add(result);
        }
        return getWinResult(winCheck);
    }

    // 당첨 기준에 부합하는 결과 리스트 반환
    public List<Integer> getWinResult(List<Integer> winCheck) {
        List<Integer> winResult = new ArrayList<>();
        // 3개 일치 혹은 3개와 보너스번호 일치
        winResult.add(Collections.frequency(winCheck, 3) +
                Collections.frequency(winCheck, 10));
        // 4개 일치 혹은 4개와 보너스번호 일치
        winResult.add(Collections.frequency(winCheck, 4) +
                Collections.frequency(winCheck, 11));
        // 5개 일치
        winResult.add(Collections.frequency(winCheck, 5));
        // 5개와 보너스 번호 일치
        winResult.add(Collections.frequency(winCheck, 12));
        // 6개 일치
        winResult.add(Collections.frequency(winCheck, 6));

        return winResult;
    }

    public String getWinString(List<Integer> winResult) {
        LottoResult[] values = LottoResult.values();
        String winString = "";
        for (int i = 0; i < 5; i++) {
            winString += values[i].getWinMessage() + " - " + winResult.get(i) + "개";
            winAmount += winResult.get(i) * values[i].getReward();
        }

        return winString;
    }

    // 보너스 번호 설정
    public void setBonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public Lotto getWinLotto() {
        return winLotto;
    }

    public int getWinAmount() {
        return winAmount;
    }
}
