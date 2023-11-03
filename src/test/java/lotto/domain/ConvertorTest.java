package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ConvertorTest {
    Convertor convertor;

    @ParameterizedTest
    @DisplayName("1-45 사이의 숫자면 리스트 생성")
    @ValueSource(strings = "1,3,18,45,27,15")
    void convertToIntegersSuccess(String input) {
        setUp();
        List<Integer> numbers = convertor.convertToIntegers(input);
        assertThat(numbers).contains(1, 3, 18, 45, 27, 15);
        assertThat(numbers).containsExactly(1, 3, 15, 18, 27, 45);
    }

    @ParameterizedTest
    @DisplayName("1-45 이외의 숫자, 중복돤 숫자, 문자 입력시 예외 발생")
    @ValueSource(strings = {"1,3,18,45,27,46", "1,3,18,45,27,27", "$,3,18,45,27,15", "1,3,18,45,27, "})
    void convertToIntegersFail(String input) {
        setUp();
        assertThatThrownBy(() -> convertor.convertToIntegers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @BeforeEach
    void setUp() {
        Convertor convertor = new Convertor();
        this.convertor = convertor;
    }
}