# 3주차 프리코스 과제 로또


## LottoNumber
- [x] 로또 발행에 사용될 숫자들을 캐싱하여 보유한다.
- [x] 숫자 혹은 숫자들을 받아 LottoNumebr반환
- [x] 유효범위가 아닐 경우 예외 발생(e)
- [x] 오름차순 정렬기능

## Lotto
- [x]개수와 중복 유효성 검사 기능 (e)
- [x] 일치하는 개수를 카운팅 하는 기능
- [x] 해당 숫자 포함여부 판별 기능

## PurchaseAmount
- [x]구입 금액의 유효성 검사(e)
- [x] 구매할 로또 개수를 구하는 기능

## RandomNumbersGenerator
- [x] 로또 발행에 사용할 랜덤 숫자 발생
- [x] 숫자가 유효하지 않을 경우 다시 생성

## LottoTicket
- [x] 발행된 로또들을  보유한 클래스
- [x] 로또 개수 유효성 검사(e)

## LottoTicketFactory
- [x] 입력 개수만큼 로또를 발행하고 LottoTicket을 반환
- [x] 구매 개수 유효성 검사(e)
- 
## Parser
- [x]입력을 형식에 맞게 파싱하는 기능

## WinningLotto
- [x] 당첨번호 및 보너스 넘버 유효성 검사
- [x] 로또를 매개값으로 랭킹을 판별하는 기능

## Ranking
- [x] 맞은 개수,맞은개수, BiPredicate, 상금을 필드로 가지는 enum 클래스
- BiPredicate : 맞은개수, 보너스 보유 여부를 가지고 조건 판단
- [x] 맞은 개수와 보너스 넘버로 Ranking을 반환하는 기능

## WinningResult
- [x] 당첨 정보 저장하는 기능
- [x] 전체 수익률을 계산하는 기능

## Controller
- [x] 구매 금액 입력 및 구매 로또 정보 출력
- [x] 당첨 로또 및 보너스 번호 입력
- [x] 당첨 결과 출력
- [x] 에러 발생 시 해당 부분부터 입력 요구

## InputView
- 입력기능

## OutputView
- 출력기능

## Dto
데이터 전송을 위한 Dto클래스 생성


