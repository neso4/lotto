# 기능 목록

- [x] 입력 에러시 "그 부분" 부터 다시   

`로또 구입 금액 대한 입출력`
- [x] 구입 금액 입력 메세지 출력
- [x] 구입 금액 입력
  - [x] 1000원 단위 검사
  - [x] `구입 금액`을 저장

`구입 금액 기반 계산`
- [x] `로또 갯수` 만큼 loop
  - [x] `로또` 생성
- [x] `로또 목록`에 저장
- [x] `로또 목록`에 저장

`로또 결과에 대한 출력`
- [x] `로또 갯수` 출력
- [x] `로또 목록` 출력

`당첨 번호 입출력`
- [x] 당첨 번호 입력 메세지 출력
- [x] 당첨 번호 입력

`보너스 당첨 번호 입출력`
- [x] 보너스 당첨 번호 입력 메세지 출력
- [x] 보너스 당첨 번호 입력

`당첨 번호 저장`
- [x] `당첨 번호` 저장 
- [x] `보너스 당첨 번호` 저장

`당첨 통계 계산`
- [x] `로또 목록`의 수만큼 loop
  - [x] `당첨 번호`와 비교해 `당첨 결과` 계산
- [x] `당청 결과`의 수만큼 loop
  - [x] `당첨 총 금액` 계산
- [x] `총 수익률` 계산
  - [x] `구입 금액`과 `당첨 총 금액` 계산

`당첨 통계 출력`
- [x] 당첨 통계 메세지 출력
- [x] `당첨 결과` 출력
- [x] `총 수익률` 출력 (소수 둘째 자리에서 반올림)

- [x] 종료

### 사용 예정일 객체

`로또`
- [x] 유효성 검사
`로또 구매`
- `로또 금액` `로또 갯수 = 로또 금액 / 1000`
- [x] 유효성 검사
- 로또 갯수를 객채로 분리 해보았지만 로또 갯수는 항상 로또 금액에 의존적이며 독립적인 테스트시 로또 금액과 같은 로직이 반복됨
- 따라서 항상 로또 갯수와 로또 구매 가격과 일정한 상관관계가 유지되도록 객체내부에서 계산해서 불러오는 방식으로 처리

`로또 목록`
- [x] 유효성 검사 `IllegalStateException`
`당첨 번호` - `보너스 당첨 번호`
- [x] 유효성 검사 `IllegalArgumentException`
`당첨 결과`
- [x] 유효성 검사 `IllegalArgumentException`
`당첨 총 금액`
- [x] 유효성 검사 `IllegalStateException`
`총 수익률`
- [x] 유효성 검사 `IllegalStateException`
---
## 3주차 목표
- 클래스(객체)를 분리하는 연습
- 도메인 로직에 대한 단위 테스트를 작성하는 연습
  - [x] 단위테스트는 2주차에 연습해봤으니까 TDD 도전!
    - 장점
      - 테스트 커버리지 높아짐
      - 나중에 하면 귀찮아서 안하게 되는걸 방지
      - 오버 엔지니어링 방지
  - [x] 더 의식적으로 Test 코드 작성하기

## TODO
- [x] Java Enum을 적용한다.
- [x] `pickUniqueNumbersInRange()` 사용하기
## NOT TODO
- [ ] 메서드 15라인을 넘어가지 않기!
- [ ] else 예약어를 쓰지 않는다.
- [ ] 도메인 로직에 단위 테스트를 구현해야 한다.
- [x] `numbers`의 접근 제어자인 private을 변경할 수 없다.
- [x] `Lotto`에 필드(인스턴스 변수)를 추가할 수 없다.
- [x] `Lotto`의 패키지 변경은 가능하다.
## ISSUE
- [x] 잘못된 입력시 그부분 부터 다시 입력하라는 요구사항에 맞춰 코드를 작성하던중 try catch 의 비슷한 반복이 일어남   
  - 비슷한 함수 반복되는 것 처리방법 찾아보기
  - Caller Callee 관계의 인터페이스와 객체로 알아냄
  - Test 의 assertSimpleTest의 내부 구조를 참고.

- [x] 현재는 Lotto 에서 1~45 까지 숫자의 유효성 검사를 하고 있는데 보너스
- 넘버도 1~45 가 들어와야하고 String type이 들어왔을때 int 타입으로 계속 변경해줘야 하는데 validate 를 하는 것 보다
- 인자로 받을 때를 LottoNumber 와 같은 객체 만들어서 받고 객체 내부에서 유효성 체크하는 것으로 바꿀까 생각중

- [x] 기존 WinningLotto 에서 분기 치면 15줄 이상으로 나오던 것을 Rank enum 에서 처리하도록 하는데
- stream() 으로 표현하려니 초급자인 내게 상당히 어렵고 매칭된 수가 5개일 때 만 나눈는 것은 Rank의 규칙이 바꾸었을 때
- 대처하지 못할 거 같아서 enum 의 활용 사롕들을 보며 공부중
- 참고
- [우아한 기술 블로그 enum 활용 정리](https://techblog.woowahan.com/2527/)

- [ ] Test Code의 List.of 를 보며 어떤 방식으로 돌아가는 궁금해짐 
- 찾아보니 정적 팩토리 메소드라고 함 언뜻 디스코드 커뮤니티에서 봄 
- 더 검색 저번 주차에 피드백을 보며 사용했던 Integer.valueOf 도 정적 펙토리 메소드 였음
- 또한, enum의 valueOf도 정적 펙토리 메소드의 한 종류라고함
- 정적 팩토리 메소드 장점들을 보며 수긍함
- 정적 팩토리 메소드 가 대부분의 경우 좋다는 걸 깨달음
- 내 코드는 public 생성자로 구현되었는데 정적 팩토리 메소드로 바꿔서 직접 경험해보고 싶음.

- 참고
- [정적 펙토리 메소드는 왜 사용할까?](https://tecoble.techcourse.co.kr/post/2020-05-26-static-factory-method/)
- [정적 펙토리 메서드를 생성자 대신 사용하자](https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9C-%EC%83%9D%EC%84%B1%EC%9E%90-%EB%8C%80%EC%8B%A0-%EC%82%AC%EC%9A%A9%ED%95%98%EC%9E%90)

## 그 외 공부할 것
- [x] `IllegalArgumentException`, `IllegalStateException`의 차이점
- IllegalArgumentException
  - null이 아닌 인자의 값이 잘못되었을 때
- IllegalStateException
  - 객체 상태가 메서드 호출을 처리하기에 적절치 않을 때

- [IllegalArgumentException vs IllegalStateException](https://velog.io/@injoon2019/IllegalArgumentException-vs-IllegalStateException) 참고
- [표준예외를 사용하라](https://blog.voidmainvoid.net/7)
객체 상태가 메서드 호출을 처리하기에 적절치 않을 때
---
## 추가된 주의 사항

```java
List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);`
```
