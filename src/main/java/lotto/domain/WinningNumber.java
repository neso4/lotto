package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {
    private List<Integer> winningNumber;
    private static final String DELIMITER = ",";
    private static final String OUT_RANGE_ERROR_MESSAGE = "[ERROR] 범위를 벗어난 숫자 입니다.";
    private static final String NULL_INPUT_ERROR_MESSAGE = "[ERROR] 금액을 정확히 입력해 주세요.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "[ERROR] 숫자를 입력해 주세요.";
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 45;

    public WinningNumber(String userValue) {
        validateNullValue(userValue);
        try {
            this.winningNumber = Arrays
                    .stream(userValue.split(DELIMITER))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
        validInRangeNumber();
    }

    void validInRangeNumber(){
        for(int lottoNumber : winningNumber){
            if(lottoNumber < MIN_RANGE || lottoNumber > MAX_RANGE){
                throw new IllegalArgumentException(OUT_RANGE_ERROR_MESSAGE);
            }
        }
    }

    void validateNullValue(String value) {
        if (value.isBlank()) throw new IllegalArgumentException(NULL_INPUT_ERROR_MESSAGE);
    }

    public List<Integer> getWinningNumber(){
        return winningNumber;
    }
}
