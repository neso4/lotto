package lotto.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidationTest {

    InputValidation inputValidation = new InputValidation();

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    public void 빈문자열(String input) {
        //then (기능 작동 후 결과)
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                inputValidation.validationMoney(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1a", "12345b", "a123", "*24809", ".,;'"})
    public void 숫자검사(String input) {
        //then (기능 작동 후 결과)
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                inputValidation.validationMoney(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3333333333", "43525234324"})
    public void Integer_형변환_검사(String input) {
        //then (기능 작동 후 결과)
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                inputValidation.validationMoney(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-123"})
    public void 숫자_0이하_검사(String input) {
        //then (기능 작동 후 결과)
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                inputValidation.validationMoney(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10", "100", "123", "999", "1002", "10004"})
    public void 구매단위_검사(String input) {
        //then (기능 작동 후 결과)
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                inputValidation.validationMoney(input));
    }
}