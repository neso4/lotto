package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumbersTest {

    private static List<Integer> lottoNumberList;

    @Test
    void 로또_랜덤숫자_6개_생성(){
        LottoNumbers lottoNumbers = new LottoNumbers();
        lottoNumberList = lottoNumbers.setRandomNumbers();

        assertThat(lottoNumberList.size()).isEqualTo(6);
    }
}