package lotto.model;

public class PurchasePrice {
    private final int PIECE_OF_LOTTO;
    private static final int LOTTO_PRICE = 1000;

    public PurchasePrice(String priceFromUser){
        isNotDigits(priceFromUser);
        isUnder1000(priceFromUser);
        isNotDivisibleBy1000(priceFromUser);
        this.PIECE_OF_LOTTO = (int) (Long.parseLong(priceFromUser) / 1000);
    }
    public int getCountPieceOfLotto(){
        return this.PIECE_OF_LOTTO;
    }
    private void isNotDigits(String purchasePrice) throws IllegalArgumentException{
        if(!purchasePrice.matches("^[0-9]+$")){
            throw new IllegalArgumentException("[ERROR] 올바른 금액을 입력해주세요.");
        }
    }
    private void isUnder1000(String purchasePrice) throws IllegalArgumentException{
        if(Long.parseLong(purchasePrice) < LOTTO_PRICE){
            throw new IllegalArgumentException("[ERROR] 로또 한장은 1000원입니다.");
        }
    }
    private void isNotDivisibleBy1000(String purchasePrice) throws IllegalArgumentException{
        if(Long.parseLong(purchasePrice) % 1000 != 0){
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1000으로 나누어 떨어져야 합니다.");
        }
    }
}
