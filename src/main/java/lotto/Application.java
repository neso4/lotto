package lotto;

import java.util.*;

import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int purchaseAmount=InputView.getPurchaseAmount();
        int numberOfLotto=purchaseAmount/1000;
        OutputView.printNumberOfLotto(numberOfLotto);

        List<Lotto> lottoTickets=new ArrayList<>();

        for(int i=0;i<numberOfLotto;i++){
            List<Integer> randomNumbers=LottoNumbersMaker.generateRandomNumbers();
            Lotto lottoTicket=new Lotto(new ArrayList<>(randomNumbers));
            lottoTickets.add(lottoTicket);
        }

        for (Lotto lottoTicket: lottoTickets){
            OutputView.printLottoTicketNumbers(lottoTicket);
        }

        List<Integer> winningNumbers=new ArrayList<>();
        String[] winningNumbersInput=InputView.getWinningNumbers().split(",");
        for(String winningNumber:winningNumbersInput){
            winningNumbers.add(Integer.parseInt(winningNumber));
        }

        int bonusNumber=InputView.getBonusNumber();
        EnumMap<Rank, Integer> rankCounts=new EnumMap<>(Rank.class);
        int totalPrizeMoney=0;
        for(Lotto lottoTicket: lottoTickets){
            int countForMatchingWinningNumbers=0;
            int countForMatchingBonusNumbers=0;
            List<Integer> ticketNumbers=lottoTicket.getNumbers();
            for(int ticketNumber: ticketNumbers){
                if(winningNumbers.contains(ticketNumber)){
                    countForMatchingWinningNumbers++;
                }
            }
            if(ticketNumbers.contains(bonusNumber)){
                countForMatchingBonusNumbers++;
            }

            Rank rank=Rank.calculateRank(countForMatchingWinningNumbers, countForMatchingBonusNumbers);
            if(rank!=Rank.NOTHING){
                totalPrizeMoney+=rank.getPrizeMoney();
            }
            rankCounts.put(rank, rankCounts.getOrDefault(rank, 0)+1);
        }

        OutputView.printResults(rankCounts);
        OutputView.printEarningPercent(totalPrizeMoney, purchaseAmount);

    }
}
