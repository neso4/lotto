package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.*;

public class Base_Func {

    public static int Input_Purchase_Price() {
        System.out.println("구입금액을 입력해 주세요.");
        String tmp_input = Console.readLine();
        int price = -1;
        try {
            price = Integer.parseInt(tmp_input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 입력 오류 입니다.");
        }
        return price;
    }

    public static int Purchase_Lotto_Number(int price) {
        try {
            if (price % 1000 != 0) {
                throw new IllegalStateException("[ERROR] 로또 금액이 1000으로 나누어 지지 않습니다.");
            }
        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }

        return price / 1000;
    }

    public static void Print_Purchased_Lotto_list(ArrayList<Lotto> Buy_lotto) {
        System.out.println("\n" + Buy_lotto.size() + "개를 구매했습니다.");
        for (Lotto buyLotto : Buy_lotto) {
            System.out.println(buyLotto.Get_numbers());
        }
    }

    public static List<Integer> Input_Win_Lotto_List() {
        System.out.println("당첨 번호를 입력해 주세요.");

        String tmp_input = Console.readLine();
        String[] tmp_arr = tmp_input.split(",");
        List<Integer> ret_list = new ArrayList<>();
        try {
            for (String num : tmp_arr) {
                int tmp_num = Integer.parseInt(num);
                ret_list.add(tmp_num);
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 입력 오류 입니다.");
        }

        return ret_list;
    }

    public static int Input_Win_Lotto_Bonus_number() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String tmp_input = Console.readLine();
        int price = -1;
        try {
            price = Integer.parseInt(tmp_input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 입력 오류 입니다.");
        }
        return price;
    }
}
