package lotto.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoServiceTest {

    private LottoService service = new LottoService();

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "6,7,8,9,10,11"})
    @DisplayName("콤마로 구분된 입력 문자열을 List으로 변환")
    void convertToDifferentType(String input) {
        List<Integer> winningNumber = service.reshapeWinningNumber(input);
        assertThat(winningNumber).hasSize(input.length() / 2 + 1)
                .contains(input.charAt(0) - '0')
                .contains(input.charAt(input.length() - 1) - '0');
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,hello", "a,b,c,d,e,f"})
    @DisplayName("로또 번호중 문자 또는 문자열이 들어왔을 때의 테스트")
    void alphabetInLottoNumbers(String input) {
        assertThatThrownBy(() -> service.reshapeWinningNumber(input))
                .isInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {",1,2,3,,4"})
    @DisplayName("로또 번호중 연속된 쉼표 존재할 경우의 테스트")
    void consequentCommaInLottoNumber(String input) {
        assertThatThrownBy(() -> service.reshapeWinningNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 3000, 8000})
    @DisplayName("구매 가능한 복권 장수 계산 테스트")
    void calculateAvailableLottoNumberTest(int spentFee) {
        int numberLotteryTickets = service.calculateAvailableNumberOfLotteryTickets(spentFee);
        assertThat(spentFee).isEqualTo(spentFee / 1000);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 8})
    @DisplayName("구매한 로또장 수 만큼 랜덤값 생성")
    void generateRandomLottoNumsByNTime(int time) {
        List<List<Integer>> myLottoNums = service.generateRandomLottoNums(time);
        assertThat(myLottoNums.size()).isEqualTo(time);
        assertThat(myLottoNums.get(0).size()).isEqualTo(6);
    }

    @ParameterizedTest
    @MethodSource("generateListData")
    @DisplayName("내 번호와 당첨 번호를 비교, 몇개의 숫자와 같은지 결과 보여줌")
    void compareMyNumberWithWinningNumberTest(List<Integer> myNumber, List<Integer> winningNumber, int expected) {
        int result = service.compareMyNumberWithWinningNumber(myNumber, winningNumber);
        // 이거 지금은 enum 잘 몰라서 이렇게 했는데, 상수 enum으로 표현하는거 배우면 enum 클래스로 받아올 수 있음.
        // 그래서 밑에 있는 Assertion도 수정 될 수 있음. + 매개변수도
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> generateListData() {
        return Stream.of(
                Arguments.of(Arrays.asList(3, 8, 10, 23, 33, 42), Arrays.asList(1, 2, 3, 4, 5, 6), 0),
                Arguments.of(Arrays.asList(7, 9, 10, 11, 20, 23), Arrays.asList(11, 12, 13, 14, 20, 23), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("generateMyNumberAndBonusNumber")
    @DisplayName("보너스 번호 확인 하기")
    void checkBonusPointTest(List<Integer> myNumber, int bonusNumber, boolean expectResult) {
        boolean result = service.checkBonusPoint(myNumber, bonusNumber);
        assertThat(result).isEqualTo(expectResult);
    }

    static Stream<Arguments> generateMyNumberAndBonusNumber() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7, false),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), 7, true)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("당첨으로 얻은 총 금액 증가 테스트")
    void increaseTotalIncomeTest(int lottoWinCase) {
        int prev = service.getCaseNum(lottoWinCase);
        service.increaseTotalIncomeTest(lottoWinCase);
        int current = service.getCaseNum(lottoWinCase);
        assertThat(prev).isEqualTo(current - 1);
    }

    @ParameterizedTest
    @CsvSource(value = {"0.625342:62.5%", "0.3212:32.1%"}, delimiter = ':')
    @DisplayName("계산된 수익률을 소수점 둘째 자리의 문자열로 반환하는 테스트")
    void typeConvertIncomeRateTest(float rate, String convertedRate) {
        String convertedIncomeRate = service.typeConvertIncomeRate(rate);
//        double roundedIncomeRate = Math.round(rate * 1000) / 10.0;
//        String converted = Double.toString(roundedIncomeRate);
        assertThat(convertedIncomeRate).isEqualTo(convertedRate);
    }

    @ParameterizedTest
    @MethodSource("generateTotalIncomeAndSpentFee")
    @DisplayName("총 수익과 구입 금액으로 수익률을 계산하는 테스트")
    void calculateIncomeRateTest(int totalIncome, int spentFee, String expected) {
        String incomeRate = service.calculateIncomeRate(totalIncome, spentFee);
        assertThat(incomeRate).isEqualTo(expected);
    }

    static Stream<Arguments> generateTotalIncomeAndSpentFee() {
        return Stream.of(
                Arguments.of(8000, 5000, "62.5%")
        );
    }
}
