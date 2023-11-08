package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;


public class Input {

    public int inputPrice() {
        int price = 0;
        boolean validPrice = false;

        do {
            try {
                price = readInputPrice();
                validPrice = true;
            } catch (IllegalArgumentException wrongPrice) {
                System.out.println("[ERROR] " + wrongPrice.getMessage());
            }
        } while (!validPrice);
        return price;
    }

    public int readInputPrice() {
        String rawPrice = Console.readLine();
        checkPrice(rawPrice);
        return Converter.parseToInt(rawPrice);
    }

    public void checkPrice(String rawPrice) {
        int convertedPrice = Converter.parseToInt(rawPrice);

        if (!(convertedPrice % 1000 == 0 && convertedPrice > 0)) {
            throw new IllegalArgumentException("1000원 단위의 정수로 입력해주세요");
        }
    }

    public List<Integer> inputWinNumbers() {
        List<Integer> winNumbers = new ArrayList<>();
        boolean validWinNumbers = false;

        do {
            try {
                winNumbers = readInputWinNumbers();
                validWinNumbers = true;
            } catch (IllegalArgumentException wrongWinNumber) {
                System.out.println("[ERROR] " + wrongWinNumber.getMessage());
            }
        } while (!validWinNumbers);

        return winNumbers;
    }

    public List<Integer> readInputWinNumbers() {
        String rawNumbers = Console.readLine();
        return convertedWinNumber(rawNumbers);
    }

    public List<Integer> convertedWinNumber(String rawNumbers) {
        List<Integer> splitNumbers = new ArrayList<>();
        String nowNumbers = rawNumbers;
        while (nowNumbers.contains(",")) {
            int index = nowNumbers.indexOf(",");
            String tempNumbers = nowNumbers.substring(0, index);
            splitNumbers = checkAndthenAdd(tempNumbers, splitNumbers);
            nowNumbers = nowNumbers.substring(index + 1);
        }
        int convertedRawNumber = Converter.parseToInt(nowNumbers);
        splitNumbers.add(convertedRawNumber);
        checkWinNumberSize(splitNumbers);
        return splitNumbers;
    }

    public List<Integer> checkAndthenAdd(String tempNumber, List<Integer> splitNumbers) {
        int convertedTempNumber = Converter.parseToInt(tempNumber);
        checkNumberRange(convertedTempNumber);
        checkSameNumber(convertedTempNumber, splitNumbers);
        splitNumbers.add(convertedTempNumber);
        return splitNumbers;
    }

    public void checkNumberRange(int tempNumber) {
        if (!(tempNumber>=1 && tempNumber<=45)) {
            throw new IllegalArgumentException("1부터 45까지의 정수를 입력해 주세요"); // 값이 하드코딩되어있음
        }
    }

    public void checkWinNumberSize(List<Integer> winNumbers) {
        if (!(winNumbers.size() == 6)) {
            throw new IllegalArgumentException("6개의 정수를 입력해주세요"); // 하드코딩해놧음 수정필요
        }
    }

    public void checkSameNumber(int tempNumber, List<Integer> winNumbers) {
        if (winNumbers.contains(tempNumber)) {
            throw new IllegalArgumentException("중복되지않는 6개의 정수를 입력해주세요");
        }
    }

    public  int inputBonusNumber (List<Integer> winNumbers) {
        int bonusNumber = 0;
        boolean validBonusNumbers = false;

        do {
            try {
                bonusNumber = readInputBonusNumber(winNumbers);
                validBonusNumbers = true;
            } catch (IllegalArgumentException wrongBonusNumber) {
                System.out.println("[ERROR] " + wrongBonusNumber.getMessage());
            }
        } while (!validBonusNumbers);

        return bonusNumber;
    }

    public int readInputBonusNumber (List<Integer> winNumbers) {
        int bonusNumber = Converter.parseToInt(Console.readLine());
        checkNumberRange(bonusNumber);
        checkSameBonusNumber(bonusNumber, winNumbers);
        return bonusNumber;
    }

    public void checkSameBonusNumber(int bonusNumber, List<Integer> winNumbers) {
        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버는 당첨번호와 중복 될 수 없습니다.");
        }
    }
}
