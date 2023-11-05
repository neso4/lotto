## 🔴🟢🟠 3주차 미션 - 로또 게임 

### 📌 개요
로또 구입 금액과 당첨 번호와 보너스 번호를 입력받고, 구매한 로또 번호와 당첨 번호를 비교하여 추첨하는 게임이다.
게임을 완료한 후 당첨 내역 및 수익률을 출력하고, 로또 게임을 종료한다.

- - -

### 📌 동작 방식
- 로또 구입 금액을 입력한다. (1000원 단위만 성립)
  - 입력받은 금액만큼 로또를 구매해야 한다.
    * 입력받은 금액을 로또금액으로 나누어 구매 개수를 보여준다.
- 각 차수별로 발행한 로또 수량 및 번호를 출력한다. (로또 번호는 오름차순 정렬)
  - 1~45까지 랜덤한 숫자를 발행 후, 중복되지 않는 6개의 숫자를 뽑는다.
- 당첨 번호를 "," 기준으로 입력한다.
- 보너스 번호를 입력한다.
- 당첨 내역을 출력한다.
  - 수익률을 소수점 둘째 자리에서 반올림하여 출력한다. (ex. 100.0%, 51.5%, 1,000,000.0%)

- - -

### 📌 기능 구현 목록

**<게임>**
- [] 로또 구매 개수 만큼 1~45사이의 랜덤 숫자를 6개를 한세트로 발행한다.
  - [] 무작위 숫자가 중복되지 않는 6개의 숫자를 발행 해야한다.
- [] 사용자가 구매한 로또 번호와 당첨 번호를 비교한다.
  - [] 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.
    - 1등: 6개 번호 일치 / 2,000,000,000원
    - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등: 5개 번호 일치 / 1,500,000원
    - 4등: 4개 번호 일치 / 50,000원
    - 5등: 3개 번호 일치 / 5,000원

**<입력>**
- [] 사용자는 로또 구입 금액을 입력한다.
  - [] 로또 1장의 가격은 1,000원이다.
  - [] 구입 금액은 1,000원 단위로 입력 받으며 거스름돈이 남을 시, 예외처리 한다.
  - [] 입력받은 금액을 로또금액으로 나누어 구매 개수를 보여준다.
- [] 당첨 번호를 입력 받는다. 번호는 쉼표(,)를 기준으로 구분한다.
- [] 보너스 번호를 입력 받는다. 
  - [] 당첨 번호와 보너스 번호 총 7개 숫자 입력 시, 중복을 허용하지 않는다.

**<출력>**
- [] 발행한 로또 수량 및 번호를 출력한다. 로또 번호는 오름차순으로 정렬하여 보여준다.
- [] 당첨 내역을 통계하여 출력한다. 
- [] 로또 게임을 완료한 후, 수익률을 출력한다.
  - [] 수익률은 소수점 둘째 자리에서 반올림한다. (ex. 100.0%, 51.5%, 1,000,000.0%)

### 📌 예외 처리  []
- [] 에러 문구는 "[ERROR]"로 시작해야 한다.

### 📌 테스트 코드 []


- - -