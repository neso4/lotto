package lotto.Model;

public class StringToInt {
    private int valueToReturn;

    public StringToInt(String input){
        valueToReturn = Integer.parseInt(input);
    }

    public int getInteger(){
        return valueToReturn;
    }
}