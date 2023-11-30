package lotto.view;

import lotto.domain.Lottos;
import lotto.dto.LottosIssuanceResponse;
import lotto.dto.MatchResultResponse;
import lotto.dto.RateOfReturnResponse;

public class OutputView {

    public static void printLottosIssuance(LottosIssuanceResponse response) {
        System.out.println(response);
    }

    public static void printRateOfReturn(RateOfReturnResponse response) {
        System.out.println(response);
    }

    public static void printMatchResult(MatchResultResponse response){
        System.out.println(response);
    }

}

