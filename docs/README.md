# **프로그램 흐름**

- [x]  프로그램 시작
- [x]  구입금액 입력
    - [x]  입력값이 숫자가 맞는지 예외처리
    - [x]  입력값이 0이 아닌지 예외처리
    - [x]  1000으로 나눴을 때 나머지가 없는지 예외처리
- [x]  구입개수 출력
- [x]  구입 개수 만큼 로또번호 랜덤출력
    - [x]  오름차순 정리
- [x]  ,기준으로 6자리 당첨 번호입력
    - [x]  숫자가 아닌 다른 문자가 없는지
    - [x]  1~45사이의 수인지
    - [x]  중복된 숫자가 없는지
- [x]  보너스 번호 입력
    - [x]  숫자가 아닌 다른 문자가 없는지
    - [x]  위에 6자리와 중복되지 않은 수인지
    - [x]  1~45사이의 수인지
- [x]  각 복권이 당첨과 맞는지 확인
- [x]  수익률 계산
- [x]  통계 출력

# **기능 명세서**

## 메세지 **(Message)**

- [x]  예외처리 메세지 (`**ExceptionMessage**`)
- [x]  정상 실행 메세지 (`**RunMessage**`)

## **뷰 (View)**

- [x]  InputView
    - [x]  금액 입력 (**`inputAmount`**)
        - [x]  입력값 예외처리 후 다시입력
    - [x]  당첨번호 입력 (**`inputPrizeNumber`**)
        - [x]  입력값 예외처리 후 다시입력
    - [x]  보너스 번호 입력 (**`inputBonusNumber`**)
        - [x]  입력값 예외처리 후 다시입력
- [x]  OutputView
    - [x]  금액입력 안내 메세지(`**printAmountInputMessage**`)
    - [x]  로또 구매 안내 메세지(`**printBuyLottoMessage`)**
    - [x]  당첨 번호 입력 안내 메세지(`**printWinningNumberInputMessage**`)
    - [x]  보너스 번호 입력 안내 메세지(`**printBonusNumberInputMessage**`)
    - [x]  당첨 통계 출력 메세지(`**printWinningStatisticsMessage**`)

## **도메인 (Domain)**

- [x]  금액 (**`Amount`**)
    - 정규식
    - 최소금액
    - 입력 금액
    - [x]  금액 예외처리
        - [x]  입력값이 0이 아닌지 예외처리
        - [x]  1000으로 나눴을 때 나머지가 없는지 예외처리
    - [x]  로또 개수 반환
    - [x]  입력값 숫자로 변환
- [x]  복권 (**`Lotto`**) 
    - 번호 리스트
    - [x]  6자리 숫자 생성
    - [x]  로또번호 예외처리
        - [x]  6개의 숫자가 맞는지
    - [x]  금액에 맞게 로또 번호생성
- [x]  당첨 번호 (**`WinningNumber`)**
    - 정규식
    - 당첨번호
    - [x]  당첨번호 예외처리
        - [x]  중복된 번호가 없는지
        - [x]  숫자와 ,만 있는 문자열이 들어왔는지
        - [x]  6개의 숫자가 맞는지
        - [x]  1~45사이의 수인지
    - [x] string을 List<Integer>로 변환
- [x]  보너스 번호 (**`BonusNumber`)**
   - 정규식
   - 보너스 번호
   - [x]  보너스 번호 예외처리
      - [x]  당첨 번호와 중복된 번호가 없는지
      - [x]  숫자만 있는 문자가 들어왓는지
      - [x]  1~45사이의 수인지
- [x]  순위 (**`Ranking`)**
    - 순위(enum사용)
- [x]  당첨 통계(**`WinningStatistics`)**
    - 당첨결과(해쉬맵) 
    - [x]  당첨된 복권에 맞게 순위 개수 증가
    - [x]  총 금액 계산
    - [x]  수익률 계산


## **컨트롤러 (Controller)**

- [x]  LottoController
    - [x]  로또 구매 (**`startLotto`**)
    - [x]  로또 번호 생성 (**`lottoAutomaticCompletion`**)
    - [x]  로또 추첨 (**`lottoDraw`**)
    - [x]  추첨 확인 (**`checkDraw`**)
    - [x]  결과 출력(**`**outputResult**`**)

## **서비스 (Service)**
- [x]  로또번호 비교 순위반환 (**`LottoComparison`**)
   - [x]  순위반환
   - [x]  당첨번호 비교
   - [x]  보너스 번호 비교