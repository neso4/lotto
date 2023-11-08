package lotto.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

@DisplayName("자동 로또 발행기 테스트")
class LottoFactoryServiceTest {
    private LottoFactoryService lottoFactoryService;

    @BeforeEach
    void setUp() {
        lottoFactoryService = new LottoFactoryService();
    }

    @DisplayName("생성된 로또는 검증 요구사항을 통과한다")
    @RepeatedTest(50)
    void testGeneratedLottoIsValid() {
        Assertions.assertThatNoException().isThrownBy(lottoFactoryService::generateLotto);
    }
}