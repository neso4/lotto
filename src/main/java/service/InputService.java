package service;

import camp.nextstep.edu.missionutils.Console;

import static constants.Constant.*;
import java.util.Scanner;

public class InputService {
    public static Scanner sc = new Scanner(System.in);

    public static String inputPurchasePrice(){
        System.out.println(PURCAHSE_PRICE_STRING);
        return Console.readLine();
//        return sc.next();
    }

    public static String inputWinningNumber(){
        System.out.println(WINNING_NUMBER_STRING);

        return Console.readLine();
//        return sc.next();
    }



    public static String inputBonusNumber(){
        System.out.println(BONUS_NUMBER_STRING);
        return Console.readLine();
//        return sc.next();
    }
}
