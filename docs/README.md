## 기능 목록

- [x] 구입 금액으로 로또 몇장을 구입할 수 있는지 계산한다.(로또 1장당 1,000원) - LottoManager#calculateNumberOfLottos()
  - [x] 구입 금액이 1,000원단위로 나누어 떨어지는지 검사한다. - LottoManager#validatePurchaseAmount()
- [x] 로또를 생성한다. - LottoFactory#createLotto()
- [x] 매개변수로 들어온 장수만큼 로또를 생성한다. - Lottos#createLottos()
- [x] 1 ~ 45까지의 중복하지 않는 난수 6개를 `List<Integer>`로 생성한다. - NumberGenerator#generateRandomNumbers()

- [x] 몇개의 번호가 담청되었는지 알 수 있다. - WinnigLotto#countMatchingNumbers()
- [ ] 보너스 번호와 일치하는 번호가 있는지 검사한다. - BonusNumber#isContains()
- [ ] 당첨 기준에 맞게 금액을 배당한다. Prizes#distributePrize()
- [ ] 각 등수별 상금 합계를 계산한다. Prizes#calculateTotalPrizeForRank()
- [ ] 총 상금을 계산한다. Prizes#calculateTotalPrize()
- [ ] 수익률을 계산한다. (수익률은 소수점 둘째 자리에서 반올림) - LottoManager#calculateProfitRate()

### 입력
- [ ] 로또 구입 금액을 입력 받을 수 있다. - InputView#askLottoPurchaseAmount()
- [ ] 당첨 번호를 입력 받을 수 있다. - InputView#askWinnigNumbers()
- [ ] 보너스 번호를 입력 받을 수 있다. - InputView#askBonusNumber()
    - [ ] 입력 값이 숫자인지 검사한다. - InputValidator#validateNumeric()
    - [ ] 입력 값이 양수인지 검사한다. - InputValidator#validatePositiveNumber()
      
### 출력
- [ ] 발행한 로또 수량을 출력한다. - OutputView#showLottoAmount()
- [ ] 발행한 로또 번호를 출력한다. - OutputView#showLottoNumbers()
- [ ] 당첨 내역을 출력한다. - OutputView#showLottoResult()
- [ ] 수익률을 출력한다. - OutputView#showProfitRate()