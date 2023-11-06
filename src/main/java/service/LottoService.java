package service;

import domain.Lotto;
import domain.Player;
import domain.WinningLotto;

import java.util.List;

public class LottoService {
    private final Player player = new Player();
    private final WinningLotto winningLotto = new WinningLotto();

    public int buyTickets(int moneyAmount) {
        player.buyLottoTickets(moneyAmount);
        return player.getTicketCount();
    }

    public List<Lotto> getTickets() {
        return player.getTickets();
    }

    public void setWinningNumber(List<Integer> winningNumbers) {
        winningLotto.setWinningNumber(winningNumbers);
    }

    public void setBonusNumber(int bonusNumber) {
        winningLotto.setBonusNumber(bonusNumber);
    }
}
