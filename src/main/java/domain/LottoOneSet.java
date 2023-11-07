package domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : domain
 * fileName       : LottoOneSet
 * author         : qkrtn_ulqpbq2
 * date           : 2023-11-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-11-06        qkrtn_ulqpbq2       최초 생성
 */
public class LottoOneSet {

    private static final int LOTTO_GAME_START_NUMBER = 1;
    private static final int LOTTO_GAME_END_NUMBER = 45;
    private static final int LOTTO_GAME_NUMBER_SIZE = 6;
    private final List<Integer> numbers;

    public List<Integer> getLottoOneSet(){
        return numbers;
    }
    public LottoOneSet() {
        this.numbers = sortRandomNumber(generateRandomNumbers());
    }

    private List<Integer> generateRandomNumbers(){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LOTTO_GAME_START_NUMBER, LOTTO_GAME_END_NUMBER, LOTTO_GAME_NUMBER_SIZE);
        System.out.println(numbers);
        return numbers;
    }

    private List<Integer> sortRandomNumber(List<Integer> number){
//        Collections.sort(number);
//        return number; >> 이 내용 블로그에 정리하자!
        List<Integer> sortedNumbers = new ArrayList<>(number);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }
}
