/**
 * @Package_name   : model
 * @Class_name     : LottoResultJudger
 * <p>
 * Create Date : 2023-11-07
 * Create User : 정은채
 */
package model;

import java.util.ArrayList;
import model.enums.LottoWinResults;

public class LottoResultJudger {
    private ArrayList<Integer> resultLottoNum;
    private int resultBonusNum;

    public LottoResultJudger(ArrayList<Integer> resultLottoNum, int resultBonusNum){
        this.resultLottoNum = resultLottoNum;
        this.resultBonusNum = resultBonusNum;
    }

    /**
     * Description : 비교할 로또 번호를 결과번호와 비교하여 결과 반환
     *
     * @Method : inputLottoNum()
     * @return : String
     */
    public String judgeLottoResult(ArrayList<Integer> lottoNum){
        int countCorrectNum = checkResultLottoNum(lottoNum);
        boolean isBonusIn = checkResultBonusNum(lottoNum);

        if ( countCorrectNum == 3 ){
            return LottoWinResults.LOTTO_3SAME.getMessage();
        }else if (countCorrectNum == 4 ){
            return LottoWinResults.LOTTO_4SAME.getMessage();
        }else if (countCorrectNum == 5  && isBonusIn){
            return LottoWinResults.LOTTO_5SAME.getMessage();
        }else if (countCorrectNum == 5 ){
            return LottoWinResults.LOTTO_5SAME_BONUS.getMessage();
        }
        return LottoWinResults.LOTTO_6SAME.getMessage();
    }

    /**
     * Description : 결과로또번호와 비교
     *
     * @Method : checkresultLottoNum()
     * @return : int
     */
    public int checkResultLottoNum(ArrayList<Integer> lottoNum){
        int countCorrectNum = 0;
        for ( int num : lottoNum ){
            if (resultLottoNum.contains(num)){
                countCorrectNum++;
            }
        }
        return countCorrectNum;
    }

    /**
     * Description : 보너스 번호와 비교
     *
     * @Method : inputLottoNum()
     * @return : boolean
     */
    public boolean checkResultBonusNum(ArrayList<Integer> lottoNum){
        if (lottoNum.contains(resultBonusNum)){
            return true;
        }
        return false;
    }

}
