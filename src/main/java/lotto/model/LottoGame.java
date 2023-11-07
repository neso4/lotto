package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import lotto.utils.Calculator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    Calculator calculator = new Calculator();

    private static final int LOTTO_PRICE = 1000;

    public User InitUser(){
        int amount = InputView.validateInputAmount();
        List<Lotto> lottos = createLottos(amount);
        User user = new User(amount,lottos);
        OutputView.displayLottoList(user);
        return user;
    }

    private List<Integer> generateLotto(){
        List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(1,45,6);
        return lottoNumbers;
    }

    private List<Lotto> createLottos(int amount){
        List<Lotto> lottos = new ArrayList<>();
        int purchaseNumber = amount/LOTTO_PRICE;
        for(int i = 0; i< purchaseNumber; i++){
            Lotto lotto = new Lotto(generateLotto());
            lottos.add(lotto);
        }
        return lottos;
    }

    public List<Integer> createRankList(User user, WinningLotto winningLotto){
        List<Lotto> lottos = user.getUserLottos();
        List<Integer> winngLotto = winningLotto.getNumbers();
        List<Integer> ranks = new ArrayList<>();
        for(Lotto lotto : lottos ){
            List<Integer> purchaselotto = lotto.getNumbers();
            Integer rank = calculator.calculatingRank(winningLotto,purchaselotto);
            ranks.add(rank);
        }
        return ranks;
    }



}
