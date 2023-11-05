package lotto;

import java.util.List;
import lotto.UI.InputBonus;
import lotto.UI.InputNum;
import lotto.UI.InputPrice;
import lotto.UI.Output;
import lotto.domain.Lotto;
import lotto.domain.RandomNum;

public class Application {
    public static void main(String[] args) {
        InputPrice inputPrice = new InputPrice();
        InputNum inputNum = new InputNum();
        InputBonus inputBonus = new InputBonus();
        Output output = new Output();
        RandomNum randomNum = new RandomNum();

        int price = inputPrice.inputPrice();

        List<List<Integer>> randomLottoNumbers = randomNum.buyLotto(price);
        output.printRandomLottoNumbers(randomLottoNumbers);

        List<Integer> lottoNumbers = inputNum.inputLottoNumbers();
        int bonusNumber = inputBonus.inputBonusNumber(lottoNumbers);

        Lotto lotto = new Lotto(lottoNumbers);




    }
}
