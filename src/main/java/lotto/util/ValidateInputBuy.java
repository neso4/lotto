package lotto.util;

public class ValidateInputBuy {

    public static void isThousand(int inputValue){

        if(inputValue % 1000 != 0){
            throw new IllegalArgumentException("[ERROR] 1000원 단위로만 구입 가능합니다.");
        }

    }

}
