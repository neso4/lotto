package lotto.domain.model;

public class Money {
    private Integer money;
    public static int unit = 1000;
    public Money () {

    }
    public Money(Integer moneyAmount) {
        checkMoney(moneyAmount);
        this.money = moneyAmount;
    }

    public int getTicketAmount(int givenMoney) {
        return givenMoney / unit;
    }

    public static void checkMoney(int givenMoney) {
        if (givenMoney % unit != 0) {
            throw new IllegalArgumentException("[ERROR]" + givenMoney);
        }
    }

    public int saveMoney(int givenMoney) {
        this.money = givenMoney;
        return givenMoney;
    }

    public double countPrize(int totalPrize) {
        return (totalPrize) / (double) money * 100;
    }

}
