package lotto;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import lotto.constant.Message;
import lotto.domain.Lotto;
import lotto.domain.Procedure;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    Procedure procedure;

    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 아래에 추가 테스트 작성 가능

    @Test
    void 정상_금액_입니다() {
        procedure = Procedure.PURCHASE_LOTTO;
        assertAll(
                () -> procedure.checkPossibleError("12000"),
                () -> procedure.checkPossibleError("1000"),
                () -> procedure.checkPossibleError("143000")
        );


    }

    @Test
    void 금액은_한_개만_넣어주세요() {
        procedure = Procedure.PURCHASE_LOTTO;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_ONE_NUMBER),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1, ,"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_ONE_NUMBER)
        );

    }

    @Test
    void 금액은_숫자여야_합니다() {
        procedure = Procedure.PURCHASE_LOTTO;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("금액"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_A_NUMBER),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1q23e4,"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_A_NUMBER)
        );
    }

    @Test
    void 금액은_0보다_커야_합니다() {
        procedure = Procedure.PURCHASE_LOTTO;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("-100"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_MONEY_OUT_OF_RANGE),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("0"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_MONEY_OUT_OF_RANGE)
        );
    }

    @Test
    void 금액은_1000원_단위여야_합니다() {
        procedure = Procedure.PURCHASE_LOTTO;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1200"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_IN_THOUSANDS),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("3451"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_IN_THOUSANDS)
        );

    }

    @Test
    void 정상적인_당첨_번호입니다(){
        procedure = Procedure.DRAW_WINNING_NUMBERS;
        assertAll(
                () -> procedure.checkPossibleError("1, 2, 3, 4, 5, 6"),
                () -> procedure.checkPossibleError("1, 30,45, 20,16,33"),
                () -> procedure.checkPossibleError(" 45,2,33,20, 11,19")
        );
    }


    @Test
    void 당첨번호는_6_자리여야_합니다() {
        procedure = Procedure.DRAW_WINNING_NUMBERS;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1,2,3,"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_SIX_WINNING_NUMBERS),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("10000"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_SIX_WINNING_NUMBERS)
        );
    }

    @Test
    void 당첨번호는_숫자여야_합니다() {
        procedure = Procedure.DRAW_WINNING_NUMBERS;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1, , ㅂㅂ"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_SIX_WINNING_NUMBERS),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("il00"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_SIX_WINNING_NUMBERS)
        );
    }

    @Test
    void 당첨번호는_1부터_45_사이의_수여야_한다() {
        procedure = Procedure.DRAW_WINNING_NUMBERS;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1,23,11,30,50,40"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NUMBER_OUT_OF_RANGE),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1,22,3,16,0,44"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NUMBER_OUT_OF_RANGE)
        );
    }

    @Test
    void 당첨번호는_중복될_수_없습니다(){
        procedure = Procedure.DRAW_WINNING_NUMBERS;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1,2, 3, 4, 5, 5"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_DUPLICATED_NUMBER),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("1, 11, 11, 11, 11, 11"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_DUPLICATED_NUMBER)
        );
    }

    @Test
    void 정상_보너스_번호입니다(){
        procedure = Procedure.DRAW_BONUS_NUMBER;
        assertAll(
                () -> procedure.checkPossibleError("1"),
                () -> procedure.checkPossibleError("45"),
                () -> procedure.checkPossibleError("20")
        );
    }

    @Test
    void 보너스_번호도_숫자여야_합니다(){
        procedure = Procedure.DRAW_BONUS_NUMBER;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("수"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_A_NUMBER),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("!"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NOT_A_NUMBER)
        );
    }

    @Test
    void 보너스_번호도_1에서_45사이의_수입니다(){
        procedure = Procedure.DRAW_BONUS_NUMBER;
        assertAll(
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("0"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NUMBER_OUT_OF_RANGE),
                () -> assertThatThrownBy(() -> procedure.checkPossibleError("46"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(Message.ERROR_NUMBER_OUT_OF_RANGE)
        );
    }
}