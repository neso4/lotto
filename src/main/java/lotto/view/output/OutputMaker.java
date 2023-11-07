package lotto.view.output;

import java.util.List;
import java.util.Objects;

public class OutputMaker {
    public String lottoNumberCheckMaker(List<Integer> numbers) {
        StringBuilder lottoNumber = new StringBuilder();
        Integer lastNumber = numbers.get(numbers.size() - 1);
        lottoNumber.append("[");
        for (Integer number : numbers) {
            if (!Objects.equals(number, lastNumber)) {
                lottoNumber.append(number).append(", ");
            }
            if (Objects.equals(number, lastNumber)) {
                lottoNumber.append(number);
            }
        }
        lottoNumber.append("]");
        return lottoNumber.toString();
    }

    public String visualizeWinningGrade(int[] winningGrade) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("3개 일치 (5,000원) - ").append(winningGrade[0]).append("개\n");
        stringBuilder.append("4개 일치 (50,000원) - ").append(winningGrade[1]).append("개\n");
        stringBuilder.append("5개 일치 (1,500,000원) - ").append(winningGrade[2]).append("개\n");
        stringBuilder.append("5개 일치, 보너스 볼 일치 (30,000,000원) - ").append(winningGrade[3]).append("개\n");
        stringBuilder.append("6개 일치 (2,000,000,000원) - ").append(winningGrade[4]).append("개\n");
        return stringBuilder.toString();
    }

    public String makeProfitMessage(double profitRate) {
        StringBuilder profitMessage = new StringBuilder();
        profitMessage.append("총 수익률은 ").append(profitRate).append("%입니다.");
        return profitMessage.toString();
    }
}
