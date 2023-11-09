## 🚕 2주차 미션 공통 피드백
- [x] README.md 를 상세히 작성한다.
- [x] 기능 목록을 재검토한다.
- [x] 기능 목록을 업데이트한다.
- [x] 값을 하드 코딩하지 않는다.
- [x] 구현 순서도 코딩 컨벤션이다.
- [x] 변수 이름에 자료형은 사용하지 않는다.
- [x] 한 함수가 한 가지 기능만 담당하게 한다.
- [x] 함수가 한 가지 기능을 하는지 확인하는 기준을 세운다.
  - 기준
    - 더이상 세분화 되지 않을 만큼 쪼갠다.
    - 어떤 기준으로 쪼개는가? 
      - 일단 가장 작아질 수 있을 정도로 쪼갠다
      - 가장 작은 단위로 쪼갰을 때가 쪼개기 전단계보다 비효율적이라면 쪼개기 전으로 작성한다.
- [x] 처음부터 큰 단위의 테스트를 만들지 않는다.

## 🚨 과제 제출 전 체크 리스트 [ 0점 방지 ]
- [x] Java Version 17 확인
- [x] ```./gradlew clean test``` 명령 실행하고, 결과가 ```BUILD SUCCESSFUL in 0s``` 인지 확인

## 🎯 프로그래밍 요구 사항
- [x] 프로그램 실행희 시작점은 Application의 main() 이다.
- [x] build.gradle 파일을 변경할 수 없고, 외부 라이브러리를 사용하지 않는다.
- [x] Java Code Cenvention 가이드를 준수하며 프로그래밍한다.
- [x] 프로그램 종료 시 System.exit()를 호출하지 않는다.
- [x] 프로그램 구현이 완료되면 ApplicationTest 의 모든 테스트가 성공해야 한다. 테스트가 실패할 경우 0점 처리한다.
- [x] 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 이름을 수정하거나 이동하지 않는다. ( Lotto Package 이동 가능 )
- [x] indent depth는 2까지만 허용한다.
- [x] 3항 연산자를 쓰지 않는다.
- [x] 함수가 한 가지 일만 하도록 최대한 작게 만들어라.
- [ ] JUnit5와 AssertJ 를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.

### 🔈 추가된 요구 사항
- [x] 함수의 길이가 15라인을 넘어가지 않도록 구현한다.
- [x] else 예약어를 쓰지 않는다.
  - Hint: if 조건절에서 값을 return 하는 방식으로 구현한다.
- [x] switch/case 허용하지 않는다.
- [x] Java Enum을 적용한다.
- [x] 도메인 로직에 단위 테스트를 구현해야 한다. 
  - UI(System.out, System.in, Scanner) 로직은 제외한다.
- [x] 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.

## ⚙️ 라이브러리
- [x] camp.nextstep.edu.missionutils에서 제공하는 Randoms 및 Console API를 사용하여 구현해야 한다.
- [x] Random 값 추출은 camp.nextstep.edu.missionutils.Randoms의 pickUniqueNumbersInRange()를 활용한다.
- [x] 사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다

## 🎱 Lotto 클래스
- [x] 제공된 Lotto 클래스를 활용해 구현해야 한다.
- [x] numbers의 접근 제어자인 private을 변경할 수 없다.
- [x] Lotto에 필드(인스턴스 변수)를 추가할 수 없다.
- [x] Lotto의 패키지 변경은 가능하다.

## ⌨️ 입출력 요구 사항
### 입력
- [x] 로또 구입 금액을 입력 받는다. 1,000원 단위로 입력 받으며 1,000원으로 나누어 떨어지지 않는 경우 예외 처리한다.
- [x] 당첨 번호을 쉼표 기준으로 구분하여 입력 받는다.
- [x] 보너스 번호를 입력 받는다.
### 출력
- [ ] 발행한 로또 수량 및 번호를 출력한다. 오름차순 정렬하여 보여준다.
- [x] 당첨 내역을 출력한다.
- [x] 수익률은 소수점 둘째 자리에서 반올림한다.
- [x] 예외 사항 시 에러 문구를 출력해야 한다. 에러 문구는 "[ERROR]" 로 시작해야 한다.

## 🚀 기능 요구 사항
- [x] 로또 구입 금액 입력 받기 (pay)
- [x] pay % 1000 이 0인지 아닌지 판별하기 (아니면 ERROR)
- [x] pay / 1000 만큼 로또 발행하기
- [x] 당첨 번호 입력 받기 ( , 로 구분 )
- [x] 보너스 번호 입력 받기
- [x] 발행한 로또들 중 당첨된 것이 있는지 확인하기
- [x] 당첨 통계 출력하기
- [x] 수익률 계산하기
- [x] 수익률 출력하기

---

## 🙌 지난 2주차 미션 PR 리뷰
- [x] 한 번만 사용하는 변수들이니 따로 정의하지 않고 바로 매개변수로 넣기
```java
InputView userInputView = new InputView();
OutputView userOutputView = new OutputView();
RacingCarGameModel racingCarGameModel = new RacingCarGameModel();


RacingCarGameController game = new RacingCarGameController(
        userInputView,
        userOutputView,
        racingCarGameModel
);

⬇️

RacingCarGameController game = new RacingCarGameController(
        new InputView(),
        new OutputView(),
        new RacingCarGameModel()
);
```

- [x] 변수명에 변수 타입 넣지 않기 
- [x] 하나의 함수가 여러 기능을 하지 않게 더 잘게 쪼개기
- [x] 1주차 공통 피드백 '축약하지 않는다' 더 신경쓰기
- [x] 나중에 변경되지 않는 값은 final 로 선언하기
- [x] Java에서 제공하는 API 적극 활용하기
  - 코드 작성 전 Java API에서 제공하는지 확인하기