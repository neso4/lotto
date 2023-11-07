package lotto;

import java.util.List;
import java.util.ArrayList;
import camp.nextstep.edu.missionutils.Console;

public class Input_function {
    private static final int ArrayList = 0;
    public int num = 0; 
    public int getMoney() {
        boolean isValid = false;
        int number = 0;
        while(!isValid) {
            try {
                number = inputNumber();
                isValid = true;
            }
            catch(IllegalArgumentException e) {
                System.out.println(e.getMessage() + " 다시 입력해주세요.");
            }
        }
        return number;
    }
    public int inputNumber() {
        int number = 0;
        while(true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String input_str = Console.readLine();
                number = Integer.parseInt(input_str);
                //Console.close();
                return number;
            }
            catch(NumberFormatException nfe) {
                throw new IllegalArgumentException("[ERROR]", nfe);
            }
        }
    }
    
    public List<Integer> get_lotto_number() {
        boolean isValid = false;
        List<Integer> number = new ArrayList<>();
        while(!isValid) {
            try {
                number = input_lotto_number();
                isValid = true;
            }
            catch(IllegalArgumentException e) {
                System.out.println(e.getMessage() + " 다시 입력해주세요.");
            }
        }
        return number;
    }

    public List<Integer> input_lotto_number() {
        try {
            System.out.println("당첨번호를 입력해 주세요.");
            String input_str = Console.readLine();
            String[] str_numbers = input_str.split(",");
            int number = 0;
            List<Integer> lotto_numbers = new ArrayList<>();

            for(String str: str_numbers) {
                number = Integer.parseInt(str);
                validate_lotto_number(number, lotto_numbers);
                lotto_numbers.add(number);
                
            }

            if(lotto_numbers.size() != 6) {
                throw new IllegalArgumentException("[ERROR] 입력한 숫자가 6개가 아닙니다.");
            }
            return lotto_numbers;
        }
        catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("[ERROR] 올바른 숫자 형식이 아닙니다.", nfe);
        }
    }

    public void validate_lotto_number(int number, List<Integer> numbers) {
        if(number < 1 || number > 45)
        {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if(numbers.contains(number))            {
                throw new IllegalArgumentException("[ERROR] 중복된 숫자가 포함되어 있습니다.");
        }
    }


    public int getBonusNumber(List<Integer> numbers) {
        boolean isValid = false;
        int number = 0;
        while(!isValid) {
            try {
                number = inputBonusNumber(numbers);
                isValid = true;
            }
            catch(IllegalArgumentException e) {
                System.out.println(e.getMessage() + " 다시 입력해주세요.");
            }
        }
        return number;
    }

    public int inputBonusNumber(List<Integer> numbers) {
        try {
            System.out.println("보너스 번호를 입력해 주세요.");
            String input_str = Console.readLine();  
            int number = Integer.parseInt(input_str);

            validate_bonus_number(number, numbers);
            
            return number;
        }
        catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("[ERROR] 올바른 숫자 형식이 아닙니다.", nfe);
        }
    }
    public void validate_bonus_number(int number, List<Integer> numbers) {
        if(number < 1 || number > 45)
        {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if(numbers.contains(number))            {
                throw new IllegalArgumentException("[ERROR] 당첨번호에 있는 숫자를 입력하셨습니다.");
        }
    }
    
}
