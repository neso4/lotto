package lotto.controller;

import lotto.model.Number;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private List<Number> boughtLotto = new ArrayList<>();
    public LottoController(InputView inputView,OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startLotto(){
        int price = inputView.readPrice();
        int count = price/1000;
        createBoughtLotto(count);


        outputView.printBoughtLotto(count,boughtLotto);

    }

    public void createBoughtLotto(int count){
        for(int i=0;i<count;i++){
            Number number = new Number();
            boughtLotto.add(number);
        }
    }
}
