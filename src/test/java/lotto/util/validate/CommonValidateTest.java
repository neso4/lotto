package lotto.util.validate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommonValidateTest {
    @DisplayName("입력한 값이 숫자가 아닌 경우")
    @Test
    void 일반_검증_숫자_확인(){
        assertThatThrownBy(() -> CommonValidate.checkIsNumber("abc"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
