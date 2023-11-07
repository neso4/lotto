package lotto.controller;

import java.util.concurrent.Callable;
import lotto.model.Game;
import lotto.util.converter.PurchaseAmountConverter;
import lotto.view.View;

public class GameController {
    private final View view;
    private final Game game;

    public GameController(View view, Game game) {
        this.view = view;
        this.game = game;
    }

    public void run() {
        // TODO: 게임 로직 작성
        retryUntilValidInput(this::handlePurchaseLottoTickets);
    }

    private void retryUntilValidInput(Callable<Boolean> containUserInput) {
        boolean validInput = false;
        while (!validInput) {
            try {
                validInput = containUserInput.call();
            } catch (IllegalArgumentException e) {
                view.displayException(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean handlePurchaseLottoTickets() {
        int purchaseAmount = inputPurchaseAmount();
        game.purchaseLottoTickets(purchaseAmount);
        return true;
    }

    private int inputPurchaseAmount() {
        String input = view.getPurchaseAmount();
        return PurchaseAmountConverter.convert(input);
    }
}
