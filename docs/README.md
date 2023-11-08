# &nbsp;&nbsp;[미션 - 로또]


---

## &nbsp;&nbsp;구조

- Model (resultMessage)
- View (money, number, lucky, output)
- Controller (board)
- DTO(Dispatcher, ViewResolver)

---


## &nbsp;VIEW


- 사용자와 직접적으로 상호작용을 하는 계층
- 콘솔로부터 사용자의 입력을 받는 InputView
- 콘솔에 사용자에게 메시지를 출력하는 OutputView
- 입력 값의 길이와 형식 같은 처리는 Controller

### &nbsp;&nbsp;기능 명세서

- [x] : 구매 금액을 입력 받는다. 
- [x] : 구매 금액에 비례한 발행 수를 출력
- [x] : 발행된 모든 로또 용지를 오름차순으로 출력
- [x] : 잘못된 입력은 `IllegalArgumentException` 발생 시킨 뒤 [ERROR] 로 시작하는 메시지를 출력 후 해당 부분부터 다시 받는다.
- [x] : 로또 번호와 보너스를 입력 받는다.
- [x] : 당첨 통계를 출력한다.
- [x] : 총 수익률을 출력한다.

### &nbsp;&nbsp;유효성 검사

- [x] : 금액이 숫자가 아닌 값이 포함된 경우
- [x] : 금액이 1000원에 나누어 떨어지지 않는 경우
- [x] : 잘못된 입력에 대한 예외 처리 및 메시지 출력
- [x] : 잘못된 입력에 대한 메시지 출력 이후 재입력
---
## &nbsp;MODEL

### &nbsp;&nbsp;Lotto

- 사용자 입력을 통해 로또 용지를 받는다.
- 여섯 자리가 중복되지 않는지 유효검사를 추가 구현.

### &nbsp;&nbsp;주의 사항 체크리스트

- [x] : 제공된 Lotto 클래스를 활용해 구현해야 한다.
- [x] : numbers의 접근 제어자인 private을 변경할 수 없다.
- [x] : Lotto에 필드(인스턴스 변수)를 추가할 수 없다.
- [x] : Lotto의 패키지 변경은 가능하다.

### &nbsp;&nbsp;기능 명세서

- 로또 클래스로 사용자가 입력한 로또를 반환한다.

## &nbsp;LottoTable

### &nbsp;&nbsp;기능 명세서

- 뷰 객체로부터 금액에 비례하는 발행 수를 매개로 받는다.
- 1-45의 무작위 값을 여섯 자리 생성한 뒤에 정렬을 한다.
- 위 조건에 충족하는 한 행의 로또 용지를 발행 수 만큼 추가한다.

## &nbsp;ResultMessage

### &nbsp;&nbsp;기능 명세서

- 통계 값을 가지고 해당하는 이넘 값과 함께 출력할 메시지를 만든다.
- 위 조건에 충족하는 한 행의 로또 용지를 발행 수 만큼 추가한다.

---

## &nbsp;LottoDispatcher

### &nbsp;&nbsp;기능 명세서

- 보드에 주입할 서비스들을 관리하는 클래스.
- DTO 역할을 한다.

## &nbsp;LottoViewResolver

### &nbsp;&nbsp;기능 명세서

- 보드에 주입하기 위해 필요한 정보를 가지고 있는 뷰들을 관리.
- 메인에서 어플리케이션이 실행될 때 사용자와 상호 작용을 도운다.

## &nbsp;Price

### &nbsp;&nbsp;기능 명세서

- 등수 별 상금을 enum화.

## &nbsp;Result

### &nbsp;&nbsp;기능 명세서

- 등수 별 안내 메시지를 enum화. 


