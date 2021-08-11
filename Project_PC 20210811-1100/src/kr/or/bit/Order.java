package kr.or.bit;

public class Order {
	private int orderNo;
	private Food food;
	private Member member;
	private String seat;

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "[주문번호=" + orderNo + ", 음식정보=" + food + ", 회원ID=" + member.mId + ", 좌석정보=" + seat + "]";
	}
}