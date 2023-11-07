# 🔍미션-로또 홍성문 

## 🚀프로그래밍 전 기능목록 작성
1. 로또 구입 금액을 입력 받고 1000으로 나눠서 구매 갯수를 구한다.
2. 로또 구매 갯수 만큼 함수를 통해 로또 번호를 생성해 list에 저장하고 출력한다.
3. 당첨 번호와 보너스 번호를 입력받는다.
4. 구매한 로또 list를 for문을 반복하면서 당첨 여부를 확인하는 함수를 만든다. 
    - 로또 갯수만큼 for문을 돌면서 현재 index의 로또의 당첨 여부를 확인하는 함수를 호출한다.
    - 당첨 여부를 확인하는 함수의 return은 일치하는 숫자의 갯수로 하자.
    - 리턴받은 일치하는 숫자 갯수를 최종 당첨 갯수 리스트를 업데이트 하는데 사용한다.
5. 당첨 내역을 출력하는 함수를 구현한다.
6. 수익률을 구하고 출력한다.
7. Exception 부분을 구현 한다. Exception 종류는 아래와 같다.
    - 로또 구입금액 입력오류. 1000으로 나누어 떨어지지 않는 입력.
    - 당첨번호 입력오류
      - 1~45 사이의 숫자가 아니다.  
      - 문자나 공백이 입력 되었다. 
   
## 📮클래스와 Method📮
- Base_Func 이름의 클래스를 만든다. Base_Func 클래스에는 Application.java에서 사용할 기본 함수 들의 모임이다.
  1. Input_Purchase_Price() -> 로또 구입 금액을 입력 받아 정수로 변환후 리턴.
  2. Purchase_Lotto_Number(int price) -> 로또 구입 금액이 1000으로 나눠 떨어지는지 확인하고 구매할 로또 갯수 리턴.
  3. Print_Purchased_Lotto_list(ArrayList<Lotto> Buy_lotto) -> 구매한 모든 로또의 번호 6개를 출력

- Application.java 클래스
  1. main()
  2. Make_Buy_Lotto_List(int purchase_num) -> 기능 2번 구현

- Lotto.java 클래스
  1. Get_numbers()