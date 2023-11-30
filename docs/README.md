# 미션 - 로또

## 프로젝트 설명

- 로또를 구매한 뒤, 당첨 번호를 추첨하여 수익률을 계산해 주는 프로그램

## 기능 목록

### 내부 기능

#### 로또 게임 시작

- 로또 프로그램을 시작하는 기능
- 사용자에게 입력 받은 구매금액을 토대로 로또 구매 개수를 계산하는 기능

#### 로또 게임 중

- 6개의 로또 번호를 로또 구매 개수만큼 생성하는 기능
- 생성된 각 로또 번호와 당첨 번호의 숫자 일치 여부를 계산하는 기능
- 각 로또의 총 당첨 번호 개수를 계산하는 기능
- 특정 로또의 번호가 5개 일치 시 보너스 볼 일치 여부를 계산하는 기능

#### 로또 게임 종료

- 당첨된 로또 개수를 계산하는 기능
- 총 수익률을 계산하는 기능

### 사용자 인터페이스

#### 로또 게임 시작

- 로또 구입금액 입력 안내 문구 출력
- 로또 구입금액 입력

#### 로또 게임 중

- 로또 구매 개수 안내 문구 출력
- 로또 추첨 내역 출력
- 당첨 번호 입력 안내 문구 출력
- 당첨 번호 6개를 쉼표로 구분해 입력
- 보너스 번호 입력

#### 로또 게임 종료

- 당첨 통계 안내 문구 출력
- 구분선 출력
- 번호 일치 개수와 당첨 금액, 당첨된 로또 개수 출력
- 총 수익률 출력

### 예외 처리

- 로또 구입금액 입력
    - 구입금액이 양의 정수가 아닐 시 IllegalArgumentException 발생
    - 구입금액이 로또 가격(1,000원) 단위가 아닐 시 IllegalArgumentException 발생
- 로또 생성
    - 로또 개수가 6개 초과 시 IllegalArgumentException 발생
    - 로또 개수가 6개 미만일 시 IllegalArgumentException 발생
    - 각 로또의 로또 번호 중 서로 중복된 로또 번호가 존재할 시 IllegalArgumentException 발생
    - 각 로또 번호가 1과 45 사이의 값이 아닐 시 IllegalArgumentException 발생
    - 로또 번호가 오름차순으로 정렬되어 있지 않을 시 IllegalArgumentException 발생
- 당첨 번호 입력
    - 당첨 번호가 6개가 아닐 시 IllegalArgumentException 발생
    - 당첨 번호가 양의 정수가 아닐 시 IllegalArgumentException 발생
    - 당첨 번호가 1과 45 사이의 값이 아닐 시 IllegalArgumentException 발생
    - 당첨 번호가 오름차순으로 입력되지 않을 시 IllegalArgumentException 발생
    - 서로 중복된 당첨 번호가 존재할 시 IllegalArgumentException 발생
- 보너스 번호 입력
    - 보너스 번호가 양의 정수가 아닐 시 IllegalArgumentException 발생
    - 보너스 번호가 1과 45 사이의 값이 아닐 시 IllegalArgumentException 발생
    - 보너스 번호가 당첨 번호와 중복될 시 IllegalArgumentException 발생
