package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerLottoTest {

    @Nested
    @DisplayName("최종 성적을 계산하는 기능 테스트")
    class calculateResultTest {
        @Test
        @DisplayName("1 ~ 5등을 각각 1회씩 획득")
        void randomTest1() {
            // given
            PlayerLotto playerLotto = createPlayerLotto();
            Lotto winningNumber = new Lotto(List.of(1, 3, 2, 4, 7, 5));
            LottoNumber bonusNumber = new LottoNumber(27);
            // when
            FinalGrade finalGrade = playerLotto.calculateFinalGrade(winningNumber, bonusNumber);
            EnumMap<Rank, Integer> eachRankCounts = finalGrade.getEachRankCounts();
            // then
            assertEachRankCounts(eachRankCounts, 1, 1, 1, 1, 1, 0);
        }

        @Test
        @DisplayName("2, 4, 5등을 각각 1회씩 획득")
        void randomTest2() {
            // given
            PlayerLotto playerLotto = createPlayerLotto();
            Lotto winningNumber = new Lotto(List.of(1, 2, 3, 5, 27, 9));
            LottoNumber bonusNumber = new LottoNumber(7);
            // when
            FinalGrade finalGrade = playerLotto.calculateFinalGrade(winningNumber, bonusNumber);
            EnumMap<Rank, Integer> eachRankCounts = finalGrade.getEachRankCounts();
            System.out.println(eachRankCounts);
            // then
            assertEachRankCounts(eachRankCounts, 0, 1, 0, 1, 1, 2);
        }

        @ParameterizedTest
        @CsvSource(value = {"1,2,3,4,5,6:1,2,3,4,5,6:6",
                "11,12,13,14,15,16:16,15,14,13,12,11:23"}, delimiter = ':')
        @DisplayName("1등 테스트 - 6개 일치, 보너스 볼 일치 여부 상관 없음")
        void firstRankWhetherBonusMatchOrNot(String playerLottoInput, String winningNumberInput, int bonusNumberInput) {
            // given
            PlayerLotto playerLotto = new PlayerLotto(List.of(new Lotto(parser(playerLottoInput))));
            Lotto winningNumber = new Lotto(parser(winningNumberInput));
            LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
            // when
            FinalGrade finalGrade = playerLotto.calculateFinalGrade(winningNumber, bonusNumber);
            EnumMap<Rank, Integer> eachRankCounts = finalGrade.getEachRankCounts();
            // then
            assertEachRankCounts(eachRankCounts, 1, 0, 0, 0, 0, 0);
        }

        @ParameterizedTest
        @CsvSource(value = {"1,2,3,4,5,6:1,2,3,4,6,21:5",
                "11,12,13,14,15,16:16,15,14,13,12,29:11"}, delimiter = ':')
        @DisplayName("2등 테스트 - 5개 일치, 보너스 볼 일치")
        void secondTest(String playerLottoInput, String winningNumberInput, int bonusNumberInput) {
            // given
            PlayerLotto playerLotto = new PlayerLotto(List.of(new Lotto(parser(playerLottoInput))));
            Lotto winningNumber = new Lotto(parser(winningNumberInput));
            LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
            // when
            FinalGrade finalGrade = playerLotto.calculateFinalGrade(winningNumber, bonusNumber);
            EnumMap<Rank, Integer> eachRankCounts = finalGrade.getEachRankCounts();
            // then
            assertEachRankCounts(eachRankCounts, 0, 1, 0, 0, 0, 0);
        }

        @ParameterizedTest
        @CsvSource(value = {"1,2,3,4,5,6:1,2,3,4,6,21:7",
                "11,12,13,14,15,16:16,15,14,13,12,29:17",
                "1,45,3,4,2,21:1,2,3,4,45,43:6"}, delimiter = ':')
        @DisplayName("3등 테스트 - 5개 일치, 보너스 볼 불일치")
        void thirdTest(String playerLottoInput, String winningNumberInput, int bonusNumberInput) {
            // given
            PlayerLotto playerLotto = new PlayerLotto(List.of(new Lotto(parser(playerLottoInput))));
            Lotto winningNumber = new Lotto(parser(winningNumberInput));
            LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
            // when
            FinalGrade finalGrade = playerLotto.calculateFinalGrade(winningNumber, bonusNumber);
            EnumMap<Rank, Integer> eachRankCounts = finalGrade.getEachRankCounts();
            // then
            assertEachRankCounts(eachRankCounts, 0, 0, 1, 0, 0, 0);
        }

        @ParameterizedTest
        @CsvSource(value = {"1,2,3,4,5,6:1,2,3,4,45,43:6",
                "21,22,23,24,25,26:22,24,25,26,1,2:11"}, delimiter = ':')
        @DisplayName("4등 테스트 - 당첨 번호 4개 일치, 보너스 볼 일치 여부 상관 없음")
        void forthTestWhenBonusMatch(String playerLottoInput, String winningNumberInput, int bonusNumberInput) {
            // given
            PlayerLotto playerLotto = new PlayerLotto(List.of(new Lotto(parser(playerLottoInput))));
            Lotto winningNumber = new Lotto(parser(winningNumberInput));
            LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
            // when
            FinalGrade finalGrade = playerLotto.calculateFinalGrade(winningNumber, bonusNumber);
            EnumMap<Rank, Integer> eachRankCounts = finalGrade.getEachRankCounts();
            // then
            assertEachRankCounts(eachRankCounts, 0, 0, 0, 1, 0, 0);
        }

        @ParameterizedTest
        @CsvSource(value = {"1,2,3,4,5,6:1,3,6,21,27,34:45",
                "11,12,13,14,15,16:16,15,14,21,22,29:11"}, delimiter = ':')
        @DisplayName("5등 테스트 - 3개 일치, 보너스 볼 일치 여부 상관 없음")
        void fifthTest(String playerLottoInput, String winningNumberInput, int bonusNumberInput) {
            // given
            PlayerLotto playerLotto = new PlayerLotto(List.of(new Lotto(parser(playerLottoInput))));
            Lotto winningNumber = new Lotto(parser(winningNumberInput));
            LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
            // when
            FinalGrade finalGrade = playerLotto.calculateFinalGrade(winningNumber, bonusNumber);
            EnumMap<Rank, Integer> eachRankCounts = finalGrade.getEachRankCounts();
            // then
            assertEachRankCounts(eachRankCounts, 0, 0, 0, 0, 1, 0);
        }

        @ParameterizedTest
        @CsvSource(value = {
                "1,3,11,23,34,44:1,3,6,21,27,33:45",

                "29,22,1,2,3,11:16,15,14,21,22,29:11",

                "11,12,13,14,15,16:11,17,18,19,20,21:22",

                "17,18,19,20,21,22:22,23,24,25,26,27:17",

                "30,31,32,33,34,35:36,37,38,39,40,41:42",

                "40,41,42,43,44,45:1,2,3,4,5,6:40"}, delimiter = ':')
        void noneTest(String playerLottoInput, String winningNumberInput, int bonusNumberInput) {
            // given
            PlayerLotto playerLotto = new PlayerLotto(List.of(new Lotto(parser(playerLottoInput))));
            Lotto winningNumber = new Lotto(parser(winningNumberInput));
            LottoNumber bonusNumber = new LottoNumber(bonusNumberInput);
            // when
            FinalGrade finalGrade = playerLotto.calculateFinalGrade(winningNumber, bonusNumber);
            EnumMap<Rank, Integer> eachRankCounts = finalGrade.getEachRankCounts();
            // then
            assertEachRankCounts(eachRankCounts, 0, 0, 0, 0, 0, 1);
        }
    }

    private static void assertEachRankCounts(EnumMap<Rank, Integer> eachRankCounts,
                                             int firstCounts, int secondCounts, int thirdCounts,
                                             int fourthCounts, int fifthCounts, int noneCounts) {
        Assertions.assertThat(eachRankCounts)
                .containsEntry(Rank.FIRST, firstCounts)
                .containsEntry(Rank.SECOND, secondCounts)
                .containsEntry(Rank.THIRD, thirdCounts)
                .containsEntry(Rank.FOURTH, fourthCounts)
                .containsEntry(Rank.FIFTH, fifthCounts)
                .containsEntry(Rank.NONE, noneCounts);
    }

    private static List<Integer> parser(String playerLottoInput) {
        return Arrays.stream(playerLottoInput.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    private PlayerLotto createPlayerLotto() {
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 5, 7, 27)),
                new Lotto(List.of(1, 4, 5, 7, 2, 28)),
                new Lotto(List.of(7, 4, 2, 3, 30, 37)),
                new Lotto(List.of(2, 3, 7, 11, 12, 13)));
        return new PlayerLotto(lottos);
    }

}