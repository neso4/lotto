package lotto.Logic;

import java.util.ArrayList;
import java.util.List;

public class WinningNumber {
    List<Integer> numbers=new ArrayList<>();
    int bonus;


    public WinningNumber(){
    }
    public static boolean isParsable(String str) {

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            if (!Character.isDigit(currentChar))
                return false;
        }

        return true;
    }
    public void splitValid(String number){
        String[] checkNumber=number.split(",",-1);

        if (checkNumber.length!=6)
            throw new IllegalArgumentException("[ERROR] 당첨번호는 6개의 정수로 입력해야 한다");

        digitValid(checkNumber);
    }

//    public void numberValid(String[] checkNumber){//이거 필요?
//
//        for(int i=0;i<checkNumber.length;i++){
//
//            if(checkNumber[i].length()==0)
//                throw new IllegalArgumentException("[ERROR] 각 당첨 번호는 1자 이상 입력해야 한다");
//        }
//        digitValid(checkNumber);
//    }
    public void digitValid(String[] checkNumber){
        List<String> validNumber=new ArrayList<>();

        for(int i=0;i<checkNumber.length;i++){

            if (isParsable(checkNumber[i]))
                validNumber.add(checkNumber[i]);
            else if (!isParsable(checkNumber[i]))
                throw new IllegalArgumentException("[ERROR] 각 당첨 번호는 숫자로 입력해야 한다");

        }
        setNumbers(validNumber);
    }

    public void setNumbers(List<String> validNumber){//같은 값

        for(int i=0;i<validNumber.size();i++){
            int number=Integer.parseInt(validNumber.get(i));

            if (number<=0 || number>45)
                throw new IllegalArgumentException("[ERROR] 각 당첨 번호는 1~45 사이로 표현해야 한다");

            if (numbers.contains(number))
                throw new IllegalArgumentException("[ERROR] 각 당첨 번호는 중복되면 안된다");
            else if(!numbers.contains(number))
                numbers.add(number);
        }
    }

    public void setBonusValid(String bonus){
        try{
            this.bonus=Integer.parseInt(bonus);

        } catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자로 표현해야 한다");
        }
        bonusValid(this.bonus);
    }
    public void bonusValid(int bonusNumber){
        if (bonusNumber<=0)
            throw new IllegalArgumentException("[ERROR] 보너스 숫자는 0 이하로 표현하면 안된다");
        else if(bonusNumber>=46)
            throw new IllegalArgumentException("[ERROR] 보너스 숫자는 46 이상으로 표현하면 안된다");

    }
}
