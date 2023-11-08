package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoRankTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void findByMatchCountAndBonus_3개일치시_FIFTH반환(boolean isBonusMatch) {
        // given
        int count = 3;
        // when
        LottoRank lottoRank = LottoRank.findRankByMatchCountAndBonus(count, isBonusMatch);
        // then
        assertThat(lottoRank).isEqualTo(LottoRank.FIFTH);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void findByMatchCountAndBonus_4개일치시_FOURTH반환(boolean isBonusMatch) {
        // given
        int count = 4;
        // when
        LottoRank lottoRank = LottoRank.findRankByMatchCountAndBonus(count, isBonusMatch);
        // then
        assertThat(lottoRank).isEqualTo(LottoRank.FOURTH);
    }

    @Test()
    void findByMatchCountAndBonus_5개일치_보너스불일치시_THIRD반환() {
        // given
        int count = 5;
        boolean isBonusMatch = false;
        // when
        LottoRank lottoRank = LottoRank.findRankByMatchCountAndBonus(count, isBonusMatch);
        // then
        assertThat(lottoRank).isEqualTo(LottoRank.THIRD);
    }

    @Test()
    void findByMatchCountAndBonus_5개일치_보너스일치시_SECOND반환() {
        // given
        int count = 5;
        boolean isBonusMatch = true;
        // when
        LottoRank lottoRank = LottoRank.findRankByMatchCountAndBonus(count, isBonusMatch);
        // then
        assertThat(lottoRank).isEqualTo(LottoRank.SECOND);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void findByMatchCountAndBonus_6개일치시_FIRST반환(boolean isBonusMatch) {
        // given
        int count = 6;
        // when
        LottoRank lottoRank = LottoRank.findRankByMatchCountAndBonus(count, isBonusMatch);
        // then
        assertThat(lottoRank).isEqualTo(LottoRank.FIRST);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, true", "0, false", "2, false"})
    void findByMatchCountAndBonus_일치하는_등수_못찾으면_NOTHING반환(int count, boolean isBonusMatch) {
        // when
        LottoRank lottoRank = LottoRank.findRankByMatchCountAndBonus(count, isBonusMatch);
        // then
        assertThat(lottoRank).isEqualTo(LottoRank.NOTHING);
    }

}