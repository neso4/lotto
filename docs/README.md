# 기능 구현 목록

## 절차적 기능
1. 로또 구입 금액을 입력받음. ✅
2. 발행한 로또 수량 및 번호를 출력함. ✅
3. 당첨 번호를 입력받음. ✅
4. 보너스 번호를 입력받음. ✅
5. 당첨 내역을 출력함.
6. 수익률을 출력함.

## 세부 기능

### 로또 구입 금액 입력
- 로또 구입 금액을 입력 받는다. ✅
- 구입 금액은 1,000원 단위로 입력받는다. ✅ 
- 금액에 대한 예외는 다음과 같이 처리한다. ✅
  - 숫자가 아닐 때: `IllegalArgumentException`
  - 양이 아닌 정수일 때: `IllegalArgumentException`
  - 1,000원 단위가 아닐 때: `IllegalArgumentException`

### 로또 수량 및 번호 출력
- 구입한 로또 수량을 출력한다. ✅
- 수량만큼 로또 번호를 생성한다. ✅
- 번호는 오름차순으로 출력한다. ✅
- 번호에 대한 예외는 다음과 같이 처리한다.
    - 값이 6개가 아닐 때: `IllegalArgumentException` ✅
    - 번호가 범위를 벗어났을 때: `IllegalArgumentException` ✅ 
    - 중복 번호가 있을 때: `IllegalArgumentException` ✅


### 당첨 번호 입력
- 당첨 번호를 입력받는다. ✅
- 당첨 번호는 총 6개이다.
- 당첨 번호는 (,)로 구분한다.
- 번호에 대한 예외는 다음과 같이 처리한다.
  - 값이 6개가 아닐 때: `IllegalArgumentException`
  - 값이 숫자가 아닐 때: `IllegalArgumentException`
  - 번호가 범위를 벗어났을 때: `IllegalArgumentException`
  - 중복 번호가 있을 때: `IllegalArgumentException`

### 보너스 번호 입력
- 보너스 번호를 입력받는다. ✅
- 입력 번호는 1개이다.
- 번호에 대한 예외는 다음과 같이 처리한다.
  - 값이 숫자가 아닐 때: `IllegalArgumentException`
  - 번호가 범위를 벗어났을 때: `IllegalArgumentException`
  - 당첨 번호와 중복된 값이 있을 때: `IllegalArgumentException`

### 당첨 내역 출력
- 구입한 로또와 당첨 및 보너스 번호를 비교한다. ✅
- 일치한 숫자의 개수에 따른 당첨된 수량을 출력한다.

### 수익률 출력
- 당첨 내역과 로또 구입 금액을 이용하여 수익률을 계산한다.
- 계산한 수익률을 출력한다.


---

# 더 생각해볼 것
- Lotto 객체에 대한 일급 컬렉션
- 당첨번호/보너스번호 관리?
  1. 당첨/보너스 번호를 포함한 당첨 관련 로직을 모두 다루는 클래스 만들기?
  2. 당첨번호+보너스번호 객체로 관리하고, 당첨 관련 로직은 따로 분리하기?
- Validator class를 따로 둬야할까?
