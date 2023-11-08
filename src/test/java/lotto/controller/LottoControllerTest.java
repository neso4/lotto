package lotto.controller;

import static lotto.utils.StringUtils.countOccurrences;
import static lotto.view.ErrorMessage.*;
import static lotto.view.ErrorMessage.NOT_A_INTEGER_NUMBER;
import static lotto.view.ErrorMessage.RECEIVED_MONEY_NOT_MULTIPLE_OF_1000;
import static org.assertj.core.api.Assertions.*;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

class LottoControllerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private LottoController lottoController;

    InputStream createUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    @BeforeEach
    public void beforeEach() {
        lottoController = new LottoController();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void afterEach() {
        Console.close();
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("기능15 테스트 : 사용자로 부터 로또 구입 금액을 입력받아 정수로 변환해서 반환한다.")
    void receiveMoneyShouldReturnTotalPurchaseMoneyFromUserInput() {
        // given
        System.setIn(createUserInput("1000"));

        // when
        int result = lottoController.receiveMoney();

        // then
        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("기능16, 기능17 테스트 : 로또 총 구매 비용으로 입력한 값이 숫자가 아니면 예외가 발생한다.")
    void receiveMoneyShouldThrowIllegalArgumentExceptionWhenInputIsNotNumber() {
        // given
        System.setIn(createUserInput("1000j"));

        // when, then
        assertThatThrownBy(() -> lottoController.receiveMoney())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_A_INTEGER_NUMBER.getErrorMessage());
    }

    @Test
    @DisplayName("기능16, 기능18 테스트 : 로또 총 구매 비용으로 입력한 값이 1000의 배수가 아니면 예외가 발생한다.")
    void receiveMoneyShouldThrowIllegalArgumentExceptionWhenInputIsNotMultipleOf1000() {
        // given
        System.setIn(createUserInput("1001"));

        // when, then
        assertThatThrownBy(() -> lottoController.receiveMoney())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(RECEIVED_MONEY_NOT_MULTIPLE_OF_1000.getErrorMessage());
    }

    @Test
    @DisplayName("기능20 테스트 : 0원을 입력했을 때 로또를 하나 구매하고 그 결과를 반환한다.")
    void purchaseOneLottoWhenInputMoneyIs0() {
        // given
        int totalPurchaseAmount = 0;
        int totalPurchaseCount = totalPurchaseAmount / LottoController.ONE_LOTTO_PRICE;
        List<Lotto> lottoList = lottoController.generateLottoList(totalPurchaseCount);

        // when
        lottoController.showPurchaseResult(lottoList, totalPurchaseAmount);
        String result = outputStreamCaptor.toString();
        int count = countOccurrences(result, "[");

        // then
        assertThat(result).contains("0개를 구매했습니다.");
        assertThat(count).isEqualTo(0);
    }


    @Test
    @DisplayName("기능20 테스트 : 1000원을 입력했을 때 로또를 하나 구매하고 그 결과를 반환한다.")
    void purchaseOneLottoWhenInputMoneyIs1000() {
        // given
        int totalPurchaseAmount = 1000;
        int totalPurchaseCount = totalPurchaseAmount / LottoController.ONE_LOTTO_PRICE;
        List<Lotto> lottoList = lottoController.generateLottoList(totalPurchaseCount);

        // when
        lottoController.showPurchaseResult(lottoList, totalPurchaseAmount);
        String result = outputStreamCaptor.toString();
        int count = countOccurrences(result, "[");

        // then
        assertThat(result).contains("1개를 구매했습니다.");
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("기능20 테스트 : 5000원을 입력했을 때 로또 5개를 구매하고 그 결과를 반환한다.")
    void purchaseFiveLottoWhenInputMoneyIs5000() {
        // given
        int totalPurchaseAmount = 5000;
        int totalPurchaseCount = totalPurchaseAmount / LottoController.ONE_LOTTO_PRICE;
        List<Lotto> lottoList = lottoController.generateLottoList(totalPurchaseCount);

        // when
        lottoController.showPurchaseResult(lottoList, totalPurchaseAmount);
        String result = outputStreamCaptor.toString();
        int count = countOccurrences(result, "[");

        // then
        assertThat(result).contains("5개를 구매했습니다.");
        assertThat(count).isEqualTo(5);
    }

    @Test
    @DisplayName("기능08 테스트 : generateLottoList 메서드가 지정된 개수만큼 Lotto 객체를 담은 리스트를 반환한다.")
    void generateLottoListMakeLottoAsManyAsCount() {
        // given
        int count = 5;

        // when
        List<Lotto> lottoList = lottoController.generateLottoList(5);

        // then
        assertThat(lottoList).hasSize(count);
    }

    @Test
    @DisplayName("기능27 테스트 : 당첨 통계가 잘 출력된다.")
    void showStatisticResultCorrectly() {
        // given
        int totalPurchaseCount = 5;
        List<Lotto> lottoList = lottoController.generateLottoList(totalPurchaseCount);
        Lotto answer = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        // when
        lottoController.showStatisticsResult(lottoList, answer, 7);
        String result = outputStreamCaptor.toString();
        int count = countOccurrences(result, "원");

        // then
        assertThat(result).containsSubsequence("당첨 통계", "---", "총 수익률은", "%입니다.");
        assertThat(count).isEqualTo(totalPurchaseCount);
    }

    @Test
    @DisplayName("기능24 테스트 : 유저의 입력값을 보너스 숫자로 변환한다.")
    void registerBonusNumberShouldTransformUserInputToBonusNumber() {
        // given
        System.setIn(createUserInput("7"));

        Lotto answer = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        int result = lottoController.registerBonusNumber(answer);

        // then
        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("기능25 테스트 : 보너스 번호로 정수가 아닌 값을 받은 경우 예외가 발생한다.")
    void registerBonusNumberShouldThrowIllegalArgumentExceptionWhenGivenNotIntegerNumber() {
        // given
        System.setIn(createUserInput("1.1"));
        Lotto answer = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when, then
        assertThatThrownBy(() -> lottoController.registerBonusNumber(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_A_INTEGER_NUMBER.getErrorMessage());
    }

    @Test
    @DisplayName("기능25 테스트 : 보너스 번호로 숫자가 아닌 값을 입력받은 경우 예외가 발생한다.")
    void registerBonusNumberShouldThrowIllegalArgumentExceptionWhenGivenNotNumberValue() {
        // given
        System.setIn(createUserInput("1a"));
        Lotto answer = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when, then
        assertThatThrownBy(() -> lottoController.registerBonusNumber(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_A_INTEGER_NUMBER.getErrorMessage());
    }

    @Test
    @DisplayName("기능26 테스트 : 보너스 번호로 1~45 범위에 있지 않은 값을 입력받은 경우 예외가 발생한다.")
    void registerBonusNumberShouldThrowIllegalArgumentExceptionWhenInputNotInLottoNumRange() {
        // given
        System.setIn(createUserInput("0"));
        Lotto answer = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when, then
        assertThatThrownBy(() -> lottoController.registerBonusNumber(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_RANGE_IS_BETWEEN_ONE_AND_FORTYFIVE.getErrorMessage());
    }

    @Test
    @DisplayName("기능40 테스트 : 보너스 번호로 입력한 값이 이미 정답 조합에 있는 값일 경우 예외가 발생한다.")
    void throwIllegalArgumentExceptionWhenBonusNumberAlreadyContainedAnswerLottoCombination() {
        // given
        System.setIn(createUserInput("1"));
        Lotto answer = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when, then
        assertThatThrownBy(() -> lottoController.registerBonusNumber(answer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(THAT_NUMBER_IS_ALREADY_CONTAINS_ANSWER_COMBINATION.getErrorMessage());
    }


    @Test
    @DisplayName("기능21 테스트 : 사용자의 입력을 로또 객체로 변환한다.")
    void registerWinningNumberShouldReturnLottoInstance() {
        // given
        System.setIn(createUserInput("1,2,3,4,5,6"));

        // when
        Lotto lotto = lottoController.registerWinningLottoCombination();
        String result = lotto.getNumbers();

        // then
        assertThat(result).containsSubsequence("[", "1", "2", "3", "4", "5", "6", "]");
    }

    @Test
    @DisplayName("기능22 테스트 : 당첨 번호 등록할 때 공백이 있어도 정상입력되는지 확인한다.")
    void registerWinningNumberShouldRemoveWhiteSpace() {
        // given
        System.setIn(createUserInput("1, 2, 3, 4, 5, 6"));

        // when
        Lotto lotto = lottoController.registerWinningLottoCombination();
        String result = lotto.getNumbers();

        // then
        assertThat(result).containsSubsequence("[", "1", "2", "3", "4", "5", "6", "]");
    }

    @Test
    @DisplayName("기능23 테스트 : 당첨번호로 입력한 값 중에 정수가 아닌 값이 있으면 에러 발생")
    void registerWinningNumberShouldThrowExceptionWhenGivenNotNumberValue() {
        // given
        System.setIn(createUserInput("1,2,3,4,5,A"));

        // when, then
        assertThatThrownBy(() -> lottoController.registerWinningLottoCombination())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_A_INTEGER_NUMBER.getErrorMessage());
    }

    @Test
    @DisplayName("기능38 테스트 : 당첨 번호 등록할 때 1~45 범위 밖에 있는 정수를 입력하면 예외가 발생한다.")
    void registerWinningNumberShouldThrowIllegalArgumentExceptionWhenGivenWrongRangeValue() {
        // given
        System.setIn(createUserInput("1, 2, 3, 4, 5, 46"));

        // when, then
        assertThatThrownBy(() -> lottoController.registerWinningLottoCombination())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_NUMBER_RANGE_IS_BETWEEN_ONE_AND_FORTYFIVE.getErrorMessage());
    }

}