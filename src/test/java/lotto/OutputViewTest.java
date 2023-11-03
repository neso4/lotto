package lotto;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import lotto.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class OutputViewTest {


    ByteArrayOutputStream captureOutputValues() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        return outputStream;
    }


    @DisplayName("로또 구매 금액 요청 메세지 정상 작동 확인 테스트")
    @Test
    void printRequestBuyAmount_Different_ExceptionThrow() {
        ByteArrayOutputStream output = captureOutputValues();
        OutputView.printRequestBuyAmount();
        assertThat(output.toString()).isEqualTo("구입금액을 입력해 주세요.\n");
    }

    @DisplayName("로또 구매 갯수 메세지 정상 작동 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void printBuyCount_Different_ExceptionThrow(int count){
        ByteArrayOutputStream output = captureOutputValues();
        OutputView.printBuyCount(count);
        assertThat(output.toString()).isEqualTo("\n"+count+"개를 구매했습니다.\n");
    }





}
