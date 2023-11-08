package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
        if(!checkRangeAll(numbers)){
            // 특정 범위 내의 값이 아닌 경우
            throw new IllegalArgumentException();
        }
        if(!checkDupliAll(numbers)){
            // 동일한 값이 존재할 경우
            throw new IllegalArgumentException();
        }
    }

    private boolean checkDupliAll(List<Integer> numbers){
        boolean res = true;
        for(int i=1;i<6;i++){
            res = res & checkDupli(numbers.get(i-1), numbers.get(i));
        }
        return res;
    }

    private boolean checkDupli(int a, int b){
        return a!=b;
    }

    private boolean checkRangeAll(List<Integer> numbers){
        boolean res = true;
        for(int i=0;i<6;i++){
            res = res & checkRange(numbers.get(i));
        }
        return res;
    }

    // TODO: 추가 기능 구현
    private boolean checkRange(int checkNum){
        return (checkNum>0)&(checkNum<46);
    }
    public void printInfo(){
        Collections.sort(numbers);
        System.out.println(numbers);
    }

    public int compareOther(Lotto other){
        List<Integer> res_com = new ArrayList<>();
        for(int i=0;i<6;i++){
            res_com.add(this.numbers.get(i));
        }
        res_com.retainAll(other.numbers);
        return res_com.size();
    }

    public boolean containNum(int contain_num){
        return this.numbers.contains(contain_num);
    }

}