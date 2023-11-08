기능구현
1. 입력받은 금액을 1000나눈 것의 개수의 모든 list값 출력하기
2. 당첨번호, 보너스 번호 입력하기
3. 당첨 통계 내기 
4. 3, 4, 5, 6개가 일치하는 개수 출력하기 
5. 총 수익률 출력하기

구현 현황
1. 입력받은 금액을 1000나눈 개수를 list값 출력위해 lotto class 만듬
2. 당첨번호와 보너스 번호 입력 구현 및 lotto class에 sort추가
3. 당첨 통계함수로 일치하는 개수랑 수익률 출력
4. 구현을 하였지만 요구사항을 확인 후 수정 예정
5. enum을 활용하기 위해 LottoRank enum class 구현
6. winStatistic메서드길이가 너무 길어서 쪼개기
7. 사용할 winStatistic 메서드를 LottoRank 를 활용해서 여러개의 메서드로 나누기 
8. initializePrizeCount 메서드를 구현하여 enum에 만들어 놓은 등수들의 당첨 횟수를 0으로 초기화
9. calculatePrizes 메서드 구현