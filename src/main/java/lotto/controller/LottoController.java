package lotto.controller;

import lotto.Constants;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.LottoGenerator.startRandom;

public class LottoController {
        int payMoney;
        private static List<Integer> lottoNumbers;
        private static List<Lotto> lottos;
        private static CalculateLotto calculateLotto;
        private static LottoPrice lottoPrice;
        int bonusNumber;

        public void run(){
                try {
                        int count = buyingLotto();
                        OutputView.printLottoCount(count);

                        lottos = makeLottos(count);

                        lottoNumbers = InputView.inputLottoNumbers();
                        Lotto winLotto = new Lotto(lottoNumbers);

                        bonusNumber = InputView.inputBonusNumber();

                        calculateLotto = new CalculateLotto(winLotto, bonusNumber);
                        resultLottos(lottos, calculateLotto, payMoney);

                }catch (IllegalArgumentException e){
                        System.out.println("[ERROR] " + e.getMessage());
                }
        }
        private int buyingLotto() {
                payMoney = InputView.inputMoney();
                LottoPrice lottoPrice = new LottoPrice(payMoney);
                int count = lottoPrice.getLottoCount();
                return count;
        }
        private static List<Lotto> makeLottos(int count){
                lottos = new ArrayList<>();
                for(int i = 0; i< count; i++){
                        lottos.add(makeLotto());
                }
                return lottos;
        }
        private static Lotto makeLotto(){
                lottoNumbers = startRandom();
                System.out.println(lottoNumbers);
                return new Lotto(lottoNumbers);
        }
        private void resultLottos(List<Lotto> lottos, CalculateLotto calculateLotto,int payMoney){
                Map<WinLotto,Integer> result = setResult();
                WinLotto winLotto;

                for (Lotto lotto : lottos) {
                        winLotto = calculateLotto.match(lotto);
                        result.put(winLotto, result.get(winLotto) + 1);
                }
                printResult(result);
                printProfit(result,payMoney);
        }
        private Map<WinLotto,Integer> setResult(){
                Map<WinLotto,Integer> result = new LinkedHashMap<>();
                for(WinLotto winLotto: WinLotto.values()){
                        result.put(winLotto,0);
                }
                return result;
        }
        private void printResult(Map<WinLotto, Integer> result){
                OutputView.printResultMessage();
               for(int i = WinLotto.values().length -1; i >=0; i--){
                       WinLotto.values()[i].printMessage(result.get(WinLotto.values()[i]));
               }
        }
        private void printProfit(Map<WinLotto, Integer> result, int payMoney){
                double profit = 0;
                long totlaPrize = 0;
                for( WinLotto winLotto: result.keySet()){
                        totlaPrize += (long) winLotto.getPrize() * result.get(winLotto);
                }
                profit = (double) totlaPrize / payMoney * Constants.PERCENTAGE;
                OutputView.printProfit(profit);
        }

}
