package lotto.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static lotto.input.validator.InputBonusValidator.getInstance;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputBonusTest {
    @DisplayName("사용자가 빈 문자열을 입력하면 예외가 발생한다.")
    @Test
    public void createInputBlank() {
        assertThatThrownBy(() -> getInstance().validate(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자가 특수문자를 입력하면 예외가 발생한다.")
    @Test
    public void createInputSpecialCharacter() {
        assertThatThrownBy(() -> getInstance().validate(";"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자가 알파벳을 입력하면 예외가 발생한다.")
    @Test
    public void createInputAlphabet() {
        assertThatThrownBy(() -> getInstance().validate("a"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자가 올바른 값을 입력하면 입력 값이 그대로 반환된다.")
    @Test
    void createInputSuccess() {
        String input = "2";
        String result = "";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        InputMoney inputMoney = new InputMoney();

        System.setIn(in);
        result = inputMoney.getUserInput();

        Assertions.assertEquals(input, result);
    }
}
