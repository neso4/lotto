package lotto;

import lotto.controller.ControlMain;
import lotto.controller.ErrorCheck;
import lotto.model.EnumRanking;
import lotto.model.Player;
import lotto.view.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    //     아래에 추가 테스트 작성 가능
    @DisplayName("로또 범위를 넘어갔을 경우 예외가 발생한다")
    @Test
    void validateMaxAndMinNumTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 0, 46, 2, 3, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.isMaxAndMinValue());
    }

    @DisplayName("1000으로 안 나누어질 경우 예외가 발생한다")
    @Test
    void priceErrorCheckTest() {
        assertThatThrownBy(() -> ErrorCheck.priceErrorCheck("1234"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.notPriceMessage());
    }

    @DisplayName("정수형이 아닐 경우 예외가 발생한다")
    @Test
    void isDigitErrorCheckTest() {
        String[] stringArr = {"a", " 2", "$", " "};
        assertThatThrownBy(() -> ErrorCheck.stringChangeChar(stringArr))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.notIntegerMessage());
    }

    @DisplayName("중복된 값이 있을 경우 예외가 발생한다")
    @Test
    void isDuplicationErrorcheckTset() {
        String choice = "1";
        assertThatThrownBy(() -> ErrorCheck.isDuplicationErrorCheck(choice, List.of(1, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.isDuplication());
    }

    @DisplayName("로또 범위를 넘어갔을 경우 예외가 발생한다")
    @Test
    void isValidateMaxAndMinNumTest() {
        String[] stringArr = {"0", "46", "234", "3215"};
        for (String s : stringArr) {
            assertThatThrownBy(() -> ErrorCheck.isValidateMaxAndMinNum(s))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.isMaxAndMinValue());
        }
    }

    @DisplayName("공백을 입력했을 경우 예외가 발생한다")
    @Test
    void isSpaceValueTest() {
        String space = "";
        assertThatThrownBy(() -> ErrorCheck.isSpaceValue(space))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.isSpace());
    }


    @DisplayName("Lotto클래스 값의 범위를 넘었을경우 예외가 발생한다")
    @Test
    void lottoMaxAndMinNumTest() {
        List<Integer> numbers = List.of(1, 45, 6, 0, 74, 100);
        assertThatThrownBy(() -> Lotto.validateMaxAndMinNum(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.isMaxAndMinValue());

    }

    @DisplayName("Lotto 클래스의 값에 중봑이 있을경우 예외가 발생한다")
    @Test
    void lottoDuplicationTest() {
        List<Integer> numbers = List.of(1, 2, 1, 4, 5, 5);
        assertThatThrownBy(() -> Lotto.validateIsDuplicationErrorCheck(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.isDuplication());
    }

    @DisplayName("Lotto 클래스의 값의 길이가 6이 아닐경우 예외가 발생한다")
    @Test
    void validateTest() {
        List<List<Integer>> numbers = new ArrayList<>(List.of(List.of(1, 2, 3),
                List.of(1, 2, 3, 4, 5, 6, 7)));
        for (List<Integer> number : numbers) {
            assertThatThrownBy(() -> new Lotto(number))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.notValue());
        }
    }

    @DisplayName("Lotto 당첨 개수 테스트 1,1,1,1,1 이 나와야함 수익률 테스트")
    @Test
    void lottoTest() {
        List<List<Integer>> mainLotto = new ArrayList<>(List.of(
                List.of(1, 23, 45, 2, 4, 6),
                List.of(1, 23, 45, 2, 6, 7),
                List.of(1, 23, 45, 2, 20, 6),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 23, 45, 25, 26, 27),
                List.of(24, 25, 26, 27, 28, 29)));
        List<Integer> numbers = List.of(1, 23, 45, 2, 4, 6);
        Player player = new Player();
        player.updateBonusNumber(7);
        Lotto lotto = new Lotto(numbers);
        ControlMain.equalsNumber(mainLotto, lotto.getNumbers(), player);
        int[] matchCountArray = player.getMatchCount();
        List<Integer> matchCountList = Arrays.stream(matchCountArray).boxed().collect(Collectors.toList());
        assertThat(matchCountList).isEqualTo(List.of(1, 1, 1, 1, 1));
        assertThat(player.getTotal()).isEqualTo(2031555000);
    }

    @DisplayName("Player price count 입력")
    @Test
    void playerPriceTest() {
        Player player = new Player();
        player.updatePrice(8000);
        assertThat(player.getPrice()).isEqualTo(8000);
        assertThat(player.getCount()).isEqualTo(8);

    }

    @DisplayName("1등")
    @Test
    void lottoTest1() {
        int cnt = 6;
        Player player = new Player();
        assertThat(EnumRanking.Ranking.otherRanking(cnt)).isEqualTo(EnumRanking.Ranking.FIRST);
        player.updateMatchCountAndTotal(EnumRanking.Ranking.otherRanking(cnt));
        assertThat(player.getTotal()).isEqualTo(2000000000);
        int[] matchCountArray = player.getMatchCount();
        List<Integer> matchCountList = Arrays.stream(matchCountArray).boxed().collect(Collectors.toList());
        assertThat(matchCountList).isEqualTo(List.of(1,0,0,0,0));
    }

    @DisplayName("2등")
    @Test
    void lottoTest2() {
        Player player = new Player();
        player.updateBonusNumber(45);
        List<Integer> randomLotto = List.of(1, 2, 3, 4, 5, 45);
        assertThat(EnumRanking.Ranking.secondOrThird(randomLotto, player)).isEqualTo(EnumRanking.Ranking.SECOND);
        player.updateMatchCountAndTotal(EnumRanking.Ranking.secondOrThird(randomLotto, player));
        assertThat(player.getTotal()).isEqualTo(30000000);
        int[] matchCountArray = player.getMatchCount();
        List<Integer> matchCountList = Arrays.stream(matchCountArray).boxed().collect(Collectors.toList());
        assertThat(matchCountList).isEqualTo(List.of(0,1,0,0,0));
    }

    @DisplayName("3등")
    @Test
    void lottoTest3() {
        Player player = new Player();
        player.updateBonusNumber(45);
        List<Integer> randomLotto = List.of(1, 2, 3, 4, 5, 6);
        assertThat(EnumRanking.Ranking.secondOrThird(randomLotto, player)).isEqualTo(EnumRanking.Ranking.THIRD);
        player.updateMatchCountAndTotal(EnumRanking.Ranking.secondOrThird(randomLotto, player));
        assertThat(player.getTotal()).isEqualTo(1500000);
        int[] matchCountArray = player.getMatchCount();
        List<Integer> matchCountList = Arrays.stream(matchCountArray).boxed().collect(Collectors.toList());
        assertThat(matchCountList).isEqualTo(List.of(0,0,1,0,0));
    }

    @DisplayName("4등")
    @Test
    void lottoTest4() {
        int cnt = 4;
        Player player = new Player();
        assertThat(EnumRanking.Ranking.otherRanking(cnt)).isEqualTo(EnumRanking.Ranking.FOURTH);
        player.updateMatchCountAndTotal(EnumRanking.Ranking.otherRanking(cnt));
        assertThat(player.getTotal()).isEqualTo(50000);
        int[] matchCountArray = player.getMatchCount();
        List<Integer> matchCountList = Arrays.stream(matchCountArray).boxed().collect(Collectors.toList());
        assertThat(matchCountList).isEqualTo(List.of(0,0,0,1,0));
    }

    @DisplayName("5등")
    @Test
    void lottoTest5() {
        int cnt = 3;
        Player player = new Player();
        assertThat(EnumRanking.Ranking.otherRanking(cnt)).isEqualTo(EnumRanking.Ranking.FIFTH);
        player.updateMatchCountAndTotal(EnumRanking.Ranking.otherRanking(cnt));
        assertThat(player.getTotal()).isEqualTo(5000);
        int[] matchCountArray = player.getMatchCount();
        List<Integer> matchCountList = Arrays.stream(matchCountArray).boxed().collect(Collectors.toList());
        assertThat(matchCountList).isEqualTo(List.of(0,0,0,0,1));
    }

    @DisplayName("Last")
    @Test
    void lottoTest6() {
        int[] cntList = {0, 1, 2};
        Player player = new Player();
        for (int cnt : cntList) {
            assertThat(EnumRanking.Ranking.otherRanking(cnt)).isEqualTo(EnumRanking.Ranking.LAST);
            player.updateMatchCountAndTotal(EnumRanking.Ranking.otherRanking(cnt));
            assertThat(player.getTotal()).isEqualTo(0);
            int[] matchCountArray = player.getMatchCount();
            List<Integer> matchCountList = Arrays.stream(matchCountArray).boxed().collect(Collectors.toList());
            assertThat(matchCountList).isEqualTo(List.of(0,0,0,0,0));
        }
    }


}