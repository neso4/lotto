package lotto.View;

import static lotto.Global.Exception.LOTTO_BONUS_NUMBER_INPUT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import lotto.Global.LottoResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ViewTest {
    static View view = new View(new InputView(), new OutputView());

    @AfterEach
    void cleanUp() {
        Console.close();
    }

    @DisplayName("에러메시지 출력 검증")
    @Test
    void printErrorMessage() {
        view.printErrorMessage(LOTTO_BONUS_NUMBER_INPUT.getErrorPhrase());
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        assertThat(out.toString().contains(LOTTO_BONUS_NUMBER_INPUT.getErrorPhrase()));
    }

    @DisplayName("로또 구입 금액 입력 과정 검층")
    @Test
    void getLottoBuyAmount() {
        System.setIn(new ByteArrayInputStream("1000".getBytes()));
        assertThat(view.getLottoBuyAmout() == 1);
    }

    @DisplayName("로또 생성 결과 출력 검증")
    @Test
    void putLottoSellResult() {
        view.putLottoSellResult(List.of("[1, 2, 3, 4, 5, 6]"));
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        assertThat(out.toString().contains("[1, 2, 3, 4, 5, 6]"));
    }

    @DisplayName("로또 당첨번호 입력 과정 검증")
    @Test
    void getWinningLottoNumber() {
        System.setIn(new ByteArrayInputStream("1,2,3,4,5,6".getBytes()));
        assertThat(view.getWinningLottoNumber(6).size() == 6);
    }

    @DisplayName("로또 보너스번호 입력 과정 검증")
    @Test
    void getBonusLottoNumber() {
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        assertThat(view.getBonusLottoNumber() == 2);
    }

    @DisplayName("로또 결과값 출력 과정 검증")
    @Test
    void putLottoResult() {
        HashMap<String, Integer> result = new HashMap<>();
        for (LottoResult type : LottoResult.values()) {
            result.put(type.name(), 0);
        }

        view.putLottoResult(result, 25.5F);
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        assertThat(out.toString().contains("25.5%"));
    }

}