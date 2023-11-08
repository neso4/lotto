package lotto.domain.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SetNumberGeneratorTest {
    SetNumberGenerator setNumberGenerator;

    @DisplayName("설정한 숫자 반환하는 생성기 테스트")
    @Test
    void generate() {
        setNumberGenerator = new SetNumberGenerator(List.of(1,2,3,4,5,6));

        assertThat(setNumberGenerator.generate()).isEqualTo(List.of(1,2,3,4,5,6));
    }
}
