package kr.or.bit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class SeatAdmin {
	// Singleton
	// Instance
		private static SeatAdmin instance;
		private ArrayList<UseSeat> useSeatList; //*******************	
		private Seats seat;
		private Calendar cal;
		private Member member;
		private Scanner sc;


		public static SeatAdmin getInstance() {
			if (instance == null) {
				instance = new SeatAdmin();
			}
			
			return instance;
		}
		
		// private construct
		private SeatAdmin() {
			useSeatList = new ArrayList<UseSeat>(); //*******************
			this.cal = Calendar.getInstance();
			this.seat = new Seats();
			sc = new Scanner(System.in);
		}
		

		public void login(Member member) {
			
			this.member = member;
			
		}
		
		public Calendar getCal() {
			return cal;
		}

		public void setCal(Calendar cal) {
			this.cal = cal;
		}

		
		public ArrayList<UseSeat> getUseSeatList() {
			return useSeatList;
		}

		public void setUserSeatList(ArrayList<UseSeat> useSeatList) {
			this.useSeatList = useSeatList;
		}
		
		public void showSeat() { // 좌석조회

			for (int i = 0; i < seat.getSeatArr().length; i++) {
				for (int j = 0; j < seat.getSeatArr()[i].length; j++) {
					System.out.printf("%s",
							seat.getSeatArr()[i][j].equals("___") ? "[" + (i + 1) + "-" + (j + 1) + "]" : "[사용중]");
				} // 빈좌석이면 [1-1],[1-2],[1-3] 이런식으로 출력, 그게 아니면 [사용중] 이라고 출력
				System.out.println();
			}
		}

		public void selectSeat() { // 좌석선택

			// showSeat();//좌석조회
			System.out.println("원하는 자리를 선택해주세요. 예) 1-2 ");

			try {
				String inputSeat = sc.nextLine(); // 사용자가 원하는 자리 입력
				String[] inputSeatSplit = inputSeat.split("-"); // split String메서드로 행과열 분리 ( ex: "1-2" , row = 1 , col = 2)
				int row = Integer.parseInt(inputSeatSplit[0]) - 1; // 배열은 0부터 시작하니까 스캐너입력값 -1 해야 배열 행과 열 동일...
				int col = Integer.parseInt(inputSeatSplit[1]) - 1;

				if ((row >= 0 && row <= 5) && (col >= 0 && col <= 6)) {
					if (seat.getSeatArr()[row][col].equals("___")) { // 스캐너 입력받은 행과열이 빈좌석이면
						seat.getSeatArr()[row][col] = "[사용중]"; // "[사용중]"으로 바뀜
						seat.setSeatNo((row + 1) + "-" + (col + 1)); // 좌석번호를 행과열값 처럼 ... 좌석 "1-1" 이면 좌석번호도 "1-1" 처럼
						seat.getSelSeatArr()[row][col] = seat.getSeatNo();

						System.out.println(seat.getSeatNo() + " 자리를 선택 완료됐습니다.");
						System.out.println("좌석번호 : " + seat.getSeatNo());

						// UserSeatList에 member, seatNo 넣어줌
						useSeatList.add(new UseSeat(member, seat.getSeatNo()));


						showSeat();
					} else {
						System.out.println("이미 선택된 자리입니다.");
					}

				} else {
					System.out.println("자리를 잘못 입력했습니다.");
				}

			} catch (Exception e) {
				System.out.println(" 1-1 형식으로 다시 자리를 선택해주세요");
			}

		}

		public void logout() {
			System.out.println("로그아웃 할 자리를 입력하세요.");
			String inputSeat = sc.nextLine();
			String[] inputSeatSplit = inputSeat.split("-"); // split String메서드로 행과열 분리 ( ex: "1-2" , row = 1 , col = 2)
			int row = Integer.parseInt(inputSeatSplit[0]) - 1;
			int col = Integer.parseInt(inputSeatSplit[1]) - 1;

			if ((row >= 0 && row <= 5) && (col >= 0 && col <= 6)) {
				seat.getSeatArr()[row][col] = "___";
				System.out.println("선택된 자리 로그아웃 됐습니다.");
				showSeat();
			}

		}
		public void BuyTime() { // 시간 구매

			boolean payCard;

		//	System.out.println("사용할 좌석을 입력해주세요");

			System.out.println("이용할 시간을 입력하세요. 1시간 당 1000원 입니다.");
			int inputTime = Integer.parseInt(sc.nextLine());
			// String usingTime = "";

			System.out.println("카드를 넣어서 결제해주세요");
			if (payCard = true) {
				System.out.println("결제가 완료 됐습니다.");
				System.out.println(inputTime + "시간이 충전됐습니다.");
				System.out.println(
						(int) (cal.get(Calendar.HOUR) + inputTime) + "시" + cal.get(Calendar.MINUTE) + "분 까지 이용가능합니다.");
			} else {
				System.out.println("카드 결제가 되지않았습니다.");
			}

		}
		


	
}