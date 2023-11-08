## 0. 소개
'우아한 테크 코스' 6기 선발 과정인 프리코스에 참여하여 제공된 3주차 미션인 로또를 구현하는 레포지토리입니다.   
(우테코 프리코스 미션 공지 레포지토리 : https://github.com/woowacourse-precourse/java-lotto-6)   
</br>

## 1. 기능 구현 목록
- [x] 로또 구입 금액 입력 ex)`8000`
  - 로또 1장의 가격은 1,000원이다.
  - [x] 입력값 검증
    - [x] 양의 정수여야 한다.
    - [x] 1,000원으로 나누어 떨어져야 한다.
    - [x] 쉼표(,)을 포함하여 입력할 수 있다. 
    - [x] 이외의 값 입력 시 예외 처리 후 다시 입력받는다.
- [x] 발행한 로또 수량 및 번호 출력 ex)`8개를 구매했습니다.` `[8, 21, 23, 41, 42, 43]`
  - [x] 로또 번호의 숫자 범위는 1~45까지이다.
  - [x] 6개의 숫자를 뽑는다.
  - [x] 한 로또 내의 숫자는 중복되지 않는다. (서로 다른 로또에서의 중복은 허용) 
  - [x] 로또 번호는 오름차순으로 정렬하여 보여준다.
- [x] 당첨 번호 입력 ex)`1,2,3,4,5,6`
  - [x] 입력 값 검증
    - [x] 1~45 사이의 숫자여야 한다.
    - [x] 6개의 숫자여야 한다.
    - [x] 쉼표(,)를 기준으로 구분한다.
    - [x] 중복되지 않는다.
    - [x] 숫자 사이에 빈 문자열(" ")을 입력할 수 있다. 
    - [x] 이외의 값 입력 시 예외 처리 후 다시 입력받는다.
- [x] 보너스 번호 입력 ex)`7`
  - [x] 입력 값 검증
    - [x] 1~45 사이의 숫자여야 한다. 
    - [x] 당첨 번호 6개와 중복되지 않는다.  
    - [x] 이외의 값 입력 시 예외 처리 후 다시 입력받는다.
- [x] 당첨 통계 출력
  - [x] 당첨 내역 출력 (일치한 번호의 갯수, 금액, 당첨 로또 갯수) ex) `3개 일치 (5,000원) - 1개` 
  - [x] 총 수익률 출력 ex) `총 수익률은 62.5%입니다.`
    - [x] 소수점 둘째 자리에서 반올림한다. 
- [x] 예외 출력 ex) `[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.`
  - [x] IllegalArgumentException을 발생시킨다. (Exception이 아닌 IllegalArgumentException, IllegalStateException 등과 같은 명확한 유형을 처리한다.)
  - [x] [ERROR] 로 시작하는 에러 메시지를 출력한 후, 그 부분부터 다시 입력받는다.    
</br>

## 2. 프로그래밍 요구 사항
- indent depth는 3이 넘지 않아야 한다. 
- 3항 연산자를 쓰지 않는다.
- 함수가 한 가지 일만 하도록 최대한 작게 만들어야 한다.
  - 함수의 길이가 15라인을 넘어가면 안 된다.
- else 예약어를 쓰지 않는다.
  -  if 조건절에서 값을 return 하는 방식으로 구현한다.
- Java Enum을 적용한다. 
- 도메인 로직에 단위 테스트를 구현한다.
  - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리한다.
- 작은 기능 단위의 테스트코드를 작성한다. 문제를 작게 나누고, 그 중 핵심 기능에 가까운 부분부터 작게 테스트를 만들어 빠르게 피드백 한다. 
- `camp.nextstep.edu.missionutils.Randoms`의 `pickUniqueNumbersInRange`와 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 사용한다.
- 제공된 Lotto 클래스를 사용한다.
  - Lotto에 필드를 추가할 수 없다.
  - Lotto의 패키지 변경은 가능하다.
  - numbers의 접근 제어자인 private을 변경할 수 없다.   
</br>

## 3. 패키지 구조
```bash
com.woowacourse.java-lotto-6
┃ constants
┃ ┣ ErrorMessage.java
┃ ┗ SystemMessage.java
┃
┣ controller
┃ ┗ LottoGameController.java
┃
┣ domain
┃ ┣ BonusNumber.java
┃ ┣ Lotto.java
┃ ┣ LottoPrize.java
┃ ┣ Lottos.java
┃ ┣ PurchaseAmount.java
┃ ┗ TotalWinningResult.java
┃
┣ view
┃ ┣ InputView.java
┃ ┗ OutputView.java
```
</br>


## 4. Git 커밋 컨벤션
```
type: subject
```

### Commit Type
* feat: 새로운 기능 추가 
* fix: 버그 수정 
* docs: 문서 수정 
* refactor: 코드 리팩토링
* test: 테스트 코드, 리팩토링 테스트 코드 추가 
* style: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우

### Subject
* 50자를 넘기지 않고, 마침표를 붙이지 않습니다. 
* 과거 시제를 사용하지 않고 명령어로 작성합니다.   
</br>

## 5. Java 코드 컨벤션
https://github.com/woowacourse/woowacourse-docs/tree/main/styleguide/java#java-style-guide