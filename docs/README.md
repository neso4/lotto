# 미션 - 로또

1 ~ 45까지 중복 되지 않는 6개의 숫자를 입력 받아 로또 게임을 진행 한다.

당첨 번호 : 중복 되지 않는 숫자 6개, 보너스 번호 1개

- 1등: 6개 번호 일치 / 2,000,000,000원
- 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
- 3등: 5개 번호 일치 / 1,500,000원
- 4등: 4개 번호 일치 / 50,000원
- 5등: 3개 번호 일치 / 5,000원
## 패키지 구조

```
📦lotto
 ┣ 📂controller
 ┃ ┗ 📜LottoController.java
 ┣ 📂domain
 ┃ ┣ 📂constant
 ┃ ┃ ┗ 📜LottoConstant.java
 ┃ ┣ 📂number
 ┃ ┃ ┣ 📜NumberGenerator.java
 ┃ ┃ ┗ 📜RandomNumberGenerator.java
 ┃ ┣ 📜BonusNumber.java
 ┃ ┣ 📜Lotto.java
 ┃ ┣ 📜LottoBundle.java
 ┃ ┣ 📜LottoFactory.java
 ┃ ┣ 📜LottoOrder.java
 ┃ ┣ 📜LottoResult.java
 ┃ ┣ 📜Money.java
 ┃ ┣ 📜Rank.java
 ┃ ┗ 📜WinningNumber.java
 ┣ 📂exception
 ┃ ┣ 📂lotto
 ┃ ┃ ┣ 📜BonusDuplicateException.java
 ┃ ┃ ┣ 📜LottoDuplicateException.java
 ┃ ┃ ┣ 📜LottoFormatException.java
 ┃ ┃ ┣ 📜LottoRangeException.java
 ┃ ┃ ┗ 📜LottoSizeException.java
 ┃ ┣ 📂money
 ┃ ┃ ┣ 📜MinimumMoneyException.java
 ┃ ┃ ┣ 📜MoneyOutOfRangeException.java
 ┃ ┃ ┗ 📜MoneyUnitException.java
 ┃ ┗ 📜NonNumberException.java
 ┣ 📂util
 ┃ ┗ 📜Convertor.java
 ┣ 📂view
 ┃ ┣ 📜InputView.java
 ┃ ┗ 📜OutputView.java
 ┗ 📜Application.java

```
## 🚀 기능 목록

### 로또 구매 금액 입력
- [X] 로또 구입 금액을 입력 받는다.
- [X] [예외] 사용자가 잘못된 값을 입력할 경우
  - [X] 공백일 경우
  - [X] 숫자가 아닐 경우
  - [X] 1,000원으로 나누어 떨어지지 않는 경우
  - [X] 1,000보다 작은 값을 입력할 경우
  - [X] 정수 최댓값 보다 큰 수를 입력할 경우

### 당첨 번호 입력
- [X] 6개의 당첨 번호를 쉼표(,)를 기준으로 입력 받는다.
- [X] [예외] 사용자가 잘못된 값을 입력할 경우
  - [X] 공백인 경우
  - [X] 숫자가 아닐 경우
  - [X] 1 ~ 45사이의 숫자를 입력하지 않을 경우
  - [X] 중복된 숫자가 있을 경우
  - [X] 6개의 숫자가 아닐 경우
  - [X] 잘못된 구분자를 입력한 경우

### 보너스 번호 입력
- [X] 1개의 보너스 번호를 입력 받는다.
  - [X] [예외] 사용자가 잘못된 값을 입력할 경우
    - [X] 공백인 경우
    - [X] 숫자가 아닐 경우
    - [X] 당첨 번호와 중복 된 번호를 입력할 경우
    - [X] 1 ~ 45사이의 숫자를 입력 하지 않을 경우

### 당첨 번호 저장
- [X] 입력 받은 당첨 번호를 저장 한다.
- [X] 입력 받은 보너스 번호를 저장 한다.

### 로또 결과 계산 기능
- [X] 당첨 번호와 구매한 로또번호 리스트를 저장한다.
- [X] 당첨 번호와 비교 후 등수를 계산한다.

### 발행 한 로또 출력 기능
- [X] 발행 한 로또 수량을 출력 한다.
- [X] 발행한 로또 번호를 오름차순으로 정렬해서 출력 한다.

### 당첨 통계 출력 기능
- [X] 당첨 결과를 순위 별로 출력한다.
  - [X] 천 단위 구분자로 출력한다.
- [X] 총 수익률을 출력한다.
  - [X] 천 단위 구분자로 출력한다.
  - [X] 수익률은 소수점 둘째 자리에서 반올림 한다.

### 예외 처리
- [X] 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.