package lotto;

import lotto.controller.RankMachine;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static lotto.constant.Config.*;
import static lotto.constant.RankMessage.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RankMachineTest {
    RankMachine createRankMachine() {
        return new RankMachine(List.of(
                new Rank(RANK_FIFTH, REWARD_FIFTH, (ticket, winningTicket, bonusNumber) ->
                        ticket.countMatched(winningTicket) == 3),
                new Rank(RANK_FOURTH, REWARD_FOURTH, (ticket, winningTicket, bonusNumber) ->
                        ticket.countMatched(winningTicket) == 4),
                new Rank(RANK_THIRD, REWARD_THIRD, (ticket, winningTicket, bonusNumber) ->
                        ticket.countMatched(winningTicket) == 5 && !ticket.hasNumber(bonusNumber.getLottoNumber())),
                new Rank(RANK_SECOND, REWARD_SECOND, (ticket, winningTicket, bonusNumber) ->
                        ticket.countMatched(winningTicket) == 5 && ticket.hasNumber(bonusNumber.getLottoNumber())),
                new Rank(RANK_FIRST, REWARD_FIRST, (ticket, winningTicket, bonusNumber) ->
                        ticket.countMatched(winningTicket) == 6)
        ));
    }

    void applySampleTickets(RankMachine rankMachine) {
        Lotto winningTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(new LottoNumber(7), winningTicket);
        new ArrayList<>(List.of(
                // 6개 일치
                List.of(1, 2, 3, 4, 5, 6),

                // 5개 및 보너스 일치
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 4, 6, 7),
                List.of(1, 2, 3, 5, 6, 7),

                // 5개 일치
                List.of(1, 2, 3, 4, 5, 8),
                List.of(1, 2, 3, 4, 5, 9),

                // 4개 일치
                List.of(1, 2, 3, 4, 8, 9),
                List.of(1, 2, 3, 4, 8, 10),
                List.of(1, 2, 3, 4, 8, 11),
                List.of(1, 2, 3, 4, 8, 12),

                // 3개 일치
                List.of(1, 2, 3, 8, 9, 10),
                List.of(1, 2, 3, 8, 9, 11),
                List.of(1, 2, 3, 8, 9, 12),
                List.of(1, 2, 3, 8, 9, 13),
                List.of(1, 2, 3, 8, 9, 14),
                List.of(1, 2, 3, 8, 9, 15),
                List.of(1, 2, 3, 8, 9, 16),

                // 2개 일치 - 꽝
                List.of(1, 2, 8, 9, 10, 11),
                List.of(1, 2, 8, 9, 10, 12),
                List.of(1, 2, 8, 9, 10, 13),
                List.of(1, 2, 8, 9, 10, 14),
                List.of(1, 2, 8, 9, 10, 15),

                // 1개 일치 - 꽝
                List.of(1, 8, 9, 10, 11, 12),
                List.of(1, 8, 9, 10, 11, 13),
                List.of(1, 8, 9, 10, 11, 14),
                List.of(1, 8, 9, 10, 11, 15),
                List.of(1, 8, 9, 10, 11, 16),
                List.of(1, 8, 9, 10, 11, 17),

                // 0개 일치 - 꽝
                List.of(8, 9, 10, 11, 12, 13)
        )).forEach(numbers -> rankMachine.applyTicket(new Lotto(numbers), winningTicket, bonusNumber));
    }

    @Test
    @DisplayName("5개의 랭크에 대해 정상 출력 되는지")
    void getWinningStatistics() {
        RankMachine rankMachine = createRankMachine();
        applySampleTickets(rankMachine);

        System.out.println(rankMachine.getRankStatistics());
        assertThat(rankMachine.getRankStatistics())
                .contains(
                        "3개 일치 (5,000원) - 7개",
                        "4개 일치 (50,000원) - 4개",
                        "5개 일치 (1,500,000원) - 2개",
                        "5개 일치, 보너스 볼 일치 (30,000,000원) - 3개",
                        "6개 일치 (2,000,000,000원) - 1개"
                );
    }

    @Test
    @DisplayName("수익률이 정상 출력 되는지")
    void getRateOfReturn() {
        RankMachine rankMachine = createRankMachine();
        applySampleTickets(rankMachine);

        System.out.println(rankMachine.getRateOfReturn());
        assertThat(rankMachine.getRateOfReturn()).contains("총 수익률은 7,218,051.7%입니다.");
    }
}
