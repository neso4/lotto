package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.RandomNumberGenerator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {
    RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    List<Integer> randomNumbers;

    @BeforeEach
    void setUp() {
        randomNumbers = randomNumberGenerator.createNewRandomNumber();
    }

    @Test
    void 생성된_로또의_길이_테스트() {
        assertThat(randomNumbers).hasSize(6);
    }

    @Test
    void 생성된_로또번호_범위_테스트() {
        assertThat(randomNumbers).allMatch(value -> value >= 1 && value <= 45);
    }

    @Test
    void 생성된_로또번호_중복_확인_테스트() {
        assertThat(randomNumbers).doesNotHaveDuplicates();
    }
}
