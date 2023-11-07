package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        //1.로또 구입 금액 입력 받기, 구매 갯수 구하기.
        int purchase_price = Base_Func.Input_Purchase_Price();
        int purchase_num = Base_Func.Purchase_Lotto_Number(purchase_price);

        //2.purchase_num 만큼 로또 번호를 생성해 list에 저장하고 출력한다.
        ArrayList<Lotto> Buy_lotto = Make_Buy_Lotto_List(purchase_num);
        Base_Func.Print_Purchased_Lotto_list(Buy_lotto);

        //3.당첨 번호와 보너스 번호를 입력받는다.
        List<Integer> Win_lotto_list = Base_Func.Input_Win_Lotto_List();
        int bonus_number = Base_Func.Input_Win_Lotto_Bonus_number();

        //4. 구매한 로또 당첨 여부를 확인.
        //index 0,1,2는 사용하지 않고, 7은 2등 당첨을 3,4,5,6은 일치 갯수를 저장 한다.
        int[] Jackpot_list = Make_Jackpot_List(Buy_lotto, Win_lotto_list, bonus_number);
        for(int i=0; i<purchase_num; i++)
            System.out.println(i+"개: "+Jackpot_list[i]);
    }

    public static ArrayList<Lotto> Make_Buy_Lotto_List(int purchase_num) {
        ArrayList<Lotto> Buy_lotto = new ArrayList<Lotto>();
        for (int i = 0; i < purchase_num; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto tmp_lotto = new Lotto(numbers);
            Buy_lotto.add(tmp_lotto);
        }
        return Buy_lotto;
    }

    public static int[] Make_Jackpot_List(ArrayList<Lotto> Buy_lotto, List<Integer> Win_lotto_list, int bonus_number) {
        int[] Jackpot_list = {0, 0, 0, 0, 0, 0, 0, 0};
        for (Lotto buy_lotto : Buy_lotto) {
            int jp = buy_lotto.Lotto_Jackpot_Return(Win_lotto_list, bonus_number);
            Jackpot_list[jp] += 1;
        }

        return Jackpot_list;
    }


}
