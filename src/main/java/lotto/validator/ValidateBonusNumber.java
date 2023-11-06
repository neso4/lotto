package lotto.validator;

import java.util.StringTokenizer;

public class ValidateBonusNumber {
    
    static final int START_INCLUSIVE=1;
    static final int END_INCLUSIVE=45;
    public static void validateBonusNumber(String buyerInput){
        validateEmpty(buyerInput);
        validateConvertable(buyerInput);
        validateNumberRange(buyerInput);
    }

    private static void validateConvertable(String buyerInput) {
        if(!buyerInput.matches("-?\\d+")){
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }
    }

    private static void validateEmpty(String buyerInput) {
        if(buyerInput.isBlank()){
            throw new IllegalArgumentException("[ERROR] 값을 입력해주세요.");
        }
    }

    private static void validateNumberRange(String buyerInput){
        int bonusNumber = Integer.parseInt(buyerInput);
        if(isOutOfRange(bonusNumber)){
            throw new IllegalArgumentException("[ERROR] 숫자의 범위는 1~45 입니다.");
        }

    }
    private static boolean isOutOfRange(int bonusNumber){
        if(bonusNumber<START_INCLUSIVE){
            return true;
        }
        if(bonusNumber>END_INCLUSIVE){
            return true;
        }
        return false;
    }
}
