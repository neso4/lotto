package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
	void successScenarioTest_2() {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run("10000", "1,2,3,4,5,6", "7");
				assertThat(output()).contains(
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 8, 11, 31, 41, 42]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[2, 13, 22, 32, 38, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 3, 6, 14, 22, 45]",
					"3개 일치 (5,000원) - 3개",
					"4개 일치 (50,000원) - 0개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
					"6개 일치 (2,000,000,000원) - 0개",
					"총 수익률은 150.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 8, 11, 31, 41, 42),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(2, 13, 22, 32, 38, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 3, 6, 14, 22, 45)
		);
	}


	@Test
	void successScenarioTest_3() {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run("10000", "1,2,3,4,5,6", "7");
				assertThat(output()).contains(
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 8, 11, 31, 41, 42]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[2, 13, 22, 32, 38, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 2, 3, 4, 5, 6]",
					"3개 일치 (5,000원) - 2개",
					"4개 일치 (50,000원) - 0개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
					"6개 일치 (2,000,000,000원) - 1개",
					"총 수익률은 20000100.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 8, 11, 31, 41, 42),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(2, 13, 22, 32, 38, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 2, 3, 4, 5, 6)
		);
	}

	@Test
	void successScenarioTest_4() {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run("10000", "1,2,3,4,5,6", "7");
				assertThat(output()).contains(
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 8, 11, 31, 41, 42]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[2, 13, 22, 32, 38, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 2, 3, 4, 5, 7]",
					"3개 일치 (5,000원) - 2개",
					"4개 일치 (50,000원) - 0개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
					"6개 일치 (2,000,000,000원) - 0개",
					"총 수익률은 300100.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 8, 11, 31, 41, 42),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(2, 13, 22, 32, 38, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 2, 3, 4, 5, 7)
		);
	}

	@Test
	void successScenarioTest_5() {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run("10000", "1,2,3,4,5,6", "7");
				assertThat(output()).contains(
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 2, 3, 5, 43, 45]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[1, 2, 3, 42, 43, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 2, 3, 4, 5, 6]",
					"3개 일치 (5,000원) - 3개",
					"4개 일치 (50,000원) - 1개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
					"6개 일치 (2,000,000,000원) - 1개",
					"총 수익률은 20000650.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 2, 3, 5, 43, 45),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(1, 2, 3, 42, 43, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 2, 3, 4, 5, 6)
		);
	}

	@DisplayName("현금 입력시 잘못된 현금을 입력한 경우 다시 입력 받기")
	@ParameterizedTest
	@ValueSource(strings = {"10232", "dw33", "%%@", "hello", "-1000"})
	void restartScenario_1(final String wrongCash) {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run(wrongCash, "10000", "1,2,3,4,5,6", "7");
				assertThat(output()).contains(
					ERROR_MESSAGE,
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 2, 3, 5, 43, 45]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[1, 2, 3, 42, 43, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 2, 3, 4, 5, 6]",
					"3개 일치 (5,000원) - 3개",
					"4개 일치 (50,000원) - 1개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
					"6개 일치 (2,000,000,000원) - 1개",
					"총 수익률은 20000650.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 2, 3, 5, 43, 45),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(1, 2, 3, 42, 43, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 2, 3, 4, 5, 6)
		);
	}


	@DisplayName("당첨 번호 입력시 1~45를 벗어난 숫자를 입력하는 경우 다시 입력 받기")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5,100", "1,2,3,4,5,49", "-1,2,3,4,5,6", "0,1,2,3,4,5",
		"100,200,300,400,500,600"})
	void restartScenario_2(final String wrongWinnerNumbers) {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run("10000", wrongWinnerNumbers, "1,2,3,4,5,6", "7");
				assertThat(output()).contains(
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 2, 3, 5, 43, 45]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[1, 2, 3, 42, 43, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 2, 3, 4, 5, 6]",
					ERROR_MESSAGE,
					"3개 일치 (5,000원) - 3개",
					"4개 일치 (50,000원) - 1개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
					"6개 일치 (2,000,000,000원) - 1개",
					"총 수익률은 20000650.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 2, 3, 5, 43, 45),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(1, 2, 3, 42, 43, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 2, 3, 4, 5, 6)
		);
	}

	@DisplayName("당첨 번호 입력시 숫자 이외의 값이 포함된 경우 다시 입력 받기")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5,ㅎ", "1,2,3,4,5,^", "아,2,3,4,5,6", "&,1,2,3,4,5",
		" ,1,2,3,4,5"})
	void restartScenario_3(final String wrongWinnerNumbers) {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run("10000", wrongWinnerNumbers, "1,2,3,4,5,6", "7");
				assertThat(output()).contains(
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 2, 3, 5, 43, 45]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[1, 2, 3, 42, 43, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 2, 3, 4, 5, 6]",
					ERROR_MESSAGE,
					"3개 일치 (5,000원) - 3개",
					"4개 일치 (50,000원) - 1개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
					"6개 일치 (2,000,000,000원) - 1개",
					"총 수익률은 20000650.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 2, 3, 5, 43, 45),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(1, 2, 3, 42, 43, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 2, 3, 4, 5, 6)
		);
	}


	@DisplayName("당첨 번호 입력 형식을 준수하지 않은 경우 다시 입력 받기")
	@ParameterizedTest
	@ValueSource(strings = {"1, 2, 3, 4, 5, 6", "1-2-3-4-5-6", ",1,2,3,4,5,6", "1,2,3,4,5,6,",
		"1/2/3/4/5/6"})
	void restartScenario_4(final String wrongWinnerNumbers) {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run("10000", wrongWinnerNumbers, "1,2,3,4,5,6", "7");
				assertThat(output()).contains(
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 2, 3, 5, 43, 45]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[1, 2, 3, 42, 43, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 2, 3, 4, 5, 6]",
					ERROR_MESSAGE,
					"3개 일치 (5,000원) - 3개",
					"4개 일치 (50,000원) - 1개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
					"6개 일치 (2,000,000,000원) - 1개",
					"총 수익률은 20000650.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 2, 3, 5, 43, 45),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(1, 2, 3, 42, 43, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 2, 3, 4, 5, 6)
		);
	}

	@DisplayName("보너스 번호 잘못 입력한 경우 다시 입력 받기")
	@ParameterizedTest
	@ValueSource(strings = {"0", "오야", "hello", "!!,", "-100"})
	void restartScenario_5(final String wrongBonusNumber) {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run("10000", "1,2,3,4,5,6", wrongBonusNumber, "7");
				assertThat(output()).contains(
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 2, 3, 5, 43, 45]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[1, 2, 3, 42, 43, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 2, 3, 4, 5, 6]",
					ERROR_MESSAGE,
					"3개 일치 (5,000원) - 3개",
					"4개 일치 (50,000원) - 1개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
					"6개 일치 (2,000,000,000원) - 1개",
					"총 수익률은 20000650.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 2, 3, 5, 43, 45),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(1, 2, 3, 42, 43, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 2, 3, 4, 5, 6)
		);
	}


	@DisplayName("당첨 번호와 보너스 번호 중복될 경우 다시 입력 받기")
	@ParameterizedTest
	@ValueSource(strings = {"1", "2", "3", "4", "5"})
	void restartScenario_6(final String duplicateBonusNumber) {
		assertRandomUniqueNumbersInRangeTest(
			() -> {
				run("10000", "1,2,3,4,5,6", duplicateBonusNumber, "1,2,3,4,5,6", "7");
				assertThat(output()).contains(
					"10개를 구매했습니다.",
					"[8, 21, 23, 41, 42, 43]",
					"[3, 5, 11, 16, 32, 38]",
					"[7, 11, 16, 35, 36, 44]",
					"[1, 2, 3, 5, 43, 45]",
					"[13, 14, 16, 38, 42, 45]",
					"[7, 11, 30, 40, 42, 43]",
					"[1, 2, 3, 42, 43, 45]",
					"[1, 3, 5, 14, 22, 45]",
					"[1, 2, 3, 14, 22, 45]",
					"[1, 2, 3, 4, 5, 6]",
					ERROR_MESSAGE,
					"3개 일치 (5,000원) - 3개",
					"4개 일치 (50,000원) - 1개",
					"5개 일치 (1,500,000원) - 0개",
					"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
					"6개 일치 (2,000,000,000원) - 1개",
					"총 수익률은 20000650.0%입니다."
				);
			},
			List.of(8, 21, 23, 41, 42, 43),
			List.of(3, 5, 11, 16, 32, 38),
			List.of(7, 11, 16, 35, 36, 44),
			List.of(1, 2, 3, 5, 43, 45),
			List.of(13, 14, 16, 38, 42, 45),
			List.of(7, 11, 30, 40, 42, 43),
			List.of(1, 2, 3, 42, 43, 45),
			List.of(1, 3, 5, 14, 22, 45),
			List.of(1, 2, 3, 14, 22, 45),
			List.of(1, 2, 3, 4, 5, 6)
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
	@DisplayName("공백을 입력한 경우 예외 발생")
	void inputBlankExceptionTest() {
		assertSimpleTest(() -> {
			runException(" ");
			assertThat(output()).contains(ERROR_MESSAGE);
		});
	}

	@DisplayName("1000원 보다 작은 값을 압력한 경우 에외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"100", "200", "300", "999", "-1000"})
	void inputAmountSmallThanUnitExceptionTest(final String amountSmallThanUnit) {
		assertSimpleTest(() -> {
			runException(amountSmallThanUnit);
			assertThat(output()).contains(ERROR_MESSAGE);
		});
	}

	@DisplayName("1000원 으로 나누어 떨어지지 않는 값을 압력한 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"1001", "2002", "3003", "4004", "9999"})
	void inputNotDivisibleByUnitExceptionTest(final String notDivisibleByUnit) {
		assertSimpleTest(() -> {
			runException(notDivisibleByUnit);
			assertThat(output()).contains(ERROR_MESSAGE);
		});
	}


	@DisplayName("입력한 당첨 로또 번호에 숫자 이외의 값을 입력한 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,ㄹ,5", "1,2,3,4, ,5", "1,2,3,4,a,5", "1,2,3,4,%,5"})
	void inputWinnerNumbersIncludeNotNumberExceptionTest(final String wrongWinnerNumbers) {
		assertSimpleTest(
			() -> {
				runException("8000", wrongWinnerNumbers);
				assertThat(output()).contains(ERROR_MESSAGE);
			}
		);
	}

	@DisplayName("입력한 당첨 로또 번호에 1~45 범위를 벗어난 숫자 이외의 값을 입력한 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,0,5", "1,2,3,4,100,5", "1,2,3,4,-12,5", "1,2,3,4,50,5"})
	void inputWinnerNumbersIncludeOverRangedNumberExceptionTest(final String wrongWinnerNumbers) {
		assertSimpleTest(
			() -> {
				runException("8000", wrongWinnerNumbers, "45");
				assertThat(output()).contains(ERROR_MESSAGE);
			}
		);

	}

	@DisplayName("당첨 번호 입력시 입력 형식을 준수하지 않은 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"1, 2, 3, 4, 5, 6", "1/2/3/4/10/5", "1-2-3-4-12-5"})
	void inputWinnerNumbersWithWrongFormatExceptionTest(final String wrongWinnerNumbersFormat) {
		assertSimpleTest(
			() -> {
				runException("8000", wrongWinnerNumbersFormat, "45");
				assertThat(output()).contains(ERROR_MESSAGE);
			}
		);

	}

	@DisplayName("당첨 번호 입력시 중복된 숫자 입력한 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5,5", "1,2,3,4,1,5", "1,2,3,4,4,5", "1,2,3,2,2,5"})
	void inputWinnerNumbersWithDuplicatedNumberExceptionTest(
		final String duplicatedNumberInWinnerNumber) {
		assertSimpleTest(
			() -> {
				runException("8000", duplicatedNumberInWinnerNumber, "45");
				assertThat(output()).contains(ERROR_MESSAGE);
			}
		);
	}

	@DisplayName("당첨 번호 입력시 숫자 6개를 입력하지 않은 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"1,2,3,4,5,6,7", "1,2,3", "1"})
	void inputWinnerNumbersWithWrongNumbersSizeExceptionTest(final String wrongNumbersSize) {
		assertSimpleTest(
			() -> {
				runException("8000", wrongNumbersSize, "45");
				assertThat(output()).contains(ERROR_MESSAGE);
			}
		);
	}

	@DisplayName("보너스 번호 입력시 숫자가 아닌 값 입력할 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"1e", "hello", "ㅎ", "안녕"})
	void inputBonusNumberWithNotNumber(final String wrongBonusNumber) {
		assertSimpleTest(
			() -> {
				runException("8000", "1,2,3,4,5,6", wrongBonusNumber);
				assertThat(output()).contains(ERROR_MESSAGE);
			}
		);
	}

	@DisplayName("보너스 번호 입력시 1~45 범위를 벗어난 숫자 입력 할 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"0", "100", "50", "-2"})
	void inputBonusNumberWithWrongRangeNumber(final String wrongBonusNumber) {
		assertSimpleTest(
			() -> {
				runException("8000", "1,2,3,4,5,6", wrongBonusNumber);
				assertThat(output()).contains(ERROR_MESSAGE);
			}
		);
	}

	@DisplayName("입력한 당첨 번호와 보너스 번호 사이에 중복된 숫자가 있을 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"1", "2", "3", "4", "5"})
	void inputDuplicatedNumberBetweenWinnerNumberAndBonusNumber(final String bonusNumber) {
		assertSimpleTest(
			() -> {
				runException("8000", "1,2,3,4,5,6", bonusNumber);
				assertThat(output()).contains(ERROR_MESSAGE);
			}
		);
	}


	@Override
	public void runMain() {
		Application.main(new String[]{});
	}
}
