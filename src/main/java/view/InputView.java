package view;

import java.util.*;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    public static final String PURCHASE_MESSAGE =
            "구입금액을 입력해 주세요.";
    public static final String WINNING_NUMBER_MESSAGE =
            "\n당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER_MESSAGE =
            "\n보너스 번호를 입력해 주세요.";
    public static final int LOTTO_PRICE = 1000;
    public static final int MINVALUE = 1;
    public static final int MAXVALUE = 45;
    public static final int LOTTO_SIZE = 6;

    public static int inputPurchasePrice(){
        System.out.println(PURCHASE_MESSAGE);

        int purchasePrice = checkingPurchasePrice();

        return purchasePrice;
    }

    private static int checkingPurchasePrice() {
        int purchasePrice=0;
        boolean validate = false;

        while(!validate){
            try{
                purchasePrice = checkingPurchaseWrongInputException();
                checkingPurchaseException(purchasePrice);
                validate = true;
            }catch (IllegalArgumentException e){
                if(e.getMessage().equals("WrongInput")){
                    System.out.println("[ERROR] 숫자가 아닙니다.");
                }
                else if(e.getMessage().equals("CantBuy")){
                    System.out.println("[ERROR] 로또는 천원 단위 입니다.");
                }

            }
        }
        return purchasePrice;
    }

    private static int checkingPurchaseWrongInputException() {
        int purchasePrice;
        try{
            purchasePrice = Integer.parseInt(readLine());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("WrongInput");
        }
        return purchasePrice;
    }

    private static void checkingPurchaseException(int purchasePrice) {
        if(purchasePrice % LOTTO_PRICE != 0 ){
            throw new IllegalArgumentException("CantBuy");
        }
    }

    public static List<Integer> inputWinningNumber(){
        System.out.println(WINNING_NUMBER_MESSAGE);

        List<Integer> winningNumbers = checkingWinningNumbers();

        return winningNumbers;
    }

    private static List<Integer> checkingWinningNumbers() {
        List<Integer> winningNumber = new ArrayList<>();
        boolean validate = false;

        while (!validate){
            try{
                String[] numbers = readLine().split(",");

                winningNumber = checkingWinningNumberWrongInputException(numbers);

                checkingWinningNumberOverSizeException(winningNumber);
                checkingWinningNumberOverInputException(winningNumber);
                checkingWinningNumberReplicatedException(winningNumber);

                validate = true;
            }catch (IllegalArgumentException e){
                if(e.getMessage().equals("WrongInput")){
                    System.out.println("[ERROR] 숫자가 아닙니다.");
                } else if (e.getMessage().equals("OverSize")) {
                    System.out.println("[ERROR] 로또 번호는 6개입니다.");
                } else if (e.getMessage().equals("DuplicateNumber")) {
                    System.out.println("[ERROR] 로또 번호는 중복되면 안됩니다.");
                } else if (e.getMessage().equals("OutOfRange")) {
                    System.out.println("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
                }
            }
        }
        return winningNumber;
    }

    private static List<Integer> checkingWinningNumberWrongInputException(String[] numbers) {
        List<Integer> winningNumber;
        try{
            winningNumber = Arrays.stream(numbers)
                    .map(Integer::parseInt)
                    .toList();
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("WrongInput");
        }
        return winningNumber;
    }

    private static void checkingWinningNumberOverSizeException(List<Integer> winningNumber) {
        if(winningNumber.size() != LOTTO_SIZE){
            throw new IllegalArgumentException("OverSize");
        }
    }

    private static void checkingWinningNumberReplicatedException(List<Integer> winningNumber) {
        Set<Integer> convertSet = new HashSet<>(winningNumber);
        if(convertSet.size() != LOTTO_SIZE){
            throw new IllegalArgumentException("DuplicateNumber");
        }
    }

    private static void checkingWinningNumberOverInputException(List<Integer> winningNumber) {
        for(Integer i : winningNumber){
            if(i > MAXVALUE || i<MINVALUE){
                throw new IllegalArgumentException("OutOfRange");
            }
        }
    }

    public static int inputBonusNumber(List<Integer> winningNumber){
        System.out.println(BONUS_NUMBER_MESSAGE);
        int BonusNumber = checkingBonusNumber(winningNumber);
        return BonusNumber;
    }

    private static int checkingBonusNumber(List<Integer> winningNumber) {
        int BonusNumber =  0;

        boolean validate = false;

        while (!validate){
            try{
                BonusNumber = checkingBonusNumberWrongInputException();

                checkingBonusNumberOverInputException(winningNumber, BonusNumber);
                checkingBonusNumberNumberReplicatedException(winningNumber, BonusNumber);

                validate = true;
            }catch (IllegalArgumentException e){
                if(e.getMessage().equals("WrongInput")) {
                    System.out.println("[ERROR] 숫자가 아닙니다.");
                } else if(e.getMessage().equals("DuplicateNumber")){
                    System.out.println("[ERROR] 로또 번호는 중복되면 안됩니다.");
                } else if(e.getMessage().equals("OutOfRange")){
                    System.out.println("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
                }
            }
        }
        return BonusNumber;
    }

    private static int checkingBonusNumberWrongInputException() {
        int BonusNumber;
        try{
            BonusNumber =  Integer.parseInt(readLine());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("WrongInput");
        }
        return BonusNumber;
    }

    private static void checkingBonusNumberNumberReplicatedException(List<Integer> winningNumber, int BonusNumber) {
        Set<Integer> convertSet = new HashSet<>(winningNumber);
        if(convertSet.contains(BonusNumber)){
            throw new IllegalArgumentException("DuplicateNumber");
        }
    }

    private static void checkingBonusNumberOverInputException(List<Integer> winningNumber, int BonusNumber) {
        if(BonusNumber > MAXVALUE || BonusNumber < MINVALUE){
            throw new IllegalArgumentException("OutOfRange");
        }
    }

}
