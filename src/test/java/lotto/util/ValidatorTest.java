package lotto.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidatorTest {
    private static Validator validator;

    @BeforeAll
    static void 테스트_초기설정(){
        validator=Validator.INSTANCE;
    }

    @DisplayName("인자값이 숫자로만 이루어져 있는지 검증하는 기능")
    @ParameterizedTest
    @ValueSource(strings={"abcs","1a23","aa1"," 111","11 2","   ","-1"})
    void 숫자_검증_기능(String value){
        assertThatThrownBy(()->validator.numberValidate(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정수 인자값이 1000원으로 나누어지는지 검증하는 기능")
    @ParameterizedTest
    @ValueSource(ints = {1001,-1500,900})
    void 로또구매_값_검증(long buyCash){
        assertThatThrownBy(()->validator.buyCashValidate(buyCash))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
