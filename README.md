# 로또

- 대상 독자
    - 2024 우테코 프리코스 백엔드 부분 참가자 및 코치
- 작성 목적
    - 소스 코드를 보기 쉽도록 함
    - 프로그램 동작 및 주의사항 소개

### 목차

- [소스 코드 구조](#소스-코드-구조)
- [프로그램 소개](#프로그램-소개)
    - [프로그램 규칙](#프로그램-규칙)
    - [입력 주의 사항 & 작동 예시](#입력-주의-사항--작동-예시)
- [과제 진행 방식](#과제-진행-방식)

## 소스 코드 구조

```text
main
└── java
    └── lotto
        ├── controller
        ├── domain
        ├── dto
        ├── view
        └── Application.java
```

- 패키지 설명
    - controller : 전반적인 실행 제어를 결정하는 컨트롤러가 있다.
    - domain : '로또 게임'의 기능적인 요구 사항을 다루는 로직을 담고 있다.
    - dto : view와 controller 사이에 데이터를 주고받을 때 사용하는 객체가 있다.
    - view : 프로그램의 입출력을 담당하는 객체가 있다.
    - Application.java : 프로그램 실행 시작 지점이다.

## 프로그램 소개

콘솔 창을 통해 로또 게임을 할 수 있는 프로그램입니다. 프로그램은 2가지 과정으로 나뉩니다.

1. 로또 구입 금액을 입력 받아, 해당 금액 만큼의 로또를 자동으로 발급 받습니다.
2. 당첨 번호와 보너스 번호를 입력 받은 후, 당첨 결과와 수익률을 출력합니다.

### 프로그램 규칙

- 로또 1장의 가격은 1,000원이다.
- 로또 번호의 숫자 범위는 1 ~ 45이다.
- 1개의 로또를 발행할 때 중복되지 않는 6개의 숫자를 뽑는다.
- 당첨 번호 추첨 시 중복되지 않는 숫자 6개와 보너스 번호 1개를 뽑는다.
- 당첨은 1등부터 5등까지 있다. 당첨 기준과 금액은 아래와 같다.
    - 1등: 6개 번호 일치 / 2,000,000,000원
    - 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
    - 3등: 5개 번호 일치 / 1,500,000원
    - 4등: 4개 번호 일치 / 50,000원
    - 5등: 3개 번호 일치 / 5,000원

### 입력 주의 사항 & 작동 예시

- 입력 주의 사항
    - 구입 금액
        - 1,000으로 나누어 떨어지는 양수를 입력해야 합니다.
        - 최소 1,000, 최대 2,147,483,000 (단, 숫자가 매우 커질 경우 시간이 오래 걸린다)
        - 예시
          ```text
          7000
          ```
    - 당첨 번호
        - 1 ~ 45 사이의 정수를 6개 입력해야 합니다.
        - 각 정수들은 모두 달라야 합니다.
        - 각 숫자 사이에는 ','를 이용하여 구분합니다.
        - 예시
          ```text
          1,3,10,13,27,44
          ```
    - 보너스 번호
        - 1 ~ 45 사이의 정수 1개를 입력해야 합니다.
        - 당첨 번호로 쓰이지 않은 수를 입력해야 합니다.
        - 예시
          ```text
          45
          ```
    - 입력 형식와 맞지 않을 경우, 해당 부분부터 다시 입력받습니다.

- 작동 예시
  ```text
  구입금액을 입력해 주세요.
  7000
  
  7개를 구매했습니다.
  [1, 3, 10, 13, 35, 41]
  [4, 9, 10, 13, 24, 36]
  [3, 7, 19, 35, 37, 43]
  [2, 8, 29, 30, 42, 44]
  [11, 29, 31, 32, 33, 41]
  [3, 4, 13, 26, 30, 38]
  [3, 7, 10, 25, 27, 44]
  
  당첨 번호를 입력해 주세요.
  1,3,10,13,27,44
  
  보너스 번호를 입력해 주세요.
  45
  
  당첨 통계
  ---
  3개 일치 (5,000원) - 0개
  4개 일치 (50,000원) - 2개
  5개 일치 (1,500,000원) - 0개
  5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
  6개 일치 (2,000,000,000원) - 0개
  총 수익률은 1,428.6%입니다.
  ```

## 과제 진행 방식

해당 프로그램은 우아한테크코스 2024 프리코스 3주차 과제입니다. 아래의 방식에 따라서 과제를 진행했습니다.

- 미션은 [세 개의 요구 사항](./docs/REQUIREMENTS.md)(**기능 요구 사항, 프로그래밍 요구 사항, 과제 진행 요구 사항**)으로 구성되어 있다.
- 세 개의 요구 사항을 만족하기 위해 노력한다. 특히 기능을 구현하기 전에 [기능 목록](./docs/README.md)을 만들고, 기능 단위로 커밋 하는 방식으로 진행한다.
- 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.
