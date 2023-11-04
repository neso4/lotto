package lotto.dto;
import lotto.Lotto;
import java.util.List;

public class PurchaseLottoDto {
    private List<Lotto> purchaseLottoGroup;
    private static int money;

    public PurchaseLottoDto(int money) {
        PurchaseLottoDto.money = money;
    }

    public List<Lotto> BuyLottoGroup() {
        int numberOfLotto = money / 1000;
        purchaseLotto(numberOfLotto);
        return List.copyOf(purchaseLottoGroup);
    }

    private void purchaseLotto(int numberOfLotto) {
        this.purchaseLottoGroup = Lotto.purchaseLottoEach(numberOfLotto);
    }
}
