package lotto.controller;

import static lotto.utility.Constants.ERROR_MESSAGE_1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.Application;
import lotto.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserInputTest extends NsTest {
    // 아래에 추가 테스트 작성 가능
    @DisplayName("구매 가격이 음수인 경우 예외가 발생한다.")
    @Test
    void createBonusLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> UserException.validateAll("-1000")) // 중복된 번호를 사용해서 테스트
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_1);
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

}