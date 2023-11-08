package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ConvertorTest {

    @DisplayName("입력 문자열 숫자로 변환")
    @Test
    void convert() {
        String userInput = "123456";
        Integer convertedInput = Convertor.convert(userInput);

        Assertions.assertThat(convertedInput).isEqualTo(123456);
    }

    @DisplayName("입력값 쉼표(,)로 구분")
    @Test
    void split() {
        String userInput = "1,2,3,4,5,6";
        String[] splitInput = Convertor.split(userInput);
        Assertions.assertThat(splitInput).isEqualTo(new String[]{
                "1", "2", "3", "4", "5", "6"});
    }

    @DisplayName("문자열 배열을 컬렉션(List)로 변환")
    @Test
    void convertGathering() {
        String[] inputs = new String[]{"1", "2", "3", "4", "5", "6"};
        List<Integer> inputList = Convertor.convertGathering(inputs);

        Assertions.assertThat(inputList).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }
}