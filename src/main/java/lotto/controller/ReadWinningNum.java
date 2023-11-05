package lotto.controller;

import lotto.domain.InputWinningNum;
import lotto.domain.Lotto;
import lotto.views.Exceptions;

import java.util.List;

public class ReadWinningNum {
    public static boolean isWinningNumTypeOfInput(String winningNum) {
        try {
            InputWinningNum.inputWinningNumNotInt(winningNum);
            return false;
        } catch (IllegalArgumentException e) {
            Exceptions.exceptionNotTypeOfWinningInput();
            return true;
        }
    }

    public static Lotto makeWinningNumsInput(List<Integer> winningNumsInput) {
        try {
            return new Lotto(winningNumsInput);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static boolean isBonusNumInt(String bonusNum) {
        try {
            InputWinningNum.inputBonusNumNotInt(bonusNum);
            return false;
        } catch (IllegalArgumentException e) {
            Exceptions.exceptionNotInt();
            return true;
        }
    }

    public static boolean isBonusNumRange(int bonusNum) {
        try {
            InputWinningNum.inputBonusNumNotRange(bonusNum);
            return false;
        } catch (IllegalArgumentException e) {
            Exceptions.exceptionNotRange();
            return true;
        }
    }
}
