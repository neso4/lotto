package lotto.domain;

import lotto.ui.InputView;

import java.util.List;

public class User {

    private final int PURCHASE_AMOUNT;
    private List<Lotto> lottos;

    private User(int amount,List<Lotto> lottos){
        this.PURCHASE_AMOUNT = amount;
        this.lottos = lottos;
    }

    public static User buyLotto(){
        int amount = InputView.inputAmount();
        List<Lotto> lottos = LottoStore.lottoIssuance(amount);
        return new User(amount,lottos);
    }


    public Long calculateWinningAmount(List<Integer> winningNumbers, int bonusNumber){
        Long totalWinningAmount = 0L;

        for(Lotto lotto: lottos){
            totalWinningAmount += lotto.getLottoRank(winningNumbers, bonusNumber)
                    .getWinningAmount();
        }
        return totalWinningAmount;
    }

    public double calculateRateOfReturn(List<Integer> winningNumbers, int bonusNumber){
        Long totalWinningAmount = calculateWinningAmount(winningNumbers, bonusNumber);

        return ((double)totalWinningAmount/PURCHASE_AMOUNT)*100;
    }




    @Override
    public String toString() {
        return lottos.toString();
    }
}