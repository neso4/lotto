## 기능 구현 목록
- [x] 로또 구입 금액 입력 받기 Lotto#getLottoPurchaseAmount()
  - [x] 예외) 1000원 단위가 아니면 예외처리해준다. - Buyer#validatePurchaseAmount()
  - [x] 예외) 입력에 숫자만 있는지 체크 - validator#isNumberic()
- [x] 로또 구입 금액에 맞게 로또 번호를 출력해주는 기능 - Buyer#generateLottoNumbers()
  - [x] 로또 번호를 랜덤하게, 중복되지 않게 숫자 6개와 보너스 번호 1개를 뽑는다. - getLottoNumber(), getBonusNumber()
- [x] 당첨 번호와 보너스 번호를 입력받는다. - Buyer#takeWinningNumbers()
  - [x] 입력받은 당첨 번호가 모두 쉼표로 구분된 숫자인지 확인 - validator#validateWinningNumber()
  - [x] 예외) 당첨 번호의 수가 6개가 아니면 예외 처리 - Lotto#validate()
  - [x] 예외) 보너스 번호의 수가 1개가 아니면 예외 처리 - validator#validateBonnusNumber()
  - [x] 예외) 중복된 숫자가 있으면 예외 처리 - validator#isUniqueLottoNumber()
- [x] 구매 번호와 담첨 번호 비교해 - Lotto#LottoResults()
  - [x] 당첨 내역 출력하고  - LottoReporter#lottoReport()
  - [x] 수익률을 출력 - LottoReporter#rateOfReturn()


- 로또 번호의 숫자 범위는 1~45까지이다.
- 1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다.
- 당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑는다.
- 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.
    - 1등: 6개 번호 일치 / 2,000,000,000원
    - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등: 5개 번호 일치 / 1,500,000원
    - 4등: 4개 번호 일치 / 50,000원
    - 5등: 3개 번호 일치 / 5,000원
  
로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행해야 한다.
로또 1장의 가격은 1,000원이다.
당첨 번호와 보너스 번호를 입력받는다.
사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다.
사용자가 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.