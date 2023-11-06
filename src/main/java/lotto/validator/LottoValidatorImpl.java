package lotto.validator;

import java.util.List;
import java.util.StringTokenizer;

import lotto.enumContainer.ErrorOperation;
import lotto.enumContainer.LottoRange;
import lotto.utils.ParserFromString;
import lotto.utils.ParserFromStringImpl;

public class LottoValidatorImpl implements LottoValidator {

	private final ParserFromString parser;
	public LottoValidatorImpl() {
		parser = new ParserFromStringImpl();
	}

	@Override
	public int validatePrice(String lottoPrice) {
		validateEmptyString(lottoPrice);
		validateIsDigit(lottoPrice);
		int parsePrice = validateRange(lottoPrice);

		//해당 객체로 이동
		validateMinimum(parsePrice);
		validateRest(parsePrice);
		return parsePrice;

	}

	@Override
	public List<Integer> validateWinningNumber(StringTokenizer numberSplitter, List<Integer> lottoNumbers) {
		while(numberSplitter.hasMoreTokens()) {
			String lottoNumber = numberSplitter.nextToken();
			validateIsDigit(lottoNumber);
			int parseNumber = validateLottoRange(lottoNumber);
			//중복확인
			lottoNumbers.add(parseNumber);
		}
		return lottoNumbers;
	}

	@Override
	public void validateBonusNumber(String bonusNumber) {
		validateIsDigit(bonusNumber);
		int parseNumber = validateLottoRange(bonusNumber);
	}

	private int validateLottoRange(String lottoNumber) {
		int parseNumber = validateRange(lottoNumber);
		if (parseNumber < LottoRange.START.range()) {
			ErrorOperation.UNDER_ERROR.apply();
		}
		if (parseNumber > LottoRange.END.range()) {
			ErrorOperation.OVER_ERROR.apply();
		}
		return parseNumber;
	}

	private void validateEmptyString(String lottoPrice) {
		if (lottoPrice.isEmpty()) {
			ErrorOperation.EMPTY_ERROR.apply();
		}
	}

	private void validateIsDigit(String lottoPrice) {
		for (int i = 0; i < lottoPrice.length(); i++) {
			char digit = parser.toCharacterParser(lottoPrice, i);
			if (!Character.isDigit(digit)) {
				ErrorOperation.DIGIT_ERROR.apply();
			}
		}
	}

	private int validateRange(String lottoPrice) {
		int parsePrice = 0;
		try {
			parsePrice = parser.toIntPaser(lottoPrice);
		} catch (NumberFormatException e) {
			ErrorOperation.RANGE_ERROR.apply();
		}
		return parsePrice;
	}

	private void validateMinimum(int parsePrice) {
		if (parsePrice < 1000) {
			ErrorOperation.MINIMUM_ERROR.apply();
		}
	}

	private void validateRest(int parsePrice) {
		if (parsePrice % 1000 != 0) {
			ErrorOperation.UNIT_ERROR.apply();
		}
	}
}
