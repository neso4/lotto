package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.model.BonusNumber;
import lotto.model.GenerateLottoNumbers;
import lotto.model.LottoWinNumbers;
import lotto.model.MatchedNumbers;
import lotto.model.PurchasePrice;
import lotto.model.ReturnPercentage;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoControl {
    PurchasePrice purchasePrice;
    GenerateLottoNumbers generateLottoNumbers;
    LottoWinNumbers lottoWinNumbers;
    BonusNumber bonusNumber;
    MatchedNumbers matchedNumbers;
    ReturnPercentage returnPercentage = new ReturnPercentage();
    private int PIECE_OF_LOTTO;
    private ArrayList<List<String>> ALL_LOTTOS;
    private String BONUS_NUMBER;
    private boolean[] BONUS_FLAG;


    public LottoControl(){
        getPurchasePrice();
        printPieceOfLottos();

        generateLottos();
        printAllLottos();

        getLottoWinNumbers();

        getBonusNumber();

        numberMatching();
        mappingMatchedLottos();
        fiveWithBonusFinder(matchedNumbers.getLOTTO_MATCHED_COUNT_LIST());
        OutputView.printLottoStates(matchedNumbers.getLOTTO_MATCHED_COUNT_LIST());
        printTotalReturnPercentage();

    }
    private void mappingMatchedLottos(){
        BONUS_FLAG = new boolean[PIECE_OF_LOTTO];
        for(int index = 0; index < ALL_LOTTOS.size(); index++){
            if(ALL_LOTTOS.get(index).contains(BONUS_NUMBER)){
                BONUS_FLAG[index] = true;
            }
        }
    }
    private void fiveWithBonusFinder(ArrayList<String> matchedCountList){
        for(String count : matchedCountList){
            if(count.equals("5") && BONUS_FLAG[matchedCountList.indexOf(count)]){
                matchedCountList.set(matchedCountList.indexOf(count),"5wB");
            }
        }
    }
    private void printTotalReturnPercentage() {
        float percentage = returnPercentage.getReturnPercentage(matchedNumbers.getLOTTO_MATCHED_COUNT_LIST(),
                String.valueOf(PIECE_OF_LOTTO));
        OutputView.printTotalReturn(percentage);
    }

    private void numberMatching(){
        matchedNumbers = new MatchedNumbers(generateLottoNumbers.getAllLottos(),lottoWinNumbers.getLottoWinNumbers());
    }
    private void getBonusNumber(){
        try{
            bonusNumber = new BonusNumber(getBonusNumberFromUser());
            isDuplicateWithWinNumbers();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            getBonusNumber();
        }
        BONUS_NUMBER = bonusNumber.getBonusNumber();
    }
    private void isDuplicateWithWinNumbers() throws IllegalArgumentException{
        if(lottoWinNumbers.getLottoWinNumbers().contains(bonusNumber.getBonusNumber())){
            throw new IllegalArgumentException("보너스 번호는 당첨번호와 중복될 수 없습니다.");
        }
    }
    private String getBonusNumberFromUser(){
        return InputView.getBonusNumberFromUserInput();
    }
    private void getLottoWinNumbers(){
        try{
            lottoWinNumbers = new LottoWinNumbers(getLottoWinNumbersFromUser());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            getLottoWinNumbers();
        }
    }
    private String getLottoWinNumbersFromUser(){
        return InputView.getWinNumberFromUserInput();
    }

    private void generateLottos(){
        generateLottoNumbers = new GenerateLottoNumbers(PIECE_OF_LOTTO);
    }
    private void getPurchasePrice(){
        try{
            purchasePrice = new PurchasePrice(getPriceFromUser());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            getPurchasePrice();
        }
    }
    private String getPriceFromUser(){
        return InputView.getPriceFromUserInput();
    }
    private void setPieceOfLotto(){
        PIECE_OF_LOTTO = purchasePrice.getCountPieceOfLotto();
    }

    private void printPieceOfLottos(){
        setPieceOfLotto();
        OutputView.printPieceOfLottoAmount(PIECE_OF_LOTTO);
    }
    private void printAllLottos(){
        setAllLottos();
        OutputView.printAllLottos(ALL_LOTTOS);
    }
    private void setAllLottos(){
        ALL_LOTTOS = generateLottoNumbers.getAllLottos();
    }


}
