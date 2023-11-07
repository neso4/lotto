package lotto;

import java.util.*;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
	public static void main(String[] args) {
		List<Integer> lottoNum = getLotto();
		int countLotto = countLotto();
	}

	public static int countLotto() { // 구매한 횟수 계산
		int money = 0;

		money = Integer.parseInt(Console.readLine());

		if (money % 1000 != 0)
			throw new IllegalArgumentException();

		return money / 1000;
	}

	public static List<Integer> getLotto() {
		return Randoms.pickUniqueNumbersInRange(1, 45, 6);
	} // 로또 발행

	public static List<Lotto> makeLotto(int count) {
		List<Lotto> lottoRandomList = new ArrayList<Lotto>();

		for (int i = 0; i < count; i++) {
			lottoRandomList.add(new Lotto(getLotto()));
		}

		return lottoRandomList; // 발행 로또 목록
	}

	public static void printLottoList(List<Lotto> lottoList) {
		// 로또 리스트 출력
		for (Lotto lotto : lottoList) {
			System.out.println(lotto.toString());
		}
	}

	public static Lotto inputUserLotto() { // 당첨 로또 입력
		List<Integer> number = new ArrayList<Integer>();

		for (String num : Console.readLine().split(","))
			number.add(Integer.parseInt(num));

		countSize(number);
		Lotto lotto = new Lotto(number);

		return lotto;
	}

	public static void countSize(List<Integer> calculateSize) {
		// 로또 사이즈 검사
		if (calculateSize.size() > 6)
			throw new IllegalArgumentException();
	}

	public static void printResult(List<Integer> lottoNum) {
		System.out.println("당첨 통계");
		System.out.println("---");
		System.out.println();

		System.out.println("3개 일치 (5,000원) - " + num + "개");
		System.out.println("4개 일치 (50,000원) - " + num + "개");
		System.out.println("5개 일치 (1,500,000원) - " + num + "개");
		System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + num + "개");
		System.out.println("6개 일치 (2,000,000,000원) - " + num + "개");
		System.out.println("총 수익률은 " + percentage + "%입니다.");
	}
}
