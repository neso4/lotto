package lotto;

public class Amount {
    private final int money;
    public Amount(String money) {
        validate(money);
        this.money = Integer.parseInt(money);
    }
    public void validate(String money){
        if(!money.matches("[0-9]+"))
            throw new IllegalArgumentException();

        if(Integer.parseInt(money) < 1000 || Integer.parseInt(money) % 1000 != 0)
            throw new IllegalArgumentException();
    }

    public int getMoney(){
        return this.money;
    }
}
