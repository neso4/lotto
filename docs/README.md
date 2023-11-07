## 구현
1. 기능 요구사항
    1. 입력
        - 정상
            - Console로 입력 값 받기 
        - 예외
            - 입력 받은 값이 1,000 단위로 나누어 떨어지지 않을 경우 
            - 숫자가 아닌 경우 예외 처리 
    
    2. 발행한 로또 수량 및 번호 출력 
        - 정상
            -  리스트로 값 받기 : Randoms.pickUniqueNumbersInRange(1, 45, 6)
            - 받은 리스트 값 오름차순 정렬
            - 출력 
          
        - 예외
            - 6개 이상일 경우 
            - 중복되는 숫자 발생
            - 1부터 45 이내의 숫자가 아닐 경우 
    
    3. 당첨 번호 입력
        - 정상
            - String str = Console로 입력 값 받기
            - String[] input = 쉼표(,) 기준 구분
        - 예외
            - 숫자가 아닐 경우
            - 입력 갯수가 6개가 아닐 경우
            - 중복되는 숫자가 존재할 경우 
          
   4. 보너스 번호 입력
        - 정상
            - Console로 입력 값 받기 
        - 예외
            - 당첨 번호와 중복일 경우
            - 숫자가 아닐 경우

   5. 출력 
        - 수익도 같이 출력 
   6. 예외 상황
        1. 사용자가 잘못된 값 입력시 IllegalArgumentException 발생시키고, [ERROR]로 시작하는 에러 메시지 출력후 다시 입력 받기
        2. Exception 이 아닌 IllegalArgumentException, IllegalStateException등 명확한 유형 처리 
2. 프로그래밍 요구 사항
    1. Lotto 클래스 활용 
3. 과제 진행 요구 사항 