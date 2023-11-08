package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    private static int moneyValidate(){
        int money;

        while(true){
           try{
               System.out.println("구입금액을 입력해 주세요.");
               money =Integer.valueOf(Console.readLine());

               if ( (money % 1000) != 0) {
                   throw new IllegalArgumentException("[ERROR]1,000원 단위로 입력하세요.");
               }
               break;
           }catch (NumberFormatException e){
               throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
           }
        }
        return money;
    }

    private static List<Lotto> generateLottes(int trial){
        List<Lotto> Lottos = new ArrayList<>();

        for(int i=0; i< trial; i++){
            List<Integer> lottsnumber = new ArrayList<>();
            while(lottsnumber.size() < 6){
                int number =  Randoms.pickNumberInRange(1,45);
                if(!lottsnumber.contains(number)){
                    lottsnumber.add(number);
                }
            }
            Lottos.add(new Lotto(lottsnumber));
        }
        return Lottos;
    }

    private  static void printLottes(List<Lotto> lottos,int ticket){
        System.out.println(ticket+ "개를 구매했습니다.");
        for(int i=0; i< ticket;i++)
            lottos.get(i).printLotto();
    }

    private static String[] getWinningnumber(){
        String input;
        String[] numbers;
        while(true){
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                input = Console.readLine();
                numbers = input.split(",");

                if(numbers.length != 6){
                    throw new IllegalArgumentException("6개의 당첨 번호를 입력하세요.");
                }
                break;
            }
            catch (NumberFormatException e){
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
            }
        }
        return numbers;
    }

    private static int getBonceNumber(List<Integer> winningnumber){
        int input;
        while (true){
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                input=  Integer.valueOf(Console.readLine());
                if(winningnumber.contains(input)){
                    throw new IllegalArgumentException("[ERROR] 중복된 번호입니다.");
                }
                break;
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
            }
        }
        return input;
    }

    private  static List<Integer> stringToList(String[] input){
        List<Integer> List = new ArrayList<>();
        List<Integer> integerList = Arrays.stream(input)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return List;
    }

    private  static  void resultPrint(int tiral, List<Lotto> lottos,List<Integer> winningnumber ){
        System.out.println("당첨 통계\n"+ "---");
        int[] lottosresult =getCheckNumber(tiral,lottos,winningnumber);

        System.out.println("3개 일치 (5,000원) -"+ lottosresult[4] +"개\n"
                + "4개 일치 (50,000원) - "+ lottosresult[3] +"개\n"
                + "5개 일치 (1,500,000원) - "+ lottosresult[2] +"개\n"
                + "5개 일치, 보너스 볼 일치 (30,000,000원) - "+ lottosresult[1] +"개\n"
                + "6개 일치 (2,000,000,000원) - "+ lottosresult[0] +"개");

        rateReturnPrint(lottosresult, tiral);
    }
    private  static void rateReturnPrint(int[] lottosresult, int tiral){
       System.out.println("총 수익률은"+ (double)(calculatePrize(lottosresult)/tiral)+"%입니다.");

    }

    private  static  int calculatePrize(int[] lottsresult){
        int prize =0;
        return prize = (2000000000 * lottsresult[0]) + (30000000 * lottsresult[1]) + (1500000 * lottsresult[2]) +
                (50000 * lottsresult[3]) +(5000 * lottsresult[4]);

    }
    private  static int checkNumber(Lotto lottoticket,List<Integer> winningnumber ){
        return (int) lottoticket.getNumbers().stream().filter(winningnumber::contains).count();
    }

    private  static int checkWinning(int matchdnumber, boolean isbonusmatch){
        if (matchdnumber == 6)
            return 0;
        if (matchdnumber ==5 && isbonusmatch)
            return 1;
        if (matchdnumber == 5)
            return 2;
        if (matchdnumber == 4)
            return 3;
        if (matchdnumber == 3)
            return 4;

        return matchdnumber;
    }
    private static int[] getCheckNumber(int tiral, List<Lotto> lottos,List<Integer> winningnumber){
        int[] lottosresult = new int[5];
        for(int i =0; i < tiral; i++){
            Lotto lottoticket =  lottos.get(i);
            int matchednumber = checkNumber(lottoticket,winningnumber);
            boolean isbonusmatch = lottoticket.getNumbers().contains(winningnumber.get(6));
            lottosresult[checkWinning(matchednumber, isbonusmatch)] +=1;
        }
        return lottosresult;
    }
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int money;
        int ticket;
        int bouncenumber;
        List<Lotto> lottes;
        List<Integer> winningnumber;

        money = moneyValidate();
        ticket = money /1000;
        lottes = generateLottes(ticket);

        printLottes(lottes,ticket);

        winningnumber = stringToList(getWinningnumber());

        winningnumber.add(getBonceNumber(winningnumber));

        resultPrint(ticket, lottes, winningnumber);


    }

}
