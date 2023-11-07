package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.text.NumberFormat;
import java.util.*;

public class Application {
    public static void main(String[] args) {

    }

    enum LottoRank {
        FIRST(6, 2000000000), SECOND(5, 30000000),
        THIRD(5, 1500000), FOURTH(4, 50000),
        FIFTH(3, 5000), NONE(0, 0);

        private final int matchingCount;
        private final int prize;

        LottoRank(int matchingCount, int prize) {
            this.matchingCount = matchingCount;
            this.prize = prize;
        }
        public int getMatchingCount() {
            return matchingCount;
        }
        public  int getPrize() {
            return prize;
        }
    }
}
