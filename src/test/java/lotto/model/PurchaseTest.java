package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PurchaseTest {

    @DisplayName("문자 입력시 오류가 발생한다.")
    @Test
    void validateNum() {
        assertThatThrownBy(() -> new Purchase("abc"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구입 가격이 1000원 단위가 아니면 오류가 발생한다.")
    @Test
    void validateUnit() {
        assertThatThrownBy(() -> new Purchase("1200"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 개수 확인")
    @Test
    void pieces() {
        int purchaseCount = new Purchase("12000").pieces();
        assertThat(purchaseCount).isEqualTo(12);
    }
}