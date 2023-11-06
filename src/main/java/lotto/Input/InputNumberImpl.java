package lotto.Input;

import java.util.List;
import java.util.StringTokenizer;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.LottoValidator;

public class InputNumberImpl implements InputNumber {

	private final LottoValidator validator;
	private StringTokenizer numberSplitter;

	public InputNumberImpl(LottoValidator validator) {
		this.validator = validator;
	}

	@Override
	public int inputPurchasePrice() {
		String price = Console.readLine();
		return validator.validatePrice(price);
	}

	@Override
	public List<Integer> inputWinningNumbers(List<Integer> lottoNumbers) {
		numberSplitter = new StringTokenizer(Console.readLine(), ",");
		return validator.validateWinningNumber(numberSplitter, lottoNumbers);
	}

	@Override
	public void inputBonusNumber() {
		String bonusNumber = Console.readLine();
		validator.validateBonusNumber(bonusNumber);
	}



}
