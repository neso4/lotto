# TODO

## **기능 명세**

### 🎟️ Lotto

- [x] 번호에 맞는 로또를 만든다. -> Lotto()
  - 6개의 당첨 숫자
  - 1~45 사이의 숫자
  - 중복되지 않는 로또 번호

- [x] 갖고 있는 숫자를 오름차순으로 출력한다. -> printNumbersInAscendingOrder()
  - [x] 갖고 있는 숫자를 오름차순으로 정렬한다. -> getsortedNumbers()

- [x] 다른 로또와 겹치는 개수를 반환한다. -> getHowManySameNumbers()
- [x] 보너스 볼의 존재 유뮤를 반환한다. (실행 전제 조건: 로또의 나머지 5개의 숫자는 모두 갖고 있다.)-> hasBonus


### 🤑 Player
- [x] 로또 수량 만큼 로또를 갖는다. -> Player(int n)
- [x] 발행한 로또들의 번호를 오름차순으로 출력한다. -> OutputService.printLottos()

예시 출력, 입력
```
8개를 구매했습니다.
[8, 21, 23, 41, 42, 43] 
[3, 5, 11, 16, 32, 38] 
[7, 11, 16, 35, 36, 44] 
[1, 8, 11, 31, 41, 42] 
[13, 14, 16, 38, 42, 45] 
[7, 11, 30, 40, 42, 43] 
[2, 13, 22, 32, 38, 45] 
[1, 3, 5, 14, 22, 45]

```

### 🎮 Game
- [x] 당첨 번호를 입력받는다.  -> askWinningNumbers()
    - [x] 빈 문자열 검증한다. -> string.isBlank
    - [x] 숫자, 구분자가 아닌 값 검증한다. -> parseInt 
    - [x] 검증 실패시 계속 다시 입력 받는다. -> while문
- [x] 보너스 번호를 입력받는다. -> askBonusNumbers()
    - [x] 빈 문자열 검증한다.
- [x]  로또 금액을 입력받는다. -> askMoney()
   - 1000원 단위
   - 1000원 이상, 21*10^8 이하로 제한한다.
- [x] 발행한 로또 금액에 맞는 로또 수량을 계산한다. convertMoneyToLotto()
- [x] 로또 금액에 맞는 수량만큼 player에게 지급한다. -> new Player(int n)

### ⛳️  GameResult
- [x]  당첨 내역을 검증한다.
  - [x] 3,4[GameResultOutputService.java](..%2Fsrc%2Fmain%2Fjava%2Flotto%2FService%2FGameResultOutputService.java),5,6개가 일치하는 로또의 개수를 각각 센다. (5개가 일치하는 로또의 경우, 보너스 번호가 같은지도 센다.)  -> calculateResult()
     - Lotto.howManySameNumbers(), Lotto.hasBonus()
     - [x] Enum 구현
- [x] 수익을 계산한다. ->  getRateOfReturn() 
- [x]  당첨 내역을 출력한다. -> printResult()
  - [x] 수익률 출력 -> printRateOfReturn() 
      - 수익률은 소수점 둘쨰자리에서 반올림한다.

예시출력

    ```agsl
    당첨 통계
    3개 일치 (5,000원) - 1개
    4개 일치 (50,000원) - 0개
    5개 일치 (1,500,000원) - 0개
    5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
    6개 일치 (2,000,000,000원) - 0개
    총 수익률은 62.5%입니다.
    ```



### test

- [ ]  실패하는 단위 테스트를 작성할 때까지 실제 코드를 작성하지 않는다.
- [ ]  컴파일은 실패하지 않으면서 실행이 실패하는 정도로만 단위 테스트를 작성한다.
- [ ]  현재 실패하는 테스트를 통과할 정도로만 실제 코드를 작성한다.
- [ ]  깔끔한 테스트 코드를 작성한다.
    - [ ]  테스트 당 assert 하나
    - [ ]  given-when-then
    - [ ]  FIRST 규칙 (빨라야, 서로 독립적이어야, 언제든 반복가능해야, 성공 혹은 실패로 결과가 나와야, 구현 직전에 적시에 작성해야.)



### refactor
- [ ] 함수는 15줄 이하
- [ ]  indent depth 2 이하
- [ ] else , switch 문 금지
- [ ]  3항 연산자 금지
- [ ] Enum 사용
- [ ]  함수는 한가지 일만
- [ ]  JUnit 5, AssertJ`