package lotto.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.Model.VO.LottoData;

public class GameModel {
    private LottoPlayer lottoPlayer;
    private LottoHost lottoHost;


    public void initGameModel(Integer insertData){
        lottoPlayer = new LottoPlayer(insertData);
        lottoHost = new LottoHost();
    }

    public void initAnswerByStrings(String insertData){
        List<String> splitData= Arrays.asList(insertData.split(","));
        validateIntegerList(splitData);

        List<Integer> answerNumbers = splitData.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        lottoHost.initAnswerLotto(answerNumbers);
    }

    public void initBonus(Integer insertNumber){
        lottoHost.initBonusNumber(insertNumber);
    }


    private void validateIntegerList(List<String> insertDatas){
       if(isContainsChar(insertDatas)){
           throw new IllegalArgumentException("[ERROR] 당첨번호는 숫자값들로만 이루어져야 합니다.");
       }
    }

    private boolean isContainsChar(List<String> insertDatas){
        for (String data : insertDatas) {
            try {
                Integer.parseInt(data);
            } catch (NumberFormatException e) {
                return true;
            }
        }
        return false;
    }

    public void lottoIssuance(){
        Integer purchaseQuanity = lottoPlayer.orderLottos();
        giveLottos(purchaseQuanity);
    }


    private void giveLottos(Integer quanity){
        for(int i=0;i<quanity;i++){
            Lotto lotto = lottoHost.generateLotto();
            lottoPlayer.receiveLotto(lotto);
        }
    }


    public ArrayList<LottoData> transferPlayerLottoDatas(){
        return lottoPlayer.getLottoDatas();
    }
}
