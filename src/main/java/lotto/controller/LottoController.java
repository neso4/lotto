package lotto.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.model.*;
import lotto.view.ExceptionMessage;
import lotto.view.InputMessage;
import lotto.view.OutputMessage;

public class LottoController {
    public LottoController(){
    }

    private static GetLottoPurchase getLottoPurchase;
    private boolean isGetCost = false;
    private boolean isGetLotto = false;
    private boolean isGetBonus = false;
    private static List<Lotto> lottoList;
    private static List<Integer> lotto = new ArrayList<>();
    private static Lotto prizeLotto;
    private static int bonus;


    public void run(){
        start();
    }

    public void start() {
        int lottoCount = purchaseAmount();
        OutputMessage.printLottoCount(lottoCount);
        lottoList = makeLottoList(lottoCount);
        prizeLotto = makePrizeLotto();
        bonus = makeBonus();
        computePrize(lottoList);
    }

    public int purchaseAmount(){
        while(!isGetCost){
            getLottoPurchase = new GetLottoPurchase(InputMessage.inputPurchase());
            if(getLottoPurchase.getCost() != 0)
                isGetCost = true;
        }
        return getLottoPurchase.computeLottoCount();
    }

    private static List<Lotto> makeLottoList(int lottoCount) {
        lottoList = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottoList.add(makeLotto());
        }
        return lottoList;
    }

    private static Lotto makeLotto() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        lotto = new ArrayList<>();
        lotto = lottoNumbers.setRandomNumbers();
        System.out.println(lotto);
        return new Lotto(lotto);
    }

    public Lotto makePrizeLotto(){
        LottoNumbers lottoNumbers = new LottoNumbers();
        lotto = new ArrayList<>();
        while(!isGetLotto){
            String num = InputMessage.inputPrizeLotto();
            lotto = lottoNumbers.setPrizeNumbers(num);
            if(lottoNumbers.validateRange() && lottoNumbers.validateDuplicate() && lottoNumbers.validateSize()){
                prizeLotto = new Lotto(lotto);
                isGetLotto = true;
            }
        }
        return prizeLotto;
    }

    public int makeBonus(){
        String bonus;
        int bonusNum = 0;
        while(!isGetBonus){
            isGetBonus = true;
            try{
                bonus = InputMessage.inputBonusNumber();
                bonusNum = Integer.parseInt(bonus);
                if(bonusNum < 1 || bonusNum > 45){
                    ExceptionMessage.wrongLottoRangeException();
                    isGetBonus = false;
                }
                if(prizeLotto.checkBonus(bonusNum)){
                    ExceptionMessage.wrongLottoDuplicateException();
                    isGetBonus = false;
                }
            } catch (IllegalArgumentException e){
                ExceptionMessage.wrongLottoException();
                isGetBonus = false;
            }
        }
        return bonusNum;
    }

    private void computePrize(List<Lotto> lottoList){
        ComputePrize computePrize = new ComputePrize(prizeLotto, bonus);
        Map<Prize,Integer> result = setResult();
        OutputMessage.printPrizeStat();

        for (Lotto value : lottoList) {
            Prize prize = computePrize.match(value);
            result.put(prize, result.get(prize) + 1);
        }
        OutputMessage.printResult(result);
        computePrizePercent(result);
    }

    private void computePrizePercent(Map<Prize, Integer> result) {
        double prizePercent = 0;
        for (Prize prize : result.keySet()) {
            prizePercent += Prize.getPrize(prize) * result.get(prize);
        }
        prizePercent = (prizePercent / getLottoPurchase.getCost()) * 100;
        OutputMessage.printPrizePercent(prizePercent);
    }

    private Map<Prize,Integer> setResult(){
        Map<Prize,Integer> result = new HashMap<>();
        for (Prize prize : Prize.values()) {
            result.put(prize,0);
        }
        return result;
    }

}
