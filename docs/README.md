## 게임 진행 과정
1. 로또 구입 금액 입력
2. 당첨 번호 입력
3. 보너스 번호 입력
4. 로또 발행
5. 발행된 로또 수량 및 번호 출력
6. 당첨 내역을 출력
7. 수익률 출력

## 금액 입력 안내 출력 기능
- [x] `LottoOutputView::printBuyingPriceInputRequest` 메서드
  - 금액입력 안내 메시지를 출력한다.

## 로또 구입 금액 입력 기능
- [x] `LottoInputView::getBuyingPrice` 메서드
  - 로또 구입 금액을 입력받는다.
- [ ] `validateBuyingPrice` 메서드
  - 입력받은 로또 금액을 검증한다.
    - 정수가 아니라면 `IllegalArgumentException` 발생
    - 0보다 작다면 `IllegalArgumentException` 발생
    - 1000의 배수가 아니라면 `IllegalArgumentException` 발생
  - [ ] `isInteger` 메서드
    - 문자열을 인수로 받아 정수인지 검사한다.

## 당첨 번호 입력 기능
- [ ] `LottoInputView::getLottoWinningNumbers` 메서드
  - 로또 당첨 번호를 입력받는다.
- [ ] `validateLottoNumbers` 메서드
  - 입력받은 로또 번호를 검증한다.
    - `,` 를 기준으로 입력값이 6개가 아니라면 `IllegalArgumentException` 발생
    - 각각의 값이 정수가 아니라면 `IllegalArgumentException` 발생
    - 각각의 값의 범위가 1~45가 아니라면 `IllegalArgumentException` 발생
    - 중복된 번호가 있다면 `IllegalArgumentException` 발생
  - [ ] `isInteger` 메서드
    - 문자열 리스트를 인수로 받아 정수인지 검사한다.
  - [ ] `isLottoNumbers` 메서드
    - 정수 리스트를 인수로 받아 1~45 값인지 검사한다.
    - [ ] `isLottoNumber` 메서드
      - 정수를 인수로 받아 1~45 값인지 검사한다.
  - [ ] `isDuplicated` 메서드
    - 정수 리스트를 인수로 받아 중복된 값이 있는지 검사한다.

## 보너스 번호 입력 기능
- [ ] `LottoInputView::getBonusNumber`
  - 보너스 번호를 입력받는다.
- [ ] `validateBonusNumber` 메서드
  - 입력받은 보너스 번호를 검증한다.
    - 정수가 아니라면 `IllegalArgumentException` 발생
    - 1~45 값이 아니라면 `IllegalArgumentException` 발생

## 로또 발행 기능
- [ ] `generateLottoNumbers` 메서드
  - 랜덤한 로또 번호를 생성한다.

## 로또 발행 결과 출력 기능
- [ ] `LottoOutputView::printBoughtResult` 메서드
  - 발행된 로또 번호들을 인수로 받아 출력한다.

## 당첨 확인 기능
- [ ] `getMatchingNumberCount` 메서드
  - 정답과 일치하는 번호의 개수를 반환한다.

## 당첨 내역 출력 기능
- [ ] `LottoOutputView::printLottoResult` 메서드
  - 당첨 내역 정보를 인수로 받아 출력한다.

## 수익률 출력 기능
- [ ] `LottoOutputView::printProfit` 메서드
  - 구입 금액, 당첨 금액을 인수로 받아 수익률을 출력한다.
