package lotto.model;

import java.util.List;

public class Investor {

    private final Money investMoney;
    private final Money profitMoney;

    private Investor(final Money investMoney, final Money profitMoney) {
        this.investMoney = investMoney;
        this.profitMoney = profitMoney;
    }

    public static Investor createDefault(final String investMoneyInput) {
        Money investMoney = Money.from(investMoneyInput);
        Money profitMoney = Money.createDefault();
        return new Investor(investMoney, profitMoney);
    }

    public List<Lotto> buyLottosFromShop(final Shop shop) {
        return shop.createLottos();
    }

    public void addProfitMoney(final int money) {
        profitMoney.addMoney(money);
    }

    public double calculateProfitRate() {
        return (double) profitMoney.getMoney() / investMoney.getMoney();
    }

    public int getInvestMoney() {
        return investMoney.getMoney();
    }
}
