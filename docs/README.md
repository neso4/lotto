# 로또

## 기능 목록
- [X] 1 ~ 45 사이 중복되지 않는 6개의 숫자를 생성한다. - NumberGenerator#createRandomNumbers()
- [X] 로또 구입 금액을 입력받을 수 있다. - LottoManager#inputLottoPurchaseAmount()
- [X] 로또 구입 금액에 따라 로또를 발행할 수 있다. - LottoManager#createLotties()
- [X] 발행한 로또를 출력할 수 있다. - LottoManager#printLotties()
  - [X] 로또를 출력할 수 있다. - Lotto#printLotto()
- [X] 로또 당첨 번호를 입력받을 수 있다 - LottoManager#inputLottoWinningNumbers()
- [X] 당첨된 로또 개수와 당첨 금액을 알 수 있다 - LottoManager#totalLotto()
  - [X] 추첨된 수(7자리)와 발행된 수(6자리)를 비교한다. - Lotto#compare()
  - [X] 당첨 기준에 따른 금액을 알 수 있다. - LottoManager#winningAmount()
- [X] 당첨된 로또 개수와 당첨 금액을 출력한다. - LottoManager#printWinning()
