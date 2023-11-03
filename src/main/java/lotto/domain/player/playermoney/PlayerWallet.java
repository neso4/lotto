package lotto.domain.player.playermoney;

public class PlayerWallet {
    private HoldingMoney holdingMoney;
    private UsedMoney usedMoney;

    public PlayerWallet(int initialHoldingMoney) {
        this.holdingMoney = new HoldingMoney(initialHoldingMoney);
        this.usedMoney = new UsedMoney(0);
    }

    public void consumeMoneyToLottoTicket(int consumeMoney) {
        usedMoney = usedMoney.updateUsedMoney(consumeMoney);
        holdingMoney = holdingMoney.consumeHoldingMoney(consumeMoney);
    }

    public int getHoldingMoney() {
        return holdingMoney.getHoldingMoney();
    }

    public int getUsedMoney() {
        return usedMoney.getUsedMoney();
    }
}
