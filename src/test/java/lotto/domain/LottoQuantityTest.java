package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoQuantityTest {

    LottoQuantity lottoQuantity;

    @BeforeEach
    void setUp() {
        lottoQuantity = new LottoQuantity(3);
    }

    @Test
    @DisplayName("로또 수량을 반환한다.")
    void getQuantityTest() {
        //given

        //when

        //then
        assertThat(lottoQuantity.getQuantity()).isEqualTo(3);
    }

    @Test
    @DisplayName("수량 하나를 뺀 후 0이상이면 true를 반환한다.")
    void decreaseOneByOneTest() {
        //given

        //when
        boolean flag = lottoQuantity.decreaseOneByOne();

        //then
        assertThat(flag).isEqualTo(true);
        assertThat(lottoQuantity).extracting("quantity").isEqualTo(2);
    }
}