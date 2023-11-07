package lotto.domain;


import static lotto.utils.ErrorMessages.LOTTO_NUMBER_DUPLICATION;
import static lotto.utils.ErrorMessages.LOTTO_OUT_OF_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


@DisplayName("Lotto 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottoTest {


    @Test
    void 생성자는_입력값의_길이가_6이_아니면_예외를_던진다() {
        List<Integer> testLottoNumbers = List.of(1, 2, 3, 4, 5, 6, 7);

        Assertions.assertThatThrownBy(() -> new Lotto(testLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LOTTO_OUT_OF_SIZE);
    }

    @Test
    void 생성자는_입력값에_중복이_존재하면_예외를_던진다() {
        List<Integer> testLottoNumbers = List.of(1, 1, 3, 4, 5, 6, 7);

        Assertions.assertThatThrownBy(() -> new Lotto(testLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LOTTO_NUMBER_DUPLICATION);
    }


}
