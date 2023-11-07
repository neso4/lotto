package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class Lotto {

	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		this.numbers = numbers;
	}

	private void validate(List<Integer> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException();
		}
	}

	public static List<List<Integer>> Create(int lottocount) {

		List<List<Integer>> lottos = new ArrayList<>();

		for (int i = 0; i < lottocount; i++) {

			List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);

			Collections.sort(numbers);

			lottos.add(numbers);
		}

		return lottos;
	}

	public static void printLottocount(int lottocount) {
		System.out.println();
		System.out.println(lottocount + "개를 구매했습니다.");

	}

	public static void printLottoNum(List<List<Integer>> lottos) {

		for (int i = 0; i < lottos.size(); i++) {

			System.out.println(lottos.get(i));
		}
	}

	public static List<WinningRank> checkNum(List<List<Integer>> lottos, String mynumber, String bonusnum) {

		String[] usernums = mynumber.split(",");
		List<WinningRank> rankingList = new ArrayList<>();
		int bonus = Integer.parseInt(bonusnum);

		for (int i = 0; i < lottos.size(); i++) {
			List<Integer> lotto = lottos.get(i);
			int matching = checkMatchingNumbers(lotto, usernums);

			if (matching == 5 && lottos.contains(bonus)) {
				rankingList.add(WinningRank.SECOND);
			}
			WinningRank rank = WinningRank.findByMatchingNumbers(matching);
			rankingList.add(rank);
		}
		return rankingList;
	}

	private static int checkMatchingNumbers(List<Integer> lotto, String[] usernums) {
		int matching = 0;
		for (Integer lottoNum : lotto) {
			if (Arrays.asList(usernums).contains(lottoNum.toString())) {
				matching++;
			}
		}
		return matching;
	}

}
