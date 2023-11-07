package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoDraw;
import lotto.util.Parser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    public LottoDraw createLottoDraw(){
        OutputView.printWinningDrawMessage();
        Lotto winningLotto = createWinningLotto();
        LottoDraw lottoDraw = generateLottoDrawWithWinningLotto(winningLotto);
        return lottoDraw;
    }
    private Lotto createWinningLotto(){
        try {
            String winningLottoNumbersInfo = InputView.readLine();
            List<Integer> winningLottoNumbers = Parser.parseInfoToNumbers(winningLottoNumbersInfo);
            return new Lotto(winningLottoNumbers);
        }catch (IllegalArgumentException exception){
            OutputView.printErrorMessage(exception.getMessage());
            return createWinningLotto();
        }
    }
    private LottoDraw generateLottoDrawWithWinningLotto(final Lotto winningLotto){
        try{
            int bonusNumber = createBonusNumber();
            return new LottoDraw(winningLotto,bonusNumber);
        }catch (IllegalArgumentException exception){
            OutputView.printErrorMessage(exception.getMessage());
            return generateLottoDrawWithWinningLotto(winningLotto);
        }
    }
    private int createBonusNumber(){
        OutputView.printBonusDrawMessage();
        String bonusNumberInfo = InputView.readLine();
        int bonusNumber = Parser.parseInfoToNumber(bonusNumberInfo);
        return bonusNumber;
    }

}

