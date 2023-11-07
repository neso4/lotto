package util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    Parser parser = new Parser();
    private static int[] having_lotto = new int[7];

    public void checkMoneyInput(String input) {
        checkAllMoneyInput(input);
    }

    public void checkBonusLottoInput(String input) {
        checkEmpty(input);
        checkNotNumber(input);
        checkEachChar(input);
        checkDuplicateOfBonus(input);
    }

    private void checkAllMoneyInput(String input) {
        checkEmpty(input);
        checkInteger(input);
        checkThousand(input); //checkInteger에서 exception이 끝나지만 버그 대비
    }

    private void checkEachChar(String each_num) {
        int check_num = parser.parseNumber(each_num);
        if(check_num > 45 || check_num < 0)
            throw new IllegalArgumentException("[ERROR] 값의 범위는 1부터 45까지 입니다.");
    }

    public void checkNotNumberAndSpace(String input) {
        checkNotNumber(input);
        checkEachCharSpace(input);
    }

    private void checkNotNumber(String input) {
        Pattern pattern = Pattern.compile("[^,0-9]+");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find())
            throw new IllegalArgumentException("[ERROR] 잘못 입력되었습니다");
    }
    private void checkEachCharSpace(String check_space) {
        if (check_space.contains(" "))
            throwSpaceException();
    }

    private void checkEmpty(String input) {
        if (input.isEmpty()) {
            throwSpaceException();
        }
        if (input.isBlank()) {
            throwSpaceException();
        }
    }

    private void checkInteger(String input) {
        checkFirstInteger(input.charAt(0));

        for(int i = 1; i < input.length(); i++) {
            checkZeroInteger(input.charAt(i));
        }
    }

    private void checkFirstInteger(int each) {
        if(49 > each && each > 57) {
            throw new IllegalArgumentException("[ERROR] 잘못 입력된 값입니다.");
        }
    }

    private void checkZeroInteger(int each_input) {
        if(48 != each_input) {
            throwThousandException();
        }
    }

    private void checkThousand(String input) {
        int input_money = parser.parseNumber(input);

        if (input_money % 1000 != 0) {
            throwThousandException();
        }
    }

    public void checkDuplicate(String input) {
        List<String> userlottoList = parser.parseLottoNumber(input);
        for (int i = 0; i < 6; i++) {
            having_lotto[i] = Integer.parseInt(userlottoList.get(i));
            compareDuplicate(i, userlottoList);
        }
    }

    private void checkDuplicateOfBonus(String bonus_number) {
        for(int i = 0; i < 6; i++) {
            compareDuplicateOfBonus(having_lotto[i], parser.parseNumber(bonus_number));
        }
    }

    private void compareDuplicateOfBonus(int having, int bonus) {
        if (having == bonus) {
            throwDuplicateException();
        }
    }

    private void compareDuplicate(int i, List<String> userlottoList) {
        for (int j = i + 1; j < userlottoList.size(); j++) {
            checkEqual(userlottoList.get(i), userlottoList.get(j));
        }
    }

    private void checkEqual(String lotto1, String lotto2) {
        if(lotto1.equals(lotto2)) {
            having_lotto = null;
            throwDuplicateException();
        }
    }

    private void throwSpaceException() {
        throw new IllegalArgumentException("[ERROR] 공백이 존재합니다.");
    }

    private void throwDuplicateException() {
        throw new IllegalArgumentException("[ERROR] 중복된 숫자가 있습니다.");
    }

    private void throwThousandException() {
        throw new IllegalArgumentException("[ERROR] 1000원 단위가 아닙니다.");
    }
}
