# 로또 (ver 1.3.0)

## 문서 Versioning 규칙

- 1.0.0부터 시작하여 다음 조건에 따라 버전이 증가한다.
  - 각 숫자는 다음을 의미한다. 1.0(Minor).0(Patch)
  - Minor: 기존 기능 외, 새로운 기능이 추가될 떄 증가한다.
  - Patch: 기존 기능에 변경점이 있거나, 기능이 추가될 떄 증가한다.

---

## 3주차 피드백 및 추가된 요구 사항 

- README.md 상세하게 작성하며, 기능 목록을 재검토하고 업데이트하기(살아있는 문서를 만들자)
- 값을 하드 코딩하지 않기(상수에 이름을 부여하여 역할들 드러내라)
- 클래스는 상수, 멤버 변수, 생성자, 메서드 순으로 작성하기
- 변수 이름에 자료형 사용하지 않기
- 한 함수가 한 가지 기능만 담당하게 하기(이 때, 함수의 길이는 15라인을 넘지 않도록 한다)
- else 예약어 사용하지 않기
- Java ENUM을 적용하기
- 도메인 로직에 단위 테스트 구현하고(System.out/in, Scanner는 제외), 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리하기

---

## 기능 목록

### Controller
- LottoController: 로또 미션의 기능을 수행하도록 명령하는 책임을 가진 클래스
  - start(): 로또 미션을 시작하도록 명령한다.
    - inputAmount(): 로또 금액(LottoAmount)을 입력받도록 명령한다.
      - InputView의 입력을 받아 LottoAmount(구매 금액) 인스턴스를 생성한다.
      - IllegalArgumentException이 발생할 경우, 에러 메시지를 출력하고, 다시 입력(inputAmount())받도록 명령한다.
    - buyLotto(): 로또를 구입하고, 구입한 로또의 갯수와 번호를 출력하도록 명령한다.
      - 구매한 로또 금액을 인자로 받아 LottoTickets 인스턴스를 생성하고 반환한다.
      - printLottoCount(): 구매한 로또의 갯수를 출력한다.
      - printLottoTickets(): 구매한 로또 번호를 출력한다.
      - 구매한 로또의 갯수와 로또 번호를 출력하도록 명령한다.
    - inputWinningNumber(): 당첨 번호를 입력하도록 명령한다.
      - InputView의 inputWinningLottoNumbers()를 호출하고, 입력을 받아 WinningNumber(당첨 번호) 인스턴스를 생성한다.
      - IllegalArgumentException이 발생할 경우, 에러 메시지를 출력하고, 다시 입력(inputWinningNumber())받도록 명령한다.
    - inputBonusNumber(): 보너스 번호를 입력하도록 명령하고, 
      - validateDuplicatedNumber(): 당첨 번호와 보너스 번호가 중복되는지 검증한다.
      - IllegalArgumentException이 발생할 경우, 에러 메시지를 출력하고, 다시 입력(inputBonusNumber())받도록 명령한다.
      - 유효성 검증에 성공하면 BonusNumber(보너스 번호) 인스턴스를 생성한다.
    - calculateWinningResult(): 로또 일치 여부를 계산하고 WinningResult(당첨 결과)를 반환한다.
      - WinningResult 인스턴스를 생성한다.
      - determineRanking(): 로또 일치 여부를 계산하여 당첨 등수를 반환한다.
      - addRanking(): 계산 결과를 반영한다.
      - printWinningResultHeader(): 당첨 통계 콘솔과 하이픈(---)을 출력하도록 명령한다.
      - printWinningResult(): 당첨 결과를 출력한다.
    - calculateEarningRate(): 수익률을 계산하도록 명령한다.
      - EarningRate(수익률) 인스턴스를 생성한다.
      - 수익률을 출력하도록 명령한다.

### Model
- Lotto: 로또 한 장을 추상화한 클래스
  - getMatchCount(): 로또 번호와 당첨 번호 일치 여부를 정수로 반환한다.
  - hasBonus(): 로또 번호 중 보너스 번호 유무를 확인하여 반환한다.
  - 유효성 검증
    - validateLottoSize(): 로또가 6자리가 아니라면 IllegalArgumentException을 발생시킨다.
    - validateDuplicateNumber(): 로또 번호가 중복된다면 IllegalArgumentException을 발생시킨다.
- LottoAmount: 로또 구매 금액을 추상화한 클래스
  - getLottoCount(): 로또 구매 갯수를 반환한다.
  - 유효성 검증
    - validateIsNumber(): 로또 금액 입력 시, 숫자가 아닌 문자가 입력된다면 IllegalArgumentException을 발생시킨다.
    - validateIsPositive(): 로또 금액 입력 시, 음수가 입력된다면 IllegalArgumentException을 발생시킨다.
    - validateAmount(): 로또 금액이 1000원으로 나누어 떨어지지 않는다면, IllegalArgumentException을 발생시킨다.

- LottoTickets: 구매한 로또들을 추상화한 클래스
  - buyLotto(): 구매 횟수를 받아 로또를 생성한다.
  - generateLottoNumbers(): 1부터 45까지 6개의 무작위 번호를 생성하고 오름차순하여 반환한다.
- WinningNumber: 당첨 번호를 추상화한 클래스
  - isContainLottoNumber() 로또 번호가 당첨 번호에 포함되어 있는지 확인하고 boolean을 반환한다.
  - 유효성 검증: validateWinningNumbers()
    - validateIsNumber(): 당첨 번호 입력 시, 숫자가 아닌 문자가 입력된다면 IllegalArgumentException을 발생시킨다.
    - validateIsPositive(): 당첨 번호 입력 시, 음수가 입력된다면 IllegalArgumentException을 발생시킨다.
    - validateWinningNumSize(): 당첨 번호의 원소가 6개가 아니라면 IllegalArgumentException을 발생시킨다.
    - validateWinningNumRange(): 당첨 번호의 원소가 1부터 45까지의 숫자가 아니라면 IllegalArgumentException을 발생시킨다.
    - validateIsDuplicated(): 당첨 번호의 원소가 중복된다면 IllegalArgumentException을 발생시킨다.
- BonusNumber: 보너스 번호를 추상화한 클래스
  - 유효성 검증
    - validateIsNumber(): 보너스 번호 입력 시, 숫자가 아닌 문자가 입력된다면 IllegalArgumentException을 발생시킨다.
    - validateBonusIsPositive(): 보너스 번호 입력 시, 음수가 입력된다면 IllegalArgumentException을 발생시킨다. 
    - validateBonusNumber(): 보너스 번호가 1부터 45까지의 숫자가 아니라면 IllegalArgumentException을 발생시킨다.
- LottoRanking: 로또 당첨 등수를 추상화한 ENUM
  - determineRanking(): 당첨 번호와 보너스 번호 일치 여부를 판단하고 당첨 등수를 반환한다.
- WinningResult : 로또 당첨 결과를 추상화한 클래스
  - addRanking(): Map에 당첨 등수와 당첨 횟수를 저장하여 반환한다.
  - getTotalPrize(): 로또 총 당첨 금액을 계산하여 반환한다.
- EarningRate: 수익률을 추상화한 클래스
  - calculateEarningRate(): 수익률을 계산하여 반환한다.

### View
- InputView: 사용자 입력을 받는 책임을 가진 클래스
  - inputLottoAmount(): 로또 구입 금액을 입력 받아 반환한다.
  - inputWinningLottoNumbers(): 당첨 번호를 입력받아 반환한다.
  - inputBonusNumber(): 보너스 번호를 입력받아 반환한다.
- OutputView: 로또 미션에 사용 될 메시지 및 로또 결과 출력을 담당하는 클래스
  - printLottoPurchaseAmountMessage(): 로또 구입 금액을 입력하라는 메시지를 출력한다.
  - printLottoCount(): 발행한 로또의 수량을 출력한다.
  - printLottoTickets(): 발행한 로또 번호들을 출력한다.
  - printEnterWinningNumber(): 당첨 번호를 입력하라는 메시지를 출력한다.
  - printEnterBonusNumber(): 보너스 번호를 입력하라는 메시지를 출력한다.
  - printWinningResultHeader(): 당첨 통계 콘솔과 하이픈(---)을 출력한다.
  - printWinningResult(): 당첨 통계를 출력한다.
  - printEarningRate(): 수익률을 출력한다.
  - printErrorMessage(): 예외 메시지(String)을 인자로 받아, 에러 메시지를 출력한다.

### Constant
- LottoConstants: 로또 미션에서 공통으로 사용되는 상수들을 모아놓은 클래스
