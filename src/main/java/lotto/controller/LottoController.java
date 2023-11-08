package lotto.controller;

import lotto.model.Lotto;
import lotto.model.LottoPercentageCalculation;
import lotto.model.constants.LottoPrize;
import lotto.model.RandomIntGenerator;
import lotto.view.LottoView;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static lotto.model.constants.ErrorMessage.ISNOTINTEGER;
import static lotto.model.constants.LottoInformation.BUY_PRICE;
import static lotto.model.constants.LottoPrize.*;

public class LottoController {
    private static LottoView lottoView = new LottoView();
    private static LottoPercentageCalculation lottoPercentageCalculation = new LottoPercentageCalculation();
    private static final RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
    static List<LottoPrize> LottoPrizelist= asList(FIFTH_PRIZE,FOURTH_PRIZE,THIRD_PRIZE,SECOND_PRIZE,FIRST_PRIZE);
    public LottoController() {

    }

    public static void setPrice(){
        randomIntGenerator.isStr();
//       randomIntGenerator.makeLottoNumber(lottoView.askPrice());
    }

    public static void setBuyLottoNumberPrint() {
        lottoView.buyLottoNumberPrint(randomIntGenerator.getLottoNumber());
    }

    public static void setPrizeNumberInput() {
        List<String> prizeNumbers =  lottoView.prizeNumberInput();
        List<Integer> newList = prizeNumbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Lotto lotto = new Lotto(newList);
        lotto.checkSame(lottoView.bonusNumberInput(),randomIntGenerator.getLottoNumber());

        System.out.println();
    }

    public static void winningStatistic() {
        lottoView.seeWinningStatstic(LottoPrizelist);
    }

    public static void PerformanceCalculation() {
        lottoView.seePercentage(lottoPercentageCalculation.percentageCalculation(LottoPrizelist,BUY_PRICE));

    }

    public int checkCount() {
        try{
            return Integer.parseInt(lottoView.askPrice());
        }catch (NumberFormatException e){
            throw new IllegalArgumentException(ISNOTINTEGER.getMessage());
        }
    }
}
