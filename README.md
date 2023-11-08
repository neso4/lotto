# 미션 - 로또

## 🚀 기능 요구 사항

로또 게임 기능을 구현해야 한다. 로또 게임은 아래와 같은 규칙으로 진행된다.

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

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 만큼 로또를 발행해야 한다.
- 로또 1장의 가격은 1,000원이다.
- 당첨 번호와 보너스 번호를 입력받는다.
- 사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.

## 📌 구현할 기능

- [X] 금액 입력 안내문 출력 (view)
- [X] 로또 금액 입력 (view -> model)
  - [X] 양수인지 검증 (view)
  - [X] 금액이 1000원으로 나누어 떨어지지 않는지 검증 (model)
- [X] 구매 수량 계산 (model)
- [X] 로또 구매 (model)
- [X] 로또 발행 (model)
  - [X] 1부터 45까지의 로또 번호 6개 생성
  - [X] 로또 번호간 중복 검증
  - [X] 로또 번호 오름차순 정렬
- [ ] 구매개수 출력 (view <- model)
- [ ] 로또 번호 출력 (view <- model)
- [ ] 당첨 번호 입력 안내문 출력 (view)
- [ ] 당첨 번호 입력 (view -> model)
  - [ ] 당첨 번호 문자열을 쉼표를 기준으로 구분 (view)
  - [ ] 당첨 번호가 6개인지 검증 (model)
  - [ ] 당첨 번호가 1부터 45까지인지 검증 (model)
  - [ ] 당첨 번호가 중복인지 검증 (model)
- [ ] 보너스 번호 입력 안내문 출력 (view)
- [ ] 보너스 번호 입력 (view -> model)
  - [ ] 양수인지 검증 (view)
  - [ ] 1부터 45인지 검증 (model)
  - [ ] 당첨 번호와 중복인지 검증 (model)
- [ ] 로또 당첨 확인 (model)
- [ ] 수익률 계산 (model)
- [ ] 당첨 통계 출력 (view <- model)