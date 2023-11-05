package lotto.Controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Collections;
import lotto.Service.LottoMachine;
import java.util.List;
import lotto.Model.Lotto;
import lotto.View.UIView;

public class UIController {

    public static Integer makePurchasePrice() {
        int price = 0;
        while (true) {
            try {
                UIView.printPurchasePriceInstruction();
                price = getPurchasePrice();
                validatePurchasePrice(price);

                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 구입금액은 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 1이상의 천단위 숫자만 가능합니다.");
            }
        }
        return price;
    }


    private static int getPurchasePrice() {
        return Integer.parseInt(Console.readLine());
    }


    private static void validatePurchasePrice(Integer price) {
        if (price <= 0) {
            throw new IllegalArgumentException("1이상의 숫자만 가능합니다.");
        }

        if (price % 1000 == 0) {
            return;
        }
        throw new IllegalArgumentException("천 단위의 숫자만 가능 합니다");
    }

    public static List<Lotto> makeLotto(Integer price) {
        int lottoCount = LottoMachine.lottoCount(price);
        ArrayList<Lotto> lottos = new ArrayList<Lotto>();
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> randomNumbers = LottoMachine.makeRandomNumbers();
            lottos.add(new Lotto(randomNumbers));
        }
        return lottos;
    }


    public static void makeLottoStatus(List<Lotto> lottos) {
        UIView.printLottoCount(lottos);
        UIView.printLottoNumbers(lottos);
    }


    public static List<Integer> makeWinningNumbers() {
        List<Integer> winningNumbers;
        while (true) {
            try {
                UIView.printWinningNumberInstruction();
                winningNumbers = getWinningNumber();
                validateWinningNumber(winningNumbers);
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 당첨번호는 숫자 여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 당첨번호는 1~45의 중복되지 않는 6개의 숫자 여야 합니다.");
            }
        }
        return winningNumbers;
    }


    private static List<Integer> getWinningNumber() {
        String[] line = Console.readLine().split(",");
        lineLengthCheck(line);
        ArrayList<Integer> winningNumbers = new ArrayList<>();
        for (String s : line) {
            Integer number = Integer.parseInt(s);
            numberDupCheck(winningNumbers, number);
            winningNumbers.add(number);
        }
        Collections.sort(winningNumbers);
        return winningNumbers;
    }


    private static void lineLengthCheck(String[] line) {
        if (line.length != 6) {
            throw new IllegalArgumentException("6개의 숫자만 입력 받을수 있습니다.");
        }
    }
    private static void numberDupCheck(ArrayList<Integer> winningNumbers, Integer number) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException("중복 숫자는 입력할 수 없습니다.");
        }
    }

    private static void validateWinningNumber(List<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            if (winningNumber < 1 || winningNumber > 45) {
                throw new IllegalArgumentException("당첨번호는 1~45 까지의 숫자만 허용합니다.");
            }
        }
    }


    public static Integer makeBonusNumber(List<Integer> winningNumbers) {
        Integer BonusNumber;
        while (true) {
            try {
                UIView.printBonusNumberInstruction();
                BonusNumber = getBonusNumber();
                validateBonusNumber(winningNumbers, BonusNumber);
                break;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 보너스 번호는 숫자 여야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] 보너스 번호는 1~45의 당첨번호와 중복되지 않는 숫자 여야 합니다.");
            }
        }
        return BonusNumber;
    }


    private static Integer getBonusNumber() {
        int BonusNumber;
        BonusNumber = Integer.parseInt(Console.readLine());
        return BonusNumber;
    }

    private static void validateBonusNumber(List<Integer> winningNumbers, Integer BonusNumber) {
        if (BonusNumber < 1 || BonusNumber > 45) {
            throw new IllegalArgumentException("1~45의 숫자만 가능합니다.");
        }
        if (winningNumbers.contains(BonusNumber)) {
            throw new IllegalArgumentException("당첨번호와 중복되지 않는 번호 이어야 합니다.");
        }
    }
    public static void makeStatistics(List<Lotto> lottos, List<Integer> winningNumbers,
            Integer bonusNumber, Integer price) {
        LottoMachine.countStatistics(lottos, winningNumbers, bonusNumber);

        // TODO : 통계상세 출력 , 수익률

    }
}
