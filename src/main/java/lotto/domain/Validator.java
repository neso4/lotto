package lotto.domain;

public class Validator {

    public Validator() {
    }

    public int validatePurchaseAmountInput(String input) throws IllegalArgumentException {
        validateInteger(input);
        int amount = Integer.parseInt(input);
        validatePositiveInteger(amount);
        validateAmountInThousands(amount);
        return amount;
    }

    private void validateInteger(String input) {
        try{
            Integer.parseInt(input);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 정수를 입력해야 합니다.");
        }
    }

    private void validatePositiveInteger(int lottoPurchaseCost) {
        if(lottoPurchaseCost<1){
            throw new IllegalArgumentException("[ERROR] 양의 정수를 입력해야 합니다.");
        }
    }

    private void validateAmountInThousands(int lottoPurchaseCost) {
        if(lottoPurchaseCost%1000!=0){
            throw new IllegalArgumentException("[ERROR] 1000의 배수인 정수를 입력해야 합니다.");
        }
    }




}
