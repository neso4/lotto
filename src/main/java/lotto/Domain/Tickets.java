package lotto.Domain;

import java.util.*;

public class Tickets{
	
	private List<Ticket> tickets = new ArrayList<>();
	private List<Integer> Win_count = new ArrayList<>();
	
	public Integer Purchase_Number;
	//여기에
	public Tickets(Integer Purchase_Number) {
		this.Purchase_Number = Purchase_Number;
		//this.Purchase_Number=Purchase_Number;
		//for (int i = 0 ; i<Purchase_Number; i++) {
		//	Ticket ticket = new Ticket();
		//	tickets.add(ticket);
		//	System.out.println(tickets.get(i).getNumber().toString());
		//}
			
//		}
	}
	public void Win_Tickets(List<Integer> Lotto_Number, Integer Bonus_Number) {
		for(int i =0 ; i < Purchase_Number; i++) {
			tickets.get(i).winner(Lotto_Number, Bonus_Number);
			Win_count.add(tickets.get(i).getWin());
		}
	}
	public void add(Ticket ticket) {
		this.tickets.add(ticket);
	}
	
	public Ticket Ticket_get(Integer index) {
		return this.tickets.get(index);
	}
	
	public List<Integer> get_Wins(){
		return Win_count;
	}
	public Integer get_Purchase_Number(){
		return Purchase_Number;
	}
}
