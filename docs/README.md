# 미션 - 로또 🎲
아래와 같은 규칙으로 진행되는 로또 게임 기능 구현

```
- 로또 번호의 숫자 범위는 1~45까지이다.
- 1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다.
- 당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑는다.
- 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.
  - 1등: 6개 번호 일치 / 2,000,000,000원
  - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
  - 3등: 5개 번호 일치 / 1,500,000원
  - 4등: 4개 번호 일치 / 50,000원
  - 5등: 3개 번호 일치 / 5,000원
```

### 구현 목표

1. 함수(메소드)를 분리하자.
2. 함수(메소드)별로 테스트를 작성하자.
3. 클래스(객체)를 분리하는 연습을 하자.
4. 도메인 로직에 대한 단위 테스트를 작성하는 연습을 하자.

# 요구 사항

## 프로그래밍 요구 사항 / 최종 점검

- [x] JDK 17

- [x] indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현

- [x] 3항 연산자 쓰지 않기

- [x] 함수가 한 가지 일만 하도록 최대한 작게 만들기

- [ ] JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작하는지 테스트 코드르 확인 ( ... )

- [x] 함수의 길이가 15라인을 넘어가지 않도록 구현

- [x] else 예약어를 사용하지 않기

- [x] switch/case문 사용하지 않기

- [x] Java Enum 적용하기

- [ ] **도메인 로직**에 단위 테스트를 구현 ( ... )

## 기능 요구 사항

### 정상 상황

- [x] 로또 번호를 **발행**하는 기능 - pickUniqueNumbersInRange() < 제공 라이브러리 >

- [x] 로또 구입 금액을 **입력**하는 기능 - domain ~ Purchase

  - [x] 로또 구입 금액을 입력하는 기능 - Purchase#initializeAmount();

  - [x] 로또를 **구입**하는 기능 - Purchase#initializePurchasedLotto();

  - [x] 로또 수량을 **출력**하는 기능 - Purchase#getAmount();

  - [x] 로또 번호를 **출력**하는 기능 - Purchase#printPurchasedLotto(); , Lotto#printLottoNumber();

  - [x] 로또 구입 금액이 올바른지 **판단**하는 기능

- [x] 당첨 번호를 **입력**하는 기능 - lotto ~ WinningLotto

  - [x] 당첨 번호가 올바른지 **판단**하는 기능

- [x] 보너스 번호를 **입력**하는 기능 - WinningLotto#initializeBonusNumber();

  - [x] 보너스 번호가 올바른지 **판단**하는 기능

- [x] 당첨 등수와 상금을 표현하는 Enum 생성 - constant ~ Rank

- [x] 당첨 등수를 **계산**하는 기능 - domain ~ Ranking

  - [x] 몇 등인지 **계산**하는 기능 + 보너스 볼 - Ranking#matchLotto();

    - [x] 당첨 번호와 로또 번호를 **비교**하는 기능 - Ranking#matchedLottoNumberCount();

    - [x] 보너스 번호와 로또 번호를 **비교**하는 기능 - Ranking#matchBonusNumber();

      - [x] 보너스 번호를 포함하고 있는지 **확인**하는 기능 - Ranking#isContainBonusNumber();

  - [x] 당첨된 로또의 개수를 **증가**하는 기능 - Ranking#updateRankCounts(); 

- [x] 당첨 통계 기능 - domain ~ Statistics

  - [x] 당첨 통계를 **출력**하는 기능 - Ranking#getRank(); Ranking#printRanking();

  - [x] 수익률을 **계산**하는 기능 - Ranking#sumRevenue();, Ranking#totalRevenue();

  - [x] 수익률을 **출력**하는 기능 - Ranking#printRevenue();

### 예외 상황 - "[ERROR]"로 에러 메시지 시작

- validation ~ InputException

- 로또 구입 금액 입력 시, Purchase#validate();

  - [x] 입력이 없는가 - InputException#blankInput();

  - [x] 숫자로 변환 가능한 입력인가 - InputException#notNumber();

  - [x] 천원 이하의 입력인가 - InputException#underThousand();

  - [x] 천원 단위로 나누어지지 떨어지는가 - InputException#notMultipleOfThousand();

- 당첨 번호 입력 시, - WinningLotto#stringInputValidate();, WinningLotto#listValidate();

  - [x] 입력이 없는가 - InputException#blankInput();
 
  - [x] 쉼표(,)와 관련된 입력이 정상인가 - InputException#onlyComma();

    - [x] , 전 후 공백 입력

  - [x] 숫자로 변환 가능한 입력인가 - InputException#cannotParseToInt();

  - [x] 번호의 개수가 6개를 넘는가 - InputException#notSixNumberInput();

  - [x] 1부터 45 사이의 입력인가 - InputException#wrongNumberRange();

  - [x] 번호가 중복 되는가 - InputException#numberDuplicat();

- 보너스 번호 입력 시, - WinningLotto#bonusNumberValidate();

  - [x] 입력이 없는가 - InputException#blankInput();

  - [x] 숫자로 변환 가능한 입력인가 - InputException#notNumber();

  - [x] 1부터 45 사이의 입력인가 - InputException#wrongNumberRange();

  - [x] 당첨 번호와 중복 되는가 - InputException#isDuplicatedWithWinningNumbers();