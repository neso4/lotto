package lotto;

import lotto.domain.GameInput;
import lotto.domain.InputReader;
import lotto.domain.LottoGame;
import lotto.domain.LottoTicket;

public class Application {

    public static void main(String[] args) {
        Game game = createGame();
        game.run();
    }

    private static Game createGame() {
        InputReader inputReader = new InputReader();
        GameInput gameInput = new GameInput(inputReader);
        LottoTicket lottoTicket = generateLottoTicket(gameInput.calculateLottoPurchase());
        lottoTicket.printLottoTicket();
        gameInput.setLottoNumbersAndBonusNumber(inputReader);
        LottoGame lottoGame = new LottoGame(lottoTicket.getLottos());
        return new Game(gameInput, lottoGame);
    }

    private static LottoTicket generateLottoTicket(int lottoPurchase) {
        LottoTicket lottoTicket = new LottoTicket();
        lottoTicket.generateLottoTicket(lottoPurchase);
        return lottoTicket;
    }

    public static class Game {

        private final GameInput gameInput;
        private final LottoGame lottoGame;

        public Game(GameInput gameInput, LottoGame lottoGame) {
            this.gameInput = gameInput;
            this.lottoGame = lottoGame;
        }

        public void run() {
            lottoGame.setWinningNumbers(gameInput.getLottoNumbers(), gameInput.getBonusNumber());
            lottoGame.printLottoResult();
        }
    }

}
