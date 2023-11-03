package lotto.model;

import lotto.Lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.utility.Utility;
import lotto.model.Result;

public class Model {
    public static final int LOTTO_PRICE = 1000;

    public List<Lotto> buyLotto(int payment) {
        Utility utility = new Utility();
        List<Lotto> lottos = new ArrayList<>();

        int numberOfLotto = payment / LOTTO_PRICE;

        for(int i = 0 ; i < numberOfLotto ; i++) {
            List<Integer> lottoNumbers = utility.getRandomNumbers(6);
            Lotto newLotto = new Lotto(lottoNumbers);
            lottos.add(newLotto);
        }

        return lottos;
    }

    public int compareNumbers(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        int match = 0;

        for(int number : lottoNumbers) {
            if(winningNumbers.contains(number)) {
                match++;
            }
        }

        return match;
   }
}
