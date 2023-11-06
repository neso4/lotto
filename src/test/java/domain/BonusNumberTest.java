package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {
	@DisplayName("보너스 번호가 정수형인지 검증 아니라면 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"a", "!", " ", ""})
	public void checkBonusNumberIsDigit(String input) {
		// when & then
		assertThatThrownBy(() -> new BonusNumber(input))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
