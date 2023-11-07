package lotto.domain;

public enum LottoResult {
    FIRST(6,0,2000000000),
    SECOND(5,1,30000000),
    THIRD(5,0,1500000),
    FOURTH(4,0,50000),
    FIFTH(3,0,5000),
    ;

    private final int winningNumber;
    private final int bonusNumber;
    private final int winningMoney;

    LottoResult(int winningNumber, int bonusNumber, int winningMoney) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
        this.winningMoney = winningMoney;
    }
}
