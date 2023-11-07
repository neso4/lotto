package lotto.dto;

import lotto.util.Ranking;

import java.util.Map;

public record ResultDto(Map<Ranking, Integer> rankingCounts, double benefitRate) {
}
