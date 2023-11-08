package lotto.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.domain.Lotto;
import lotto.domain.User;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.view.OutputView;

public class CalculateLottoService {
    private static final int THREE_NUMBER_MATCHES = 3;
    private static final int FOUR_NUMBER_MATCHES = 4;
    private static final int FIVE_NUMBER_MATCHES = 5;
    private static final int SIX_NUMBER_MATCHES = 6;
    private static final int BONUS_ENUM_LABEL = 7;
    private static final int BEFORE_BONUS_NUMBER_INDEX = 6;
    private static final String DECIMAL_FORMAT = "###,##0.0";
    private final OutputView outputView = new OutputView();
    private Map<WinningLotto, Integer> winningCount = new HashMap<>();

	public void calculatingWinning(User user, Lotto lotto) {
		calculatingLottoWinning(user, lotto);
        outputView.printWinningStatics(winningCount);
	}

	public void calculatingLottoWinning(User user, Lotto lotto) {
	    List<UserLottos> userLottos = user.getLottos();
	    for (UserLottos userLotto : userLottos) {
	        countingLottoWinning(user, userLotto.getLottoNumbers(), lotto);
	    }
	}
	public void countingLottoWinning(User user, List<Integer> lottoNumbers, Lotto lotto) {
		int countLottosWithoutBonus = countLottoNumbersExcludingBonusNumber(lottoNumbers, lotto.getLottoNumbers());
		if(isLottoNumberMatchedExcludingNumberFive(countLottosWithoutBonus)) {
			winningLotto(countLottosWithoutBonus, user);
			return;
		}
		if(isLottoNumberMatchedFiveWithBonusNumber(countLottosWithoutBonus, lottoNumbers, lotto)) {
			winningLotto(BONUS_ENUM_LABEL, user);
			return;
		}
		if(isLottoNumberMatchedFive(countLottosWithoutBonus, lottoNumbers, lotto)) {
			winningLotto(countLottosWithoutBonus, user);
			return;
		}
	}
	public int countLottoNumbersExcludingBonusNumber(List<Integer> userNumbers, List<Integer> lottoNumbers) {
	    int count = 0;
	    for (int i = 0; i < BEFORE_BONUS_NUMBER_INDEX; i++) {
	        if (userNumbers.contains(lottoNumbers.get(i))) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public void winningLotto(int countLottoNumbers, User user) {
		inputWinningCount(WinningLotto.FIND.valueOf(countLottoNumbers));
		user.sumWinningPrice(WinningLotto.FIND.valueOf(countLottoNumbers).getPrice());
	}

	public void inputWinningCount(WinningLotto winningLotto) {
		winningCount.put(winningLotto, winningCount.getOrDefault(winningLotto, 0) + 1);
	}
	
    public boolean isLottoNumberMatchedExcludingNumberFive(int countLottoNumbers) {
        if (countLottoNumbers == THREE_NUMBER_MATCHES || countLottoNumbers == FOUR_NUMBER_MATCHES
                || countLottoNumbers == SIX_NUMBER_MATCHES) {
            return true;
        }
        return false;
    }

    public boolean isLottoNumberMatchedFiveWithBonusNumber(int countLottoNumbers, List<Integer> numbers, Lotto lotto) {
        if (countLottoNumbers == FIVE_NUMBER_MATCHES && numbers.contains(lotto.getBonusNumber())) {
            return true;
        }
        return false;
    }

    public boolean isLottoNumberMatchedFive(int countLottoNumbers, List<Integer> numbers, Lotto lotto) {
        if (countLottoNumbers == FIVE_NUMBER_MATCHES && !numbers.contains(lotto.getBonusNumber())) {
            return true;
        }
        return false;
    }
    
    public String getTheRateOfReturnOfLotto(User user, int totalPrice) {
        if (user.getBuyingLottosPrice() == 0) {
            return "0.0";
        }
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
        return decimalFormat.format((double) totalPrice / user.getBuyingLottosPrice() * 100);
    }
    
    
}
