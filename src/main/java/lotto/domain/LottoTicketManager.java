package lotto.domain;

import static lotto.constant.ConstantInteger.LOTTO_MAX_NUMBER;
import static lotto.constant.ConstantInteger.LOTTO_MIN_NUMBER;
import static lotto.exception.ExceptionMessage.DUPLICATED_BONUS_NUMBER_MESSAGE;
import static lotto.exception.ExceptionMessage.NOT_IN_RANGE_NUMBER_INPUT_MESSAGE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoTicketManager {

    private Integer price;
    private List<Lotto> lottoTickets;
    private Lotto luckyNumbers;
    private Integer bonusNumber;
    private Map<Rank, Integer> resultMap;

    public LottoTicketManager(){
        initMap();
    }

    public Integer countTickets(String price) {
        return convertPrice(price)/1000 ;
    }

    private Integer convertPrice(String price) {
        this.price = Integer.parseInt(price);
        return this.price;
    }

    public void getUserLottoTickets(List<Lotto> lottoTickets){
        this.lottoTickets=lottoTickets;
    }

    public void getLuckyNumber(Lotto luckyNumbers){
        this.luckyNumbers=luckyNumbers;
    }

    public void getBonusNumber(Integer bonusNumber){
        validate(bonusNumber);
        this.bonusNumber=bonusNumber;
    }

    private void validate(Integer bonusNumber) {
        validateNumberRange(bonusNumber);
        validateDuplicateBonusNumberInLuckyNumber(bonusNumber);
    }

    private void validateNumberRange(Integer bonusNumber){
        if(!isInRangeNumber(bonusNumber)){
            throw new IllegalArgumentException(NOT_IN_RANGE_NUMBER_INPUT_MESSAGE.getMessage());
        }
    }

    private boolean isInRangeNumber(Integer number) {
        return LOTTO_MIN_NUMBER <= number && number <= LOTTO_MAX_NUMBER;
    }

    private void validateDuplicateBonusNumberInLuckyNumber(Integer bonusNumber) {
        if (luckyNumbers.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATED_BONUS_NUMBER_MESSAGE.getMessage());
        }
    }

    private void initMap() {
        resultMap = new HashMap<>();
        for (Rank rank : Rank.values()) {
            resultMap.put(rank, 0);
        }
    }

    public Map<Rank, Integer> makeResult() {
        for (Lotto lotto : lottoTickets) {
            int correctLuckyNumber = correctLuckyNumber(lotto);
            boolean correctBonusNumber = correctBonusNumber(lotto);
            Rank ranking = Rank.matching(correctLuckyNumber, correctBonusNumber);
            resultMap.put(ranking, resultMap.getOrDefault(ranking, 0) + 1);
        }
        return resultMap;
    }

    public Integer correctLuckyNumber(Lotto lotto) {
        return (int) lotto.getNumbers().stream().filter(luckyNumbers.getNumbers()::contains)
                .count();
    }

    public boolean correctBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }


}
