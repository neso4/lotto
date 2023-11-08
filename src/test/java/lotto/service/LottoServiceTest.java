package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.domain.AnswerLotto;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class LottoServiceTest {

	private LottoService lottoService;
	private int money;
	private int lottoPriceSum;
	private double totalReturn;
	private Lotto answerLottoNumbers;
	private AnswerLotto answerLotto;

	@BeforeEach
	void setup() {
		lottoService = new LottoService();
		money = 4000;
		answerLottoNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
		answerLotto = new AnswerLotto(answerLottoNumbers, new BonusNumber(answerLottoNumbers, 7));
	}

	@DisplayName("로또들의 총상금의 합을 확인한다.")
	@MethodSource("createCheckLottoPriceSumMethodParameter")
	@ParameterizedTest()
	void checkLottoPriceSum(Lottos lottos, int expect) {
		lottoPriceSum = lottoService.calculateLottoPriceSum(lottos, answerLotto);

		assertEquals(lottoPriceSum, expect);
	}

	static Stream<Arguments> createCheckLottoPriceSumMethodParameter() {
		Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 11, 12, 13)),
				new Lotto(List.of(1, 2, 3, 4, 12, 13)), new Lotto(List.of(8, 9, 10, 11, 12, 13))));

		return Stream.of(Arguments.of(lottos, 55000));
	}

	@DisplayName("총 수익률을 확인한다.")
	@MethodSource("createCheckTotalReturnMethodParameter")
	@ParameterizedTest()
	void checkTotalReturn(Lottos lottos, double expect) {
		lottoPriceSum = lottoService.calculateLottoPriceSum(lottos, answerLotto);
		totalReturn = lottoService.calculateTotalReturn(money, lottoPriceSum);

		assertEquals(totalReturn, expect);
	}

	static Stream<Arguments> createCheckTotalReturnMethodParameter() {
		Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 11, 12, 13)),
				new Lotto(List.of(1, 2, 10, 11, 12, 13)), new Lotto(List.of(8, 9, 10, 11, 12, 13))));

		return Stream.of(Arguments.of(lottos, 125));
	}

	@DisplayName("당첨 통계 메시지를 확인한다.")
	@MethodSource("createCheckWinningStatisticsMessageMethodParameter")
	@ParameterizedTest
	void checkWinningStatisticsMessage(Lottos lottos, String[] expectedMessages) {
		String winningStatisticsMessage = lottoService.getWinningStatisticsMessage(lottos, answerLotto);

		for (String expectedMessage : expectedMessages) {
			assertThat(winningStatisticsMessage).contains(expectedMessage);
		}
	}

	static Stream<Arguments> createCheckWinningStatisticsMessageMethodParameter() {
		Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 11, 12, 13)),
				new Lotto(List.of(1, 2, 3, 4, 12, 13)), new Lotto(List.of(8, 9, 10, 11, 12, 13))));

		String[] expects = { "3개 일치 (5,000원) - 1개", "4개 일치 (50,000원) - 1개", "5개 일치 (1,500,000원) - 0개",
				"5개 일치, 보너스 볼 일치 (30,000,000원) - 0개", "6개 일치 (2,000,000,000원) - 0개" };
		return Stream.of(Arguments.of(lottos, expects));
	}
}