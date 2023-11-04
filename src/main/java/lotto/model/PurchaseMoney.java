package lotto.model;

import static lotto.enums.ExceptionMessage.*;

public class PurchaseMoney {

    private static final int ONE_THOUSAND = 1000;

    private int value;

    public PurchaseMoney(String money){
        money = money.replaceAll(" ","");
        validate(money);
        this.value = Integer.valueOf(money);
    }

    public int getValue(){
        return value;
    }

    private void validate(String money){

        if(isEmpty(money)){
            PURCHASE_MONEY_IS_EMPTY_VALUE.throwException();
        }

        if(!isDigit(money)){
            PURCHASE_MONEY_IS_NOT_NUMBER.throwException();
        }

        if(isLessThanOneThousand(money)){
            PURCHASE_MONEY_IS_NOT_OVER_ONE_THOUSAND.throwException();
        }

        if(!isDividedByOneThousand(money)){
            PURCHASE_MONEY_IS_NOT_DIVIDENED_BY_ONE_THOUSAND.throwException();
        }

        // 음수가 입력된 경우
    }

    private boolean isEmpty(final String money){
        return money.isBlank() || money == null;
    }

    private boolean isDigit(String money){

        money = money.replace("-","");

        for (int i = 0; i < money.length(); i++) {
            if(!Character.isDigit(money.charAt(i))) return false;
        }

        return true;
    }

    private boolean isDividedByOneThousand(final String money){

        return Integer.valueOf(money) % ONE_THOUSAND == 0;
    }

    private boolean isLessThanOneThousand(String money){

        return Integer.valueOf(money) < ONE_THOUSAND;
    }
}
