package kr.or.bit;

public class Seats {

	private String[][] seatArr; // 좌석
	private String[][] selSeatArr; // 구매된 좌석
	private String seatNo; // 좌석번호

	public String[][] getSeatArr() {
		return seatArr;
	}

	public void setSeatArr(String[][] seatArr) {
		this.seatArr = seatArr;
	}

	public String[][] getSelSeatArr() {
		return selSeatArr;
	}

	public void setSelSeatArr(String[][] selSeatArr) {
		this.selSeatArr = selSeatArr;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	{ // 생성자

		this.seatArr = new String[5][6];
		this.selSeatArr = new String[5][6];

		for (int i = 0; i < seatArr.length; i++) {
			for (int j = 0; j < seatArr[i].length; j++) {
				seatArr[i][j] = "___"; // 모든좌석 "___" 로 초기화
			}
		}

	}

}