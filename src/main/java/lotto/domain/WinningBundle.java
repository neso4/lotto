package lotto.domain;

import lotto.constants.WinningBundleErrorMessage;
import lotto.dto.WinningBundleDto;

import java.util.List;
import java.util.stream.Collectors;

public class WinningBundle {

    private final List<WinningNumber> winningBundle;

    private WinningBundle(List<WinningNumber> winningBundle) {
        this.winningBundle = winningBundle;
    }

    public static WinningBundle from(List<WinningNumber> winningBundle) {
        validateSize(winningBundle);
        return new WinningBundle(winningBundle);
    }

    private static void validateSize(List<WinningNumber> winningBundle) {
        if (winningBundle.size() != 6) {
            throw new IllegalArgumentException(WinningBundleErrorMessage.TOTAL_SIZE_INVALID.entireMessage());
        }
    }

    public List<Integer> getWinningBundle() {
        return this.winningBundle.stream()
                .map(WinningNumber::toInt)
                .collect(Collectors.toList());
    }

    public WinningBundleDto getWinningBundleDto() {
        return WinningBundleDto.from(getWinningBundle());
    }

    private boolean isContainsBonus(int bonusNumber) {
        return winningBundle.stream()
                .anyMatch(winningNumber -> winningNumber.isEqualsBonusNumber(bonusNumber));
    }

}
