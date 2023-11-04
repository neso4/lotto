package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("로또 금액 입력에 대해서 검증한다.")
public class InputViewTest {

    @ParameterizedTest
    @ValueSource(strings = {"1000", "2000", "3000", "4000", "10000", "100000"})
    @DisplayName("양의 정수만 입력받을 수 있다.")
    void validateInteger_positiveInteger_notThrowAnyException(String strings) {
        InputView inputView = new InputView();
        Assertions.assertThatCode(() -> inputView.validateInteger(strings))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-", "ㄱ", "가나", "피자"})
    @DisplayName("문자 입력값은 예외가 발생한다.")
    void validateInteger_invalidCharacter_exceptionThrown(String strings) {
        InputView inputView = new InputView();
        Assertions.assertThatThrownBy(() -> inputView.validateInteger(strings))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
