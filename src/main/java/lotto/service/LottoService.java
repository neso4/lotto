package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.model.Buyer;
import lotto.model.GenerateLotto;
import lotto.model.Lotto;
import lotto.model.LottoResult;
import lotto.model.Lottos;
import lotto.model.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoService {

    public Lottos exchangeLottoTicket(int purchaseAmount){
        Buyer buyerInfo = getBuyerInfo(purchaseAmount);
        return generateLottos(buyerInfo.getLottoQuantity());
    }

    private Buyer getBuyerInfo(int purchaseAmount){
        Buyer buyer=new Buyer(purchaseAmount);
        return buyer;
    }

    private Lottos generateLottos(int lottoQuantity){
        Lottos lottos=new Lottos();
        for(int i=0;i<lottoQuantity;i++){
            Lotto lotto = getLotto();
            lottos.addLotto(lotto);
        }
        return lottos;
    }

    private static Lotto getLotto() {
        GenerateLotto generateLotto=new GenerateLotto();
        Lotto lotto = generateLotto.generateLotto();
        return lotto;
    }

    private List<LottoResult> getResult(Lottos lottos, WinningNumber winningNumber){
        List<LottoResult> results=new ArrayList<>();

        for(Lotto lotto:lottos.getLottos()){
            int matchedCount=countMatchingNumbers(lotto,winningNumber.getWinningNumber());
            boolean bonusNumberMatched=lotto.hasBonusNumber(winningNumber.getBonusNumber());
            results.add(LottoResult.findRank(matchedCount,bonusNumberMatched));
        }
        return results;
    }

    private int countMatchingNumbers(Lotto lotto,Lotto winningNumbers){
        int count=0;
        for(int number: lotto.getLotto()){
            if (winningNumbers.getLotto().contains(number)) {
                count++;
            }
        }
        return count;
    }


}
