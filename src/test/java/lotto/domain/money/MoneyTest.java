package lotto.domain.money;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("로또 구입 후, 구입 금액은 감소한다.")
    @Test
    void should_Not_Be_Equal_When_Initial_Money_Given_And_Decremented() {
        // given
        Money money = Money.fromInitialMoney(5_000);

        // when
        Money decrementedMoney = money.decremented();

        // then
        assertNotEquals(money, decrementedMoney);
    }

    @DisplayName("로또 한 장 이상을 구매하기에 구입 금액이 부족하다.")
    @Test
    public void should_Return_False_When_If_Not_Enough_With_Money() {
        // given
        Money money = Money.fromRemainingMoney(1_000);

        // when
        boolean notEnough = money.isNotEnough();

        // then
        assertFalse(notEnough);
    }

    @DisplayName("로또 한 장 이상을 구매하기에 구입 금액이 충분하다.")
    @Test
    public void should_Return_True_When_If__Enough_With_Money() {
        // given
        int amount = 3_000;
        Money money1 = Money.fromInitialMoney(amount);
        Money money2 = Money.fromRemainingMoney(amount);

        // when
        boolean isEnough1 = money1.isEnough();
        boolean isEnough2 = money2.isEnough();

        // then
        assertTrue(isEnough1);
        assertTrue(isEnough2);
    }

    @DisplayName("두 Money 객체는 같은 금액을 가질 때 동등해야 한다.")
    @Test
    public void should_MoneyObjects_Be_Equal_When_They_Have_Same_Money() {
        // given
        // when
        Money money1 = Money.fromInitialMoney(1000);
        Money money2 = Money.fromInitialMoney(1000);
        Money money3 = Money.fromInitialMoney(2000);

        // then
        assertEquals(money1, money2);
        assertNotEquals(money1, money3);
    }

    @DisplayName("두 Money 객체의 해시 코드는 같은 금액을 가질 때 동일해야 한다.")
    @Test
    public void should_HashCode_Of_MoneyObjects_Be_Equal_When_They_Have_Same_Money() {
        // given
        // when
        Money money1 = Money.fromInitialMoney(1000);
        Money money2 = Money.fromInitialMoney(1000);
        Money money3 = Money.fromInitialMoney(2000);

        // then
        assertEquals(money1.hashCode(), money2.hashCode());
        assertNotEquals(money1.hashCode(), money3.hashCode());
    }
}