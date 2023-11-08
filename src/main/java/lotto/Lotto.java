package lotto;

import java.util.Collections;
import java.util.List;
import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }

        Set<Integer> set = new HashSet<>(numbers);
        if(set.size() !=6){
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현
    public void printNumber(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i <numbers.size(); i++){
            sb.append(numbers.get(i));
            sb.append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("]");
        System.out.println(sb.toString());
    }

    private void sortNumber(){
        Collections.sort(this.numbers);
    }

    public List<Integer> getNumbers(){
        return this.numbers;
    }
    public int check(List<Integer> answer){
        int count = 0;
        for(int i = 0; i < answer.size(); i++) {
            if (numbers.contains(answer.get(i))) {
                count++;
            }
        }
        return count;
    }

    public boolean checkBonus(int bonus){
        if(numbers.contains(bonus)){
            return true;
        }
        return false;
    }
}
