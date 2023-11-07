package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.BonusNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BonusNumberTest {
    @DisplayName("보너스 번호와 일치하는 번호가 있는지 확인하는 기능 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "8:false"}, delimiter = ':')
    void isContainsTest(int number, boolean expected) {
        BonusNumber bonusNumber = new BonusNumber(number);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        boolean actualValue = bonusNumber.isContains(numbers);

        assertEquals(expected, actualValue);
    }
}