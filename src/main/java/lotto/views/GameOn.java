package lotto.views;

import java.util.Scanner;

public class GameOn {
    Scanner scan = new Scanner(System.in);

    public String showCase(){
        System.out.println("당첨번호를 입력해주세요.");
        String inputLine = scan.nextLine();

        return inputLine;
    }

    public String moreCase(){
        System.out.println("보너스 번호를 입력해 주세요.");
        String inputLine = scan.nextLine();

        return inputLine;
    }
}
