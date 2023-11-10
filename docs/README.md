## 기능 목록



### Store.class

* [x] 미션 - 로또 실행 - start()
* [x] 로또 구입 금액을 입력 받는다. - inputMoney()
* [x] 구입 금액이 숫자인지 확인한다 - validateIsNumber()
* [x] 1000원 단위로 받으며 1000으로 나누어 떨어지지 않는 경우 예외 처리한다. - validateUnitOfThousandMoney()
* [x] 구입 금액에 해당하는 만큼 로또를 발행한다. 로또 1장의 가격은 1000원이다. - giveLotto()
* [x] 로또 번호로 1~45까지 범위 중 중복되지 않은 숫자 6개를 발행한다. - makeLottoNumber()
* [x] 당첨 번호와 보너스 번호를 입력 받는다. - inputWinningLotto()
* [x] 당첨 번호를 입력 받는다. - inputWinningNumbers()
* [x] 입력 된 당첨 번호가 숫자들인지 확인한다. - validateNumbersIsNumber()
* [x] 보너스 번호를 입력 받는다. - inputBonusNumber()

### Lotto.class

* [x] 로또 번호의 숫자 범위는 1~45까지이다. - validateNumberRange()
* [x] 로또 번호를 발행할 6개의 숫자를 뽑는지 검증한다. - validate()
* [x] 로또 번호가 중복되는지 검증한다. - validateDuplicatedNumber()
* [x] 로또 번호를 오름차순으로 정렬한다. - sortNumbers()
* [x] 발행한 로또 수량 및 번호를 출력한다. - printLotto()

### WinningLotto.class  

* [x] 당첨 번호의 모든 검증 로직을 수행한다. - validateNumbers()
* [x] 보너스 번호의 모든 검증 로직을 수행한다. - validateBonusNumber()
* [x] 당첨 번호와 숫자 범위는 1~45까지이다. - validateNumberRange()
* [x] 번호 중 하나의 숫자 범위가 1~45인지 확인한다. - checkNumberRange()
* [x] 당첨 번호는 6개의 숫자를 뽑고 보너스 번호는 1개의 숫자를 뽑는다. - validateSize()
* [x] 당첨 번호가 중복인지 검증한다. - validateNumbersDuplicated()
* [x] 당첨 번호와 보너스 번호가 중복인지 검증한다. - validateNumbersBonusNumbersDuplicated()
* [x] 당첨 번호를 정렬한다. - sortNumbers()

### Grader.class

* [x] 사용자가 구매한 로또들의 번호와 당첨 번호를 비교하여 당첨 통계를 구한다. - compareLottoWinningLotto()
* [x] 로또 번호와 당첨 번호를 비교하여 결과를 반환한다. - compareNumber()
* [x] 비교한 결과를 받아 등수로 변환한다. - checkRank()
* [x] 수익률을 계산한. 소수점 둘째 자리에서 반올림한다. - calculateEarningRatio()

### PrintResult.class

* [x] 당첨 통계를 출력합니다. - winningDetailPrint()
* [x] 수익률을 출력합니다. - earningRatioPrint()
  
### PrintError.class

* [x] 구입 금액이 숫자가 아닙니다 -  moneyNotNumber()
* [x] 구입 금액이 1000단위가 아닙니다. - moneyUnitOfThousand()
* [x] 로또 번호가 6자리가 아닙니다. - numberSizeMessage()
* [x] 로또 번호가 1~45 사이의 숫자가 아닙니다. - numberRangeMessage()
* [x] 로또 번호가 중복된 숫자가 있습니다. - numberDuplicatedMessage()
  
  



