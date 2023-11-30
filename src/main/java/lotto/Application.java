package lotto;

import java.util.List;
import lotto.controller.Calculate;
import lotto.controller.Input;
import lotto.controller.Tool;
import lotto.view.View;

public class Application {

    public static void main(String[] args) {
        int money;
        int numberOfTicket;
        while(true){
            try{
                money = Input.moneyForBuyLotto();
                numberOfTicket = Tool.unsafeDivideBy1000(money);
                break;
            }catch(IllegalArgumentException e){
                View.error(e.toString());
            }
        }

        List<Lotto> lottos = Calculate.makeLottos(numberOfTicket);
        View.lottoList(lottos);

        Lotto prizeNumbers = Input.prizeNumber();
        int bonusNumber = Input.bonusNumber(prizeNumbers);

        var result = Calculate.countOfPrize(prizeNumbers, bonusNumber, lottos);
        var earningRate = Calculate.earningRate(result, money);
        View.resultStatics(result, earningRate);
    }
}
