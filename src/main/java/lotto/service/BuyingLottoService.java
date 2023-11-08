package lotto.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;
import lotto.service.exception.NumberFormattingExceptionHandler;
import lotto.service.exception.InputErrorMessage;
import lotto.service.exception.IllegalArgumentExceptionHandler;

public class BuyingLottoService {

    public int validateInputMoney(String input){
        int validMoney;
        try {
            verifyNumberFormat(input);
            verifyMoneyUnit(convertStringtoInt(input));
        } catch (NumberFormattingExceptionHandler | IllegalArgumentExceptionHandler e){
            System.out.println(e.getMessage());
            return -1;
        }
        validMoney = convertStringtoInt(input);

        return validMoney;
    }

    public int convertStringtoInt(String input){
        return Integer.parseInt(input);
    }

    private void verifyNumberFormat(String moneyInput){
        try{
            Integer.parseInt(moneyInput);
        }catch(NumberFormatException e) {
            throw new NumberFormattingExceptionHandler(InputErrorMessage.INVALID_NUMBER);
        }
    }

    private void verifyMoneyUnit(int moneyInput){
        if (moneyInput % 1000 != 0) {
            throw new IllegalArgumentExceptionHandler(InputErrorMessage.WRONG_INPUT_MONEY_UNIT);
        }
    }

    public List<Integer> sortLottoNumber(Lotto lotto){
        List<Integer> sortingNumber = new ArrayList<>(lotto.getNumbers());
        Collections.sort(sortingNumber);
        return sortingNumber;
    }
}
