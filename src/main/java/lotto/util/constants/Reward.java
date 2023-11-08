package lotto.util.constants;

import static lotto.util.exception.ErrorMessage.INVALID_RANK;

import java.util.Arrays;
import java.util.Objects;
import lotto.util.exception.LottoException;

public enum Reward {
    FIFTH(5, 5_000L, "3개 일치 (5,000원) - %d개"),
    FOURTH(4, 50_000L, "4개 일치 (50,000원) - %d개"),
    THIRD(3, 1_500_000L, "5개 일치 (1,500,000원) - %d개"),
    SECOND(2, 30_000_000L, "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개"),
    FIRST(1, 200_000_000L, "6개 일치 (2,000,000,000원) - %d개");
    private final Integer rank;
    private final Long reward;
    private final String message;

    Reward(final Integer rank, final Long reward, final String message) {
        this.rank = rank;
        this.reward = reward;
        this.message = message;
    }

    public static Long fromRank(final Integer rank) {
        return Arrays.stream(Reward.values())
                .filter(reward -> Objects.equals(reward.getRank(), rank))
                .findFirst()
                .map(Reward::getReward)
                .orElseThrow(() -> LottoException.of(INVALID_RANK));
    }

    public Integer getRank() {
        return this.rank;
    }

    private Long getReward() {
        return this.reward;
    }

    public String getMessage(final Integer count) {
        return String.format(message, count);
    }
}
