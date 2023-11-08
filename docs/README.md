## 기능 목록 ##

- [x] 구매자로 부터 구매할 금액을 입력받는다.   - NumberGenerator#inputBuyCost()
    - [x] 예외처리 : 금액은 1000원 단위로 나누어 떨어져야 한다. - NumberGeneratorVaildation#inputBuyCostUnitVaildation()
    - [x] 예외처리 : 금액은 숫자여야 한다. - NumberGeneratorVaildation#inputBuyCostIntegerVaildation()
- [x] 1부터 45까지의 서로 다른 숫자 6개를 생성한다. - NumberGenerator#createRandomNumbers()
    - [x] 로또는 한개에 1,000원 단위로 구매할 개수를 구한다. - NumberGenerator#createUnitLotto()
    - [x] 구매 개수 만큼 로또를 생성한다.  -NumberGenerator#createLottoNumbers()
    - [x] 로또 번호는 오름차순으로 정렬해야 한다.   - NumberGenerator#sortAscendingNumbers()
- [x] 로또 당첨 번호 입력 - NumberGeneartor#InputCorrectLotto()
    - [x] 로또 당첨 번호 정수형 변환 - NumberGenerator#conversionInputToInteger()
        - [x] 입력 번호 정수인지 유효성 검사 - NumberGeneratorVaildation#inputNumberIntegerVaildation()
    - [x] 로또 당첨 번호 6자리 유효성 검사 - Lotto#numberLengthvaildation
    - [x] 로또 당첨 번호 범위 유효성 검사 - Lotto#numberSizeVaildation
    - [x] 로또 당첨 번호 중복 유효성 검사 - Lotto#numberDuplecateVaildation
- [x] 보너스 번호 입력 - NumberGeneartor#InputBonusNumber()
    - [x] 보너스 번호 정수 유효성 검사 - NumberGeneratorVaildation#bonueNumberIntegerVaildation()
    - [x] 보너스 번호 중복 유효성 검사 - NumberGeneratorVaildation#bonusNumDuplicateVaildation()
    - [x] 보너스 번호 범위 유효성 검사 - NumberGeneratorVaildation#bonusNumRangeVaildation()
- [x] 몇 개의 숫자가 같은지 알 수 있다. - Judgment#correctCount()
    - [x] 6개 번호 일치 (1등 / 2,000,000,000원)
    - [x] 5개 번호 + 보너스 번호 일치 (2등 / 30,000,000원)
    - [x] 5개 번호 일치 (3등 1,500,000원)
    - [x] 4개 번호 일치 (4등 / 50,000원)
    - [x] 3개 번호 일치 (3등 / 5,000원)

## 기능 요구 사항 ##

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