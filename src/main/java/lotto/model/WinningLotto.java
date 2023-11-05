package lotto.model;

public class WinningLotto {
    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, BonusNumber bonusNumber) {
//      validateBonusNumberUnique(bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

//    private void validateBonusNumberUnique(int bonusNumber) {
//        if (winningLotto.contains(bonusNumber)) {
//            throw new IllegalArgumentException("당첨 번호화 중복된 번호는 입력할 수 없습니다.");
//        }
//    }
}