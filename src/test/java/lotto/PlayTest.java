package lotto;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PlayTest {

    @Test
    void giveNumberOfTicket() {
        Play play = new Play();
        assertEquals(2, play.giveNumberOfTicket("2000"));
    }
    @Test
    void compare() {
        Play play = new Play();
        List<Integer> expected = Arrays.asList(4,1);
        List<Integer> compare1 = Arrays.asList(1,2,3,4,5,6,7);
        List<Integer> compare2 = Arrays.asList(1,2,3,4,10,11,7);
        assertEquals(expected, play.compare(compare1, compare2));
    }
    @Test
    void givePrize() {
        Play play = new Play();
        int expected = 1;
        List<Integer> test = Arrays.asList(6,1);
        assertEquals(expected, play.givePrize(test));

    }
}