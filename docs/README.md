## 3주차 추가 목표
- 클래스(객체)를 분리하는 연습
- 도메인 로직에 대한 단위 테스트를 작성하는 연습

## 기능명세서

- [x] 로또 구입 금액을 입력 받는다. - InputView | inputLottoPurchaseAmount()
  - [x] (예외)로또 구입 금액이 1000원으로 나누어 떨어지지 않으면 예외처리를 한다. - InputValidator | checkDivisibleBy1000()
  - [x] (예외)로또 구입 금액 입력값이 숫자인가? - InputValidator | isNumber()
- [x] 로또를 발행한다.(개수만큼) - LottoStore | generateLottoNumbers()
- [x] 구입한 로또를 출력한다. - OutputView | printPurchasedLottos()
  - [x] 로또 번호는 오름차순으로 정렬한다.
- [x] 당첨번호를 입력받는다. - InputView | inputLottoWinningNumber()
  - [x] 번호는 쉼표(,)를 기준으로 구분하는가? - InpuValidator | checkCommaDelimiter() // 테스트 코드 (o)
  - [x] (예외)쉼표가 5개가 아닐경우 - InputValidator | checkSixNumber()
  - [x] (예외)숫자 6개를 입력받지 못했을 경우 - InputValidator | checkSixNumber()
  - [x] (예외)숫자가 아닐경우 - InputValidator | isNumber() // // 테스트 코드 (o)
  - [x] (예외)1~45의 범위를 벗어날 경우 - InputValidator | isValidRangeNumber
  - [x] (예외) 각 자리수는 서로 다른가? - InputValidator | checkDistinctNumbers()
  - [x (예외) 콤마 사이에 아무값도 입력하지 않은 경우 
- [x] 보너스 번호를 입력받는다. - InputView | inputBonusNumber()
  - [x] (예외) 숫자가 아닐경우 - isNumber()
  - [x] (예외)1~45의 범위를 벗어날 경우 - isValidRangeNumber()
  - [x] (예외) 당첨번호와 보너스번호가 겹치는가? - checkDistinctBetweenWinningAndBonusNumber()
- [x] 입력한 로또 번호와 발행한 로또 번호를 비교한다. - LottoSystem | compareLottoNumbers()
- [x] 당첨 내역을 출력한다. - OutputView | printLottoWinningStatistics()
- [x] 수익률을 계산한다.(소수점 둘째 자리에서 반올림 한다.) - caculateRateOfReturn()
- [x] 수익률을 출력한다. - OutputView | printRateOfReturn()

- [x] (전체예외) 입력값이 null이거나 empty일때 | isNullOrEmpty()

## 객체지향적으로 기능들을 바라보기

- MainController

- 로또 (Lotto)
  - 로또 타입 로또 객체

- 로또 가게 (LottoStore)
  - [x] 로또를 발행한다.
  - [x] 당첨금액을 계산한다

- 로또 시스템 (LottoSystem)
  - [x] 로또 당첨 번호를 입력받는다. (컨트롤러에서 받아서 생성자로 넘긴다.)
  - [x] 로또 보너스 번호를 입력받는다. (컨트롤러에서 받아서 생성자로 넘긴다.)
  - [] 당첨 번호와 발행된 번호를 비교한다. compareWinningNumber()

- 손님 (Customer)
  - [x] 로또를 구매한다.
  - [x] 내 수익률을 계산한다.

  

## 추가 요구사항
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
  - 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
- else 예약어를 쓰지 않는다.
  - 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
  - switch/case도 허용x
- Java Enum을 적용한다.
- 도메인 로직에 단위 테스트를 구현해야한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
  핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
  단위 테스트 작성이 익숙하지 않다면 test/java/lotto/LottoTest를 참고하여 학습한 후 테스트를 구현한다.

- 예외 상황시 에러 문구는 [ERROR] 로 시작한다.
- 제공된 Lotto 클래스를 활용해 구현해야한다.
- numbers의 접근 제어자인 private을 변경할 수 없다.
- Lotto에 필드(인스턴스 변수)를 추가할 수 없다.
- Lotto의 패키지 변경은 가능하다.

## 2주차 피드백 반영
- 기능 목록에 예외적인 상황도 정리한다.
- 값을 하드코딩하기 보다는 상수(static final)을 사용하여 무슨 역할을 하는지 이름으로 드러내라
- 변수이름에 자료형을 사용하지마라
- 한 함수가 한 가지 일만 사용하도록 해라
- 한 함수가 한 가지 기능만 담당하게 한다.
  - 여러 함수에서 중복되어 사용되는 코드가 있다면 함수 분리를 고민해본다.
  - 함수의 길이를 15라인을 넘어가지 않도록 구현하며 함수를 분리하는 의식적인 연습을 한다.
- 작은 단위의 테스트 부터 진행하라

## 1주차 피드백 반영
