package lotto.util;

import java.util.List;
import static lotto.util.buyingLottoException.*;
import static lotto.util.PrintConstants.*;


public class InputLottoException {
    private final static int MAX_RANGE_OF_LOTTO_LENGTH = 6;
    private final static int MIN_LOTTO_NUMBER = 1;
    private final static int MAX_LOTTO_NUMBER = 45;
    private final static String INPUT_LOTTO_CONTAINS_OTHER_THAN_NUMBERS_COMMA_ERROR ="로또 번호 입력은 숫자와 콤마(,)로 구성되어야 합니다.";
    private final static String INPUT_BONUS_CONTAINS_OTHER_THAN_NUMBERS_ERROR ="보너스 번호 입력은 숫자로 구성 되어야합니다.";
    private final static String LOTTO_NUMBER_MUST_BE_SIX_NUMBERS_ERROR = "로또 번호는 6개 입력해주세요.";
    private final static String DUPLICATED_LOTTO_NUMBER_ERROR = "번호 중에 중복 번호가 있습니다.";
    private final static String LOTTO_NUMBERS_OUT_OF_RANGE = "로또의 범위는 1~45입니다.";
    private final static String DUPLICATED_BONUS_NUMBER = "보너스 번호가 중복됩니다.";
    
    
    public void validateLottoNumbers(List<Integer> lottoNumbers) {
    	validateLottoNumbersListLength(lottoNumbers);
    	validateDuplicatedLottoNumbers(lottoNumbers);
    	validateLottoNumbersRange(lottoNumbers);
    } 
	public void validateInputBonusNumbers(List<Integer> lottoNumbers, int inputBonusNumber) {
		validateBonusNumberRange(inputBonusNumber);
		validateDuplicatedBonusNumber(lottoNumbers, inputBonusNumber);
	}
	public void validateLottoNumbersListLength(List<Integer> lottoNumbers) {
		if(lottoNumbers.size() != MAX_RANGE_OF_LOTTO_LENGTH) {
			throw new IllegalArgumentException(ERROR + LOTTO_NUMBER_MUST_BE_SIX_NUMBERS_ERROR);
		}
	}
	public void validateDuplicatedLottoNumbers(List<Integer> lottoNumbers) {
		    for (int i = 0; i < lottoNumbers.size(); i++) {
		        for (int j = i + 1; j < lottoNumbers.size(); j++) {
		            if (lottoNumbers.get(i).equals(lottoNumbers.get(j))) {
		                throw new IllegalArgumentException(ERROR + DUPLICATED_LOTTO_NUMBER_ERROR);
		            }
		        }
		    }
	}
	
	public void validateLottoNumbersRange(List<Integer> lottoNumbers) {
		for(int lottoNumber : lottoNumbers) {
			if(lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
				throw new IllegalArgumentException(ERROR + LOTTO_NUMBERS_OUT_OF_RANGE);
			}
		}
	}
	
	public void validateBonusNumberRange(int bonusNumber) {
		if(bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException(ERROR+LOTTO_NUMBERS_OUT_OF_RANGE);
		}
	}
	public void validateDuplicatedBonusNumber(List<Integer> lottoNumbers, int bonusNumber) {
		if(lottoNumbers.contains(bonusNumber)) {
			throw new IllegalArgumentException(ERROR+DUPLICATED_BONUS_NUMBER);
		}
	}
	
	public void validateInputLottoNumbersContainsNumbersAndComma(String lottoNumber) {
        for (int i = 0; i < lottoNumber.length(); i++) {
            char c = lottoNumber.charAt(i);
            if (!(Character.isDigit(c) || c == ',')) {
                throw new IllegalArgumentException(ERROR + INPUT_LOTTO_CONTAINS_OTHER_THAN_NUMBERS_COMMA_ERROR);
            }
        }
	}
	
    public void validateInputBonusNumber(String lottoNumber) {
        for (int i = 0; i < lottoNumber.length(); i++) {
            char c = lottoNumber.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException(ERROR + INPUT_BONUS_CONTAINS_OTHER_THAN_NUMBERS_ERROR);
            }
        }
    }
	
}
