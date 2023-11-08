package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    @DisplayName("구매 로또 번호 출력 테스트")
    void printPurchaseLottoNumbersTest(){
        // given
        List<Integer> testIntegerSets = new ArrayList<>(Arrays.asList(3, 5, 7, 11, 16, 22));

        // when
        DataOutput dataOutputSets = new DataOutput();
        dataOutputSets.printPurchaseLottoNumbers(testIntegerSets);

        // then
        assertThat(output().contains("[3, 5, 7, 11, 16, 22]"));
    }

    @Test
    @DisplayName("로또 게임 진행 메세지 출력 테스트")
    void printProgressMessageTest(){
        // given, when
        DataOutput dataOutputSets = new DataOutput();
        dataOutputSets.printProgressMessage(Progress.PURCHASE);

        // then
        assertThat(output().contains("구입금액을 입력해 주세요."));
    }

    @Test
    @DisplayName("구매 금액 정수 입력 테스트")
    void userInputPaymentIntegerTest() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(Progress.ERROR_INTEGER.getMessage());
        });
    }

    @Test
    @DisplayName("구매 금액 1000원 단위 입력 테스트")
    void userInputPaymentThousandTest() {
        assertSimpleTest(() -> {
            runException("3500");
            assertThat(output()).contains(Progress.ERROR_THOUSAND.getMessage());
        });
    }

    @Test
    @DisplayName("당첨 번호 정수 입력 테스트")
    void makeWinningNumberTest(){
        assertSimpleTest(() -> {
            runException("3000", "r,e,w,q,w,e");
            assertThat(output()).contains(Progress.ERROR_INTEGER.getMessage());
        });
    }

    @Test
    @DisplayName("당첨 번호 입력 정수 범위 테스트")
    void makeWinningNumberDomainTest(){
        assertSimpleTest(() -> {
            runException("3000", "1,2,3,4,5,56");
            assertThat(output()).contains(Progress.ERROR_DOMAIN.getMessage());
        });
    }

    @Test
    @DisplayName("당첨 번호 입력 갯수 테스트")
    void makeWinningNumberSizeTest(){
        assertSimpleTest(() -> {
            runException("3000", "1,2,3,4,5,6,7");
            assertThat(output()).contains(Progress.ERROR_LOTTO_SIZE.getMessage());
        });
    }

    @Test
    @DisplayName("당첨 번호 입력 중복 테스트")
    void makeWinningNumberDuplicateTest(){
        assertSimpleTest(() -> {
            runException("3000", "1,2,3,4,5,5");
            assertThat(output()).contains(Progress.ERROR_DUPLICATE.getMessage());
        });
    }

    @Test
    @DisplayName("보너스 번호 입력 정수 테스트")
    void makeBonusNumberTest(){
        assertSimpleTest(() -> {
            runException("3000", "1,2,3,4,5,6", "a");
            assertThat(output()).contains(Progress.ERROR_INTEGER.getMessage());
        });
    }

    @Test
    @DisplayName("보너스 번호 입력 정수 범위 테스트")
    void makeBonusNumberDomainTest(){
        assertSimpleTest(() -> {
            runException("3000", "1,2,3,4,5,6", "47");
            assertThat(output()).contains(Progress.ERROR_DOMAIN.getMessage());
        });
    }

    @Test
    @DisplayName("보너스 번호 입력 중복 테스트")
    void makeBonusNumberDuplicationTest(){
        assertSimpleTest(() -> {
            runException("3000", "1,2,3,4,5,6", "6");
            assertThat(output()).contains(Progress.ERROR_BONUS_DUPLICATE.getMessage());
        });
    }

    @Test
    @DisplayName("수익률 연산 출력 반올림 테스트1 - 올리는 경우")
    void calcTotalPrizeRateRoundCeilTest() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("3000", "1,2,3,41,43,44", "7");
                    assertThat(output()).contains(
                            "3개를 구매했습니다.",
                            "[1, 2, 3, 4, 5, 6]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 166.67%입니다."
                    );
                },
                List.of(1, 2, 3, 4, 5, 6),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44)
        );
    }

    @Test
    @DisplayName("수익률 연산 출력 반올림 테스트2 - 내리는 경우")
    void calcTotalPrizeRateRoundFloorTest() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("11000", "1,2,3,41,43,44", "7");
                    assertThat(output()).contains(
                            "11개를 구매했습니다.",
                            "[1, 2, 3, 4, 5, 6]",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "[11, 12, 13, 14, 15, 16]",
                            "[12, 13, 14, 15, 16, 17]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 45.45%입니다."
                    );
                },
                List.of(1, 2, 3, 4, 5, 6),
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45),
                List.of(11, 12, 13, 14, 15, 16),
                List.of(12, 13, 14, 15, 16, 17)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
