package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest {

    @AfterEach
    void afterEach () {
        Console.close();
    }

    @Test
    void 구입금액_입력_테스트() {
        String inputPurchaseAmount = "1000";
        InputStream inputStream = new ByteArrayInputStream(inputPurchaseAmount.getBytes());
        System.setIn(inputStream);

        assertThat(InputView.readPurchaseAmount()).isEqualTo(Integer.parseInt(inputPurchaseAmount));
    }

    @Test
    void 구입금액_문자입력_예외테스트() {
        String inputPurchaseAmount = "abcd";
        InputStream inputStream = new ByteArrayInputStream(inputPurchaseAmount.getBytes());
        System.setIn(inputStream);

        assertThatThrownBy(InputView::readPurchaseAmount).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액_1000단위입력_예외테스트() {
        String inputPurchaseAmount = "1222";
        InputStream inputStream = new ByteArrayInputStream(inputPurchaseAmount.getBytes());
        System.setIn(inputStream);

        assertThatThrownBy(InputView::readPurchaseAmount).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액_최대금액_테스트() {
        String inputPurchaseAmount = "100000";
        InputStream inputStream = new ByteArrayInputStream(inputPurchaseAmount.getBytes());
        System.setIn(inputStream);

        assertThat(InputView.readPurchaseAmount()).isEqualTo(Integer.parseInt(inputPurchaseAmount));
    }

    @Test
    void 구입금액_최대금액_예외테스트() {
        String inputPurchaseAmount = "100001";
        InputStream inputStream = new ByteArrayInputStream(inputPurchaseAmount.getBytes());
        System.setIn(inputStream);

        assertThatThrownBy(InputView::readPurchaseAmount).isInstanceOf(IllegalArgumentException.class);
    }



    @Test
    void 당첨번호_입력_테스트() {
        String inputPurchaseAmount = "1,2,3,4,5,6";
        InputStream inputStream = new ByteArrayInputStream(inputPurchaseAmount.getBytes());
        System.setIn(inputStream);

        String[] split = inputPurchaseAmount.split(",");
        List<Integer> inputDataNumbers = Arrays.stream(split).map(Integer::parseInt).toList();
        List<Integer> testWinningNumbers = InputView.readWinningNumberList();

        assertThat(testWinningNumbers.get(0)).isEqualTo(inputDataNumbers.get(0));
        assertThat(testWinningNumbers.get(1)).isEqualTo(inputDataNumbers.get(1));
        assertThat(testWinningNumbers.get(2)).isEqualTo(inputDataNumbers.get(2));
        assertThat(testWinningNumbers.get(3)).isEqualTo(inputDataNumbers.get(3));
        assertThat(testWinningNumbers.get(4)).isEqualTo(inputDataNumbers.get(4));
        assertThat(testWinningNumbers.get(5)).isEqualTo(inputDataNumbers.get(5));
    }

    @Test
    void 보너스번호_입력_테스트() {
        String inputPurchaseAmount = "7";
        InputStream inputStream = new ByteArrayInputStream(inputPurchaseAmount.getBytes());
        System.setIn(inputStream);

        assertThat(InputView.readBonusNumber()).isEqualTo(Integer.parseInt(inputPurchaseAmount));
    }
}