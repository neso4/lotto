package lotto.controller;

import java.util.List;
import lotto.domain.*;
import lotto.domain.generator.LottoGenerator;

import lotto.view.InputMoneySpentOnLottoView;
import lotto.view.InputWinningNumbersView;
import lotto.view.InputBonusNumberView;

import static lotto.view.OutputNumberOfBoughtLottoView.printBoughtLottos;
import static lotto.view.OutputLottoNumbersView.printLottos;
//import static lotto.view.OutputLottoResultsView..printBoughtLottos;

public class LottoController {

    public void start(){
        User lotto = getMoney();
        printBoughtLottos(lotto);

        Lottos lottos = new Lottos(getLottos(lotto.getNumberOfLotto()));
        printLottos(lottos);

        Lotto win =
    }

    public User getMoney(){
        InputMoneySpentOnLottoView spentMoney = new InputMoneySpentOnLottoView();
        int money = spentMoney.getValue();

        return new User(money);
    }

    public List<Lotto> getLottos(int numOfLotto){
        LottoGenerator lottoMaker = new LottoGenerator();
        return lottoMaker.FullLottoMaker(numOfLotto);
    }

    public Lotto getWinningLotto(){
        InputWinningNumbersView winningNumber = new InputWinningNumbersView();
        List<Integer> win = winningNumber.getValue();

        return new Lotto(win);
    }
}
