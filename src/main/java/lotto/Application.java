package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.text.DecimalFormat;

public class Application {
    public static int lottoBought;
    public static int[] userNumListInt;
    public static int userBonusNum;

    public static void main(String[] args) {
        // TODO: 로또 구입 금액 입력 받기
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                lottoBought = Integer.parseInt(Console.readLine());

                // 예외 처리
                int lottoBought1000 = lottoBought % 1000;
                if (lottoBought1000 != 0) {
                    throw new IllegalArgumentException("1,000원 단위로 입력해 주세요.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }

        // TODO: 로또 번호 출력
        int lottoNum = lottoBought / 1000;
        System.out.println();
        System.out.println(lottoNum + "개를 구매했습니다.");

        // stream.Collectors 사용하기
        List<List<Integer>> allLottoList = IntStream.range(0, lottoNum)
                .mapToObj(i -> Randoms.pickUniqueNumbersInRange(1, 45, 6)
                        .stream()
                        .sorted()
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        allLottoList.forEach(System.out::println);

        // TODO: 당첨 번호 입력 받기
        while (true) {
            try {
                System.out.println();
                System.out.println("당첨 번호를 입력해 주세요.");
                String userNum = Console.readLine();

                String[] userNumListStr = userNum.split(",");

                userNumListInt = new int[userNumListStr.length];
                for (int i = 0; i < userNumListStr.length; i++) {
                    userNumListInt[i] = Integer.parseInt(userNumListStr[i]);
                }

                if (userNum.startsWith(",") || userNum.endsWith(",")) {
                    throw new IllegalArgumentException("당첨 번호는 콤마(,)로 시작하거나 끝날 수 없습니다.");
                }
                if (userNumListInt.length != 6) {
                    throw new IllegalArgumentException("당첨 번호는 6개의 숫자여야 합니다.");
                }
                for (int i = 0; i < userNumListInt.length; i++) {
                    if (userNumListInt[i] < 1 || userNumListInt[i] > 45) {
                        throw new IllegalArgumentException("당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
                    }
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }

        // TODO: 보너스 번호 입력 받기
        while (true) {
            try {
                System.out.println();
                System.out.println("보너스 번호를 입력해 주세요.");
                String userBonusStr = Console.readLine();

                userBonusNum = Integer.parseInt(userBonusStr);

                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }

        // TODO: 로또 번호와 당첨 번호 비교
        int rank5th = 0;
        int rank4th = 0;
        int rank3rd = 0;
        int rank2nd = 0;
        int rank1st = 0;

        int prize = 0;

        for (int i = 0; i < lottoNum; i++) {
            int lottoCount = 0;
            int lottoBonusCount = 0;

            for (int j = 0; j < 6; j++) {
                if (allLottoList.get(i).contains(userNumListInt[j])) {
                    lottoCount++;
                }
                if (allLottoList.get(i).contains(userBonusNum)) {
                    lottoBonusCount = 1;
                }
            }

            if (lottoCount == 3) {
                rank5th++;
                prize += 5000;
            }
            else if (lottoCount == 4) {
                rank4th++;
                prize += 50000;
            }
            else if (lottoCount == 5) {
                rank3rd++;
                prize += 1500000;
            }
            else if (lottoCount == 5 && lottoBonusCount == 1) {
                rank2nd++;
                prize += 30000000;
            }
            else if (lottoCount == 6) {
                rank1st++;
                prize += 2000000000;
            }
        }

        // TODO: 당첨 통계 출력
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + rank5th + "개");
        System.out.println("4개 일치 (50,000원) - " + rank4th + "개");
        System.out.println("5개 일치 (1,500,000원) - " + rank3rd + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + rank2nd + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + rank1st + "개");

        // TODO: 수익률 계산
        double rateOfReturn = ((double) prize / lottoBought) * 100.0;
        System.out.println(prize);
        System.out.println(rateOfReturn);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.0%");
        String formattedRateOfReturn = decimalFormat.format(rateOfReturn / 100.0);

        // TODO: 수익률 출력
        System.out.println("총 수익률은 " + formattedRateOfReturn + "입니다.");
    }
}
