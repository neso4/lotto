package lotto.controller;

import lotto.dto.WinnerAndBonusNumber;
import lotto.service.InputNumberService;

public class InputNumberController {
    private final InputNumberService inputNumberService;

    public InputNumberController(InputNumberService inputNumberService) {
        this.inputNumberService = inputNumberService;
    }

    public WinnerAndBonusNumber inputNumber() {
        // 당첨 번호 & 보너스 번호 입력
        return inputNumberService.input();
    }
}
