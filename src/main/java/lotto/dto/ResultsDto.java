package lotto.dto;

import java.util.List;
import lotto.constant.Rank;
import lotto.domain.Result;

public record ResultsDto(List<ResultDto> resultsDto) {
    public static ResultsDto of(final Result result) {
        return new ResultsDto(result.getRankCount()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Rank.NONE)
                .map((entry -> ResultDto.of(entry.getKey(), entry.getValue())))
                .sorted()
                .toList());
    }
}
