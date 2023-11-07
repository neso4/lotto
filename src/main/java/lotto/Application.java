package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    static final int Lotto_Price = 1000;
    public static void main(String[] args) {
        int lotto_purchase_amount;
        List<Lotto> lottos;
        Lotto winning_numbers;
        int bonus_number;
        Map<LottoRank, Integer> winning_lottos;
        
        lotto_purchase_amount = inputLottoPurchase() / Lotto_Price ;
        lottos = lottoIssuance(lotto_purchase_amount);
        printLottos(lottos, lotto_purchase_amount);
        winning_numbers = inputWinningNumbers();
        bonus_number = inputBonusNumber(winning_numbers);
        winning_lottos = rankingJudgement(lottos, winning_numbers, bonus_number);
        printResult(winning_lottos, lotto_purchase_amount);
    }

    static void printResult(Map<LottoRank, Integer> winning_lottos, int lotto_purchase_amount){
        int prize = 0;
        System.out.println("\n당첨 통계\n---");
        for (LottoRank lotto_rank : LottoRank.values()) {
            if (lotto_rank == LottoRank.OUT){
                continue;
            }
            System.out.println(lotto_rank.getComment() + winning_lottos.get(lotto_rank) +"개");
            prize += winning_lottos.get(lotto_rank) * lotto_rank.getPrize();
        }
        System.out.println("총 수익률은 "+ String.format("%.1f", checkYield(prize, lotto_purchase_amount)) +"%입니다.");
    }

    static double checkYield(int prize, int lotto_purchase){
        return (double) prize / lotto_purchase / 10;
    }

    static Map<LottoRank, Integer> rankingJudgement(List<Lotto> lottos, Lotto winning_numbers, int bonus_number){
        Map<LottoRank, Integer> winning_lottos = makeLottoRankMap();
        for (Lotto lotto : lottos) {
            LottoRank lottoRank = lotto.rankJudgement(winning_numbers, bonus_number);
            winning_lottos.put(lottoRank, winning_lottos.get(lottoRank)+1);
        }
        return winning_lottos;
    }

    static Map<LottoRank, Integer> makeLottoRankMap(){
        Map<LottoRank, Integer> lottos_ranks = new HashMap<LottoRank, Integer>();
        for (LottoRank rank : LottoRank.values()) {
            lottos_ranks.put(rank, 0);
        }
        return lottos_ranks;
    }

    static int inputBonusNumber(Lotto winning_numbers){
        int bonus_number;
        while(true){
            try {
                String input_bonus_number = inputMethod("보너스 번호를 입력해 주세요.");
                bonus_number = stringToInt(input_bonus_number);
                bonusNumberCheck(winning_numbers, bonus_number);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonus_number;
    }

    static void bonusNumberCheck(Lotto winning_numbers, int bonus_number){
        if (winning_numbers.numberCheck(bonus_number)){
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 겹칩니다.");
        }
    }

    static Lotto inputWinningNumbers(){
        Lotto winning_numbers;
        while(true){
            try {
                String input_winning_numbers = inputMethod("당첨 번호를 입력해 주세요.");
                winning_numbers = new Lotto(stringToIntArray(input_winning_numbers));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return winning_numbers;
    }

    static List<Integer> stringToIntArray(String str){
        List<Integer> list_integer;
        String[] str_array = str.split(",");
        try {
            int[] int_array = Arrays.stream(str_array).mapToInt(Integer::parseInt).toArray();
            list_integer = Arrays.stream(int_array).boxed().collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자가 아닌 값이 입력되었습니다.");
        }

        return list_integer;
    }

    static void printLottos(List<Lotto> lottos, int lotto_purchase_amount){
        System.out.println("\n" + Integer.toString(lotto_purchase_amount) + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    static int inputLottoPurchase(){
        int lotto_purchase_amount;
        while(true){
            try {
                String input_lotto_purchase = inputMethod("구입금액을 입력해 주세요.");
                lotto_purchase_amount = stringToInt(input_lotto_purchase);
                checkMultiple1000(lotto_purchase_amount);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return lotto_purchase_amount;
    }

    static ArrayList<Lotto> lottoIssuance(int purchase_amount){
        ArrayList<Lotto> lottos = new ArrayList<Lotto>();
        for (int i =0; i < purchase_amount; i++){
            lottos.add(new Lotto(pickLottoNumbers()));
        }
        return lottos;
    }

    static List<Integer> pickLottoNumbers(){
        List<Integer> lotto_numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return lotto_numbers;
    }

    static int stringToInt(String value) throws IllegalArgumentException{
        int num;
        try {
            num = Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자 값을 입력해야 합니다.");
        }
        return num;
    }

    static void checkMultiple1000(int num) throws IllegalArgumentException{
        if (num % Lotto_Price != 0){
            throw new IllegalArgumentException("[ERROR] 1000의 배수를 입력해야 합니다.");
        }
    }

    static String inputMethod(String announcement){
        System.out.println("\n" + announcement);
        return Console.readLine();
    }
}
