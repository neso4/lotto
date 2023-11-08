package model;

import service.Lottoenum;

import java.util.ArrayList;
import java.util.List;

public class User {


    private int price = 0;
    private int money = 0;
    private List<Lotto> lottoList = new ArrayList<>();
    private int fifth = 0;
    private int fourth = 0;
    private int third = 0;
    private int second = 0;
    private int first = 0;

    public User(int price, List<Lotto> lottos) {
        this.price = price;
        this.lottoList = lottos;
    }

    public void checkRanking(String ranking){
        if(ranking.equals("fifth")){
            this.fifth++;
        }
        if(ranking.equals("fourth")){
            this.fourth++;
        }
        if(ranking.equals("third")){
            this.third++;
        }
        if(ranking.equals("second")){
            this.second++;
        }
        if(ranking.equals("first")){
            this.first++;
        }
    }
    public void giveMoney(){
        this.money = this.fifth * Lottoenum.FIFTH.getMoney()+
                this.fourth * Lottoenum.FOURTH.getMoney()+
                this.third * Lottoenum.THIRD.getMoney()+
                this.second * Lottoenum.SECOND.getMoney()+
                this.first * Lottoenum.FIRST.getMoney();
    }

    public int getPrice() {
        return price;
    }
    public List<Lotto> getLottoList() {
        return lottoList;
    }

    public int getFifth() {
        return fifth;
    }

    public int getFourth() {
        return fourth;
    }

    public int getThird() {
        return third;
    }

    public int getSecond() {
        return second;
    }

    public int getFirst() {
        return first;
    }

    public int getMoney() {
        return money;
    }
}
