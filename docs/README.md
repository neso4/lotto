### 기능 목록

- 구입 금액을 입력한다. - Price#inputPrice()
  - 구입 금액의 유효성을 검사한다. - Price#isValidPrice()
    - 문자가 있거나 / 1000 단위가 아닐 경우, [ERROR] 메시지를 출력하고 다시 입력 받는다. - Price#validatePrice()


- 로또를 생성해서 출력한다. - Lotto
  - 구입 금액 / 1000 만큼 로또를 생성한다. - LottoGenerator#generateLotto()
  - 로또의 유효성을 검사한다. - Lotto#validate()
    - 로또 번호가 6개가 아니거나 / 중복될 경우, [ERROR] 메시지를 출력한다.


- 당첨 번호를 입력한다. - Winning#inputNumbers()
  - 문자열로 입력을 받고, 정수형으로 변환해서 저장한다. - Price#parseAndSetNumbers()
  - 당첨 번호의 유효성을 검사한다. Winning#isValidNumbers()
    - 당첨 번호가 6개가 아니거나 / 1부터 45 사이의 숫자가 아니거나 / 중복될 경우, [ERROR] 메시지를 출력하고 다시 입력 받는다. - Winning#validateNumbers()
  - 당첨 번호가 구매한 로또 번호 중에 있는지 확인한다. - Winning#getMatchedCount()


- 보너스 번호를 입력한다. - Winning#inputBonusNumber()
  - 보너스 번호 유효성 검사 - Winning#isValidBonus()
    - 보너스 번호가 1개가 아니면, [ERROR] 메시지를 출력하고 다시 입력 받는다. - Winning#validateBonus()
  - 보너스 번호가 구매한 로또 번호 중에 있는지 확인한다. - Winning#isMatchedBonus()


- 당첨 통계를 출력한다. - Application#printWinningStatistics()
  - Enum 타입의 클래스로 통계를 생성한다. - Result
  - 사용자가 구입한 로또 중 당첨된 로또가 있는지 확인한다. - Winning#checkWinning()
    - 당첨된 로또가 구매한 로또 중에 몇 개가 있는지 확인한다. - Winning#getWinningCount()


- 총 수익률을 출력한다.
  - ( 로또 갯수 * 당첨 금액 * 100 ) / 구입 금액 으로 수익률을 계산하고 소수점 둘째자리까지 반올림해서 저장한다. - Profit#getRounding()