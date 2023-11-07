package lotto.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoRepositoryTest {

    @Nested
    @DisplayName("get 메소드 테스트")
    class Get {
        @Test
        void 저장된_로또가_잘_GET되는지_테스트() {
            LottoRepository lottoRepository = new LottoRepository();
            Lotto testLotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            Lotto testLotto2 = new Lotto(List.of(10, 11, 12, 13, 14, 15));
            lottoRepository.add(testLotto1);
            lottoRepository.add(testLotto2);

            Assertions.assertAll(() -> {
                assertThat(lottoRepository.get(0)).isEqualTo(testLotto1);
                assertThat(lottoRepository.get(1)).isNotEqualTo(testLotto1);
                assertThat(lottoRepository.get(1)).isEqualTo(testLotto2);
                assertThat(lottoRepository.get(1)).isNotEqualTo(testLotto1);
            });
        }
    }

}