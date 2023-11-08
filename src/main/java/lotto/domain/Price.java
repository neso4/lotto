package lotto.domain;

public class Price {
    private static final int A_TICKET_PRICE = 1000;
    private static final int ZERO = 0;

    private final int price;

    public Price(int purchaseAmount){
        validateZERO(purchaseAmount);
        validatePriceUnit(purchaseAmount);
        this.price = purchaseAmount;
    }

    private void validateZERO(int purchaseAmount){
        if(purchaseAmount == ZERO){
            throw new IllegalArgumentException("[ERROR] 금액이 0원입니다.");
        }
    }

    private void validatePriceUnit(int purchaseAmount){
        if(purchaseAmount % A_TICKET_PRICE != ZERO){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위만 가능합니다.");
        }
    }

    public int getPrice(){
        return price / A_TICKET_PRICE;
    }
}