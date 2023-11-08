package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AllLottoTest {
    @Test
    void 숫자만큼_로또_발행여부확인(){
        Integer count = 8;

        AllLotto allLotto = new AllLotto(count);
        assertThatThrownBy(()-> allLotto.getLotto(8))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

}