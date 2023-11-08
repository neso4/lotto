## 🏄‍ 구현 기능 목록

### ✅로또 구입 금액 입력받기
- 금액 입력 메시지 출력하기 (“구입금액을 입력해 주세요.”)
- 구입금액 입력받기 - 예외 발생시 재입력
  - (예외) 숫자를 입력하지 않은 경우
  - (예외) 1000 단위의 숫자가 아닌 경우
- 로또 구매 개수 출력하기 ("8개를 구매했습니다.")

### ✅로또 번호 생성하기
- 로또 개수만큼 6개의 랜덤번호를 리스트에 넣기
  - (예외) 중복된 숫자가 있을 경우
  - (예외) 번호가 6개가 아닐 경우
  - (예외) 숫자 범위가 1~45 가 아닐 경우
- 리스트에 있는 로또 번호 출력하기

### ✅당첨 번호와 보너스 번호 입력받기
- 당첨 번호 입력 메시지 출력하기 ("당첨 번호를 입력해 주세요.")
- 당첨 번호 입력받기  - 예외 발생시 재입력
  - (예외) 숫자가 아닐경우
  - (예외) 쉼표 외의 특수문자가 존재할 경우
  - (예외) 당첨번호가 6개가 아닐 경우
  - (예외) 중복된 숫자가 있을 경우
  - (예외) 1 ~ 45 사이의 숫자 아닐 경우
- 보너스 번호 입력 메시지 출력하기 ("보너스 번호를 입력해 주세요.")
- 보너스 번호 입력받기  - 예외 발생시 재입력
  - (예외) 숫자가 아닐경우
  - (예외) 당첨 번호와 중첩되는 숫자일 경우
  - (예외) 보너스 번호가 두 개 이상일 경우
  - (예외) 1 ~ 45 사이의 숫자 아닐 경우

### ✅당첨 결과 출력하기
- 결과 메시지 출력하기 (“당첨 통계” / "___" )
- 숫자 일치 여부에 따라 당첨 개수 출력하기
- 총 수익률 출력하기

### ✅당첨 여부 계산하기
- 로또 리스트 안에 당첨 번호가 포함되어 있는지 확인하기
- 번호 포함 개수에 따라 당첨 개수 카운트하기
- 총 수익 구하기
- 총 수익률 구하기
 

