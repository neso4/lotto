package lotto;

public class Application {
    public static void main(String[] args) {
        BuyLotto test  = new BuyLotto();
        int count = test.getCount();
        
        LottoGenerator userCreate = new LottoGenerator(count);
//        Lotto userLotto = new Lotto(count);
//        System.out.print(userLotto.getNumbers());
//        System.out.print(test.getCount());
    }
}
