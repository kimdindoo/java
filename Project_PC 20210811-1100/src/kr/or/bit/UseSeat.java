package kr.or.bit;


public class UseSeat {
	
	private Member member;
	private String seatNo;
	
	public UseSeat(Member member, String seatNo) {
		this.member = member;
		this.seatNo = seatNo;

	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	


}
