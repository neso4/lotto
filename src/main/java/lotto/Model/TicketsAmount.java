package lotto.Model;

import lotto.View.ExceptionMessage;
import lotto.Controller.InputController;
import lotto.View.OuputView;

public class TicketsAmount {

    private static final int TICKETS_MIN_PRICE = 1000;
    private final int tickets;

    public TicketsAmount(int tickets) {
        this.tickets = validate(tickets);
    }

    public int getTickets() {
        return tickets / 1000;
    }

    public int getTicketsPrice() {
        return tickets;
    }

    private int validate(int tickets) {
        try {
            validateMinimum(tickets);
            validateUnit(tickets);
            return tickets;
        } catch (IllegalArgumentException e) {
            OuputView.printErrorMessage(e.getMessage());
            int newTickets = InputController.inputTicketsAmount();
            return validate(newTickets);
        }
    }

    private void validateMinimum(int tickets) {
        if (tickets < TICKETS_MIN_PRICE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MIN_AMOUNT);
        }
    }

    private void validateUnit(int tickets) {
        if (tickets % TICKETS_MIN_PRICE != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_AMOUNT);
        }
    }
}



