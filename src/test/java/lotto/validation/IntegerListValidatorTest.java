package lotto.validation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class IntegerListValidatorTest {

    @Test
    public void 정수리스트에_중복된값이_있으면_예외처리() {
        // Given
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 2, 3, 4);

        // When && Then
        assertThatThrownBy(() -> IntegerListValidator.validateDuplicated(numbersWithDuplicates))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 값은 입력할 수 없습니다.");
    }

    @Test
    public void 정수리스트에_중복된값이_없으면_정상통과() {
        // Given
        List<Integer> numbersWithoutDuplicates = Arrays.asList(1, 2, 3, 4, 5);

        // When
        IntegerListValidator.validateDuplicated(numbersWithoutDuplicates);

        // Then

    }

    @Test
    public void 정수리스트_사이즈가_다르면_예외처리() {
        // Given
        List<Integer> validNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int invalidSize = 7;

        // When && Then
        assertThatThrownBy(() -> IntegerListValidator.validateSize(validNumbers, invalidSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정해진 개수의 숫자를 입력하세요.");
    }

    @Test
    public void 정수리스트_사이즈가_같으면_정상통과() {
        // Given
        List<Integer> validNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int validSize = 6;

        // When
        IntegerListValidator.validateSize(validNumbers, validSize);

        // Then

    }

    @Test
    public void 정수리스트_최소_최대_사이의_범위가_아닌값이_있으면_예외처리() {
        // Given
        List<Integer> invalidNumbers = Arrays.asList(0, 2, 3, 7, 8, 9);
        int min = 1;
        int max = 6;

        // When && Then
        assertThatThrownBy(() -> IntegerListValidator.validateEachIntegerInRange(
                invalidNumbers, min, max))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정해진 범위의 숫자를 입력하세요.");
    }

    @Test
    public void 정수리스트_모든값이_최소_최대_사이의_범위라면_정상통과() {
        // Given
        List<Integer> validNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int min = 1;
        int max = 6;

        // When
        IntegerListValidator.validateEachIntegerInRange(validNumbers, min, max);

        // Then

    }
}