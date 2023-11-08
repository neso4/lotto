package lotto;

import lotto.domain.PurchaseCost;
import lotto.domain.RateOfReturn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class RateOfReturnTest {


    @DisplayName("로또를 구입할 때, 구입 금액이 잘 저장되는가?")
    @Test
    void rateOfReturnFunctionCheck(){
        PurchaseCost purchaseCost = new PurchaseCost("8000");
        RateOfReturn rateOfReturn = new RateOfReturn(purchaseCost.getPurchaseCost());
        Assertions.assertEquals(rateOfReturn.getRateOfReturn(), 8000);
    }

    @DisplayName("수익률이 잘 계산되는가?")
    @Test
    void calculateRateOfReturnTest(){
        PurchaseCost purchaseCost = new PurchaseCost("8000");
        RateOfReturn rateOfReturn = new RateOfReturn(purchaseCost.getPurchaseCost());
        rateOfReturn.setReward(5000);
        rateOfReturn.setReward(50000);
        rateOfReturn.setRateOfReturn();
        Assertions.assertEquals(rateOfReturn.getRateOfReturn(),687.5);
    }

}
