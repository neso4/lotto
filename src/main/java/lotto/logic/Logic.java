package lotto.logic;

public interface Logic {
    int START_RANGE = 1;
    int END_RANGE = 45;
    int NUMBER_COUNT = 6;
    int LOTTERY_COST = 1000;

    void start();

    void run();
}
