# 미션 - 로또
- 당첨번호와 사용자의 번호를 비교하여 당첨 통계를 알려주는 로또 게임 프로젝트

---

## 목차
~~~
1. 게임 진행 순서
2. 기능 요구 사항
   1. 입출력
   2. 진행
   3. 당첨 조건
   4. 수익률 계산
   5. 예외 처리
3. 기능 구현 목록
~~~

## 게임 진행 순서
1. 사용자는 구매 금액을 입력한다.
2. (구매 금액 / 1000) 만큼 로또를 발행한다.
3. 발행된 로또들을 한줄 씩 출력한다.
4. 당첨 번호를 입력한다.
5. 보너스 번호르 입력한다.
7. 발행한 로또마다 당첨번호와 보너스 번호를 비교하여 당첨 결과 및 수익률을 구한다.
8. 당첨 통계를 출력 후 종료.

## 기능 요구 사항
### 1. 입출력 요구 사항
- 구매 금액을 입력해야 한다.
- 구매 금액은 1000원으로 나누어 떨어져야 한다.
- 로또 번호의 숫자 범위는 1~45까지이다.
- 당첨 번호 6개를 중복없이 입력한다.
- 당첨 번호는 쉼표(,)를 기준으로 구분되어야 한다.
- 보너스 번호를 입력해야 한다.
- 보너스 번호는 1개이다.
- 입력에 대한 예외를 처리한다.
- 통계를 정해진 포맷에 맞춰 출력해야 한다.
~~~ 
당첨 통계
---
3개 일치 (5,000원) - 1개
4개 일치 (50,000원) - 0개
5개 일치 (1,500,000원) - 0개
5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
6개 일치 (2,000,000,000원) - 0개
총 수익률은 62.5%입니다.
~~~

### 2. 진행 요구 사항
- 구매 금액 입력만큼 로또를 발행한다.
- 로또 1장의 가격은 1,000원이다.
- 발행된 로또 번호를 당첨 번호와 보너스 번호로 비교한다.
- 각 로또를 비교 한 결과를 합계한다.
- 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다.

### 3. 당첨 조건 요구 사항
~~~
- 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.
    - 1등: 6개 번호 일치 / 2,000,000,000원
    - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등: 5개 번호 일치 / 1,500,000원
    - 4등: 4개 번호 일치 / 50,000원
    - 5등: 3개 번호 일치 / 5,000원
~~~

### 4. 수익률 계산 요구 사항
- 수익률은 소수점 둘째 자리에서 반올림한다.

### 5. 예외 처리 요구 사항
- 예외 발생 시 프로그램이 비정상으로 종료되면 안된다.
- 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시킨다.
- IllegalArgumentException 발생 시 "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
- Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.

## 구현 기능 목록

