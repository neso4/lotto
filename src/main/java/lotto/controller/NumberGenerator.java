package lotto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.model.LottoInfo;

public class NumberGenerator {
    final static int LOTTOCOSTUNIT = 1000;
    static NumberGeneratorVaildation numbergeneratorvaildation = new NumberGeneratorVaildation();
    LottoInfo lottoinfo = new LottoInfo();

    public void inputBuyCost(){
        System.out.println("구입금액을 입력해 주세요.");
        String buycost = Console.readLine();
        
        while (numbergeneratorvaildation.inputBuyCostIntegerVaildation(buycost) ||
                numbergeneratorvaildation.inputBuyCostUnitVaildation(buycost)) {
            buycost = Console.readLine();
        }
        lottoinfo.setInputBuyCost(Integer.parseInt(buycost));
    }

    public void createUnitLotto(int buycost){
        int buycount = buycost/LOTTOCOSTUNIT;
        lottoinfo.setCreateLottoNumber(buycount);
    }

    public void createLottoNumbers(int buycount){
        System.out.println("\n"+buycount+"개를 구매했습니다.");
        List<List<Integer>> lottoNumbers = new ArrayList<>();
        for (int i = 0 ; i < buycount ; i++){
            lottoNumbers.add(createRandomNumber());
        }
        lottoinfo.setCreateLottoNumbers(lottoNumbers);
    }

    public List<Integer> createRandomNumber(){
        List<Integer> lotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        ArrayList<Integer> lottonumber = new ArrayList<>(lotto);
        return sortAscendingNumbers(lottonumber);
    }

    public String[] InputCorrectLotto(){
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        
        String[] numbers = input.split(",");
        while(numbergeneratorvaildation.
                inputNumberIntegerVaildation(numbers)){
            input = Console.readLine();
            numbers = input.split(",");
        }

        return numbers;
    }

    public ArrayList<Integer> conversionInputToInteger(String[] input){
        ArrayList<Integer> correctNumbers = new ArrayList<>();

        for(String st : input){
            int value = Integer.parseInt(st);
            correctNumbers.add(value);
        }

        return correctNumbers;
    }

    public ArrayList<Integer> sortAscendingNumbers(ArrayList<Integer> sortLottoNumbers){
        Collections.sort(sortLottoNumbers);
        return sortLottoNumbers;
    }

    public void InputBonusNumber(List<Integer> correctNumber){
        String bonusNum = "";
        boolean end = true;
        while(end){
            try{
                System.out.println("\n보너스 번호를 입력해 주세요.");
                bonusNum = Console.readLine();
                numbergeneratorvaildation.bonusNumberVaildation(bonusNum, correctNumber);
                end = false;
            }catch(Exception e){
                System.out.println(e);
            }
        }
        lottoinfo.setInputBonusNumber(Integer.parseInt(bonusNum));
    }
}
