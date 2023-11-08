package lotto.domain;

import lotto.domain.dto.input.WinningLottoRequest;

import java.util.Objects;

public class WinningLottoNumbers {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    private WinningLottoNumbers(Lotto winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLottoNumbers from(WinningLottoRequest winningLottoRequest) {
        return new WinningLottoNumbers(winningLottoRequest.winningNumbers(), winningLottoRequest.bonusNumber());
    }

    public boolean containBonusNumber(Lotto userLotto) {
        return userLotto.getNumbers().contains(bonusNumber);
    }

    public int getMatchingCount(Lotto userLotto) {
        return (int) winningNumbers.getNumbers()
                .stream()
                .filter(userLotto.getNumbers()::contains)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WinningLottoNumbers that = (WinningLottoNumbers) o;
        return Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonusNumber);
    }
}
