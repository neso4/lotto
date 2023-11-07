package lotto.domain;

import lotto.values.CorrectNumber;
import lotto.vo.BonusNumber;
import lotto.vo.Lotto;
import lotto.vo.WinningNumber;
import lotto.vo.WinningResult;

import java.util.List;

import static lotto.values.CorrectNumber.SECOND_PLACE;
import static lotto.values.CorrectNumber.THIRD_PLACE;


public class WinningChecker {
    WinningResult winningResult;
    public WinningChecker(WinningNumber w, BonusNumber b, List<Lotto> lottoPackage){
        winningResult = new WinningResult();
        for(Lotto lotto: lottoPackage){
            CorrectNumber place = w.compareLotto(lotto);
            if(place==SECOND_PLACE || place==THIRD_PLACE) place = b.compareLotto(lotto);
            if(place!=null) winningResult.win(place);
        }
    }
}
