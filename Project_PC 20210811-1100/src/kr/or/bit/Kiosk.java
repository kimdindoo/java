package kr.or.bit;

import java.util.Scanner;

public class Kiosk {
	private MemberAdmin memberAdmin;
	private SeatAdmin seatAdmin;
	private CustomMenu customMenu;
	private AdminMenu adminMenu;
	private Scanner sc;

	public Kiosk() {
		this.memberAdmin = MemberAdmin.getInstance();
		this.seatAdmin = SeatAdmin.getInstance();
		this.customMenu = new CustomMenu();
		this.adminMenu = new AdminMenu();
		sc = new Scanner(System.in);
	}

	// --------------------------------------------------------------------------------------------
	// 메인 메뉴
	private String viewMainMenu() { // 메인 화면
		String selMenu0 = null;
		System.out.println(" 일맘일조 PC에 오신 것을 환영합니다. ");
		System.out.println(" 원하는 메뉴를 선택해 주세요. ");
		System.out.println(" ------------------------------------- ");
		System.out.println(" 1. 로그인 / 2. 회원가입 / 3. **관리자 페이지 / 4. **일반 회원 페이지");
		System.out.println(" ------------------------------------- ");
		try {
			selMenu0 = sc.nextLine();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다." + e.getMessage());
		}
		return selMenu0;
	}

	public void mainMenu() { // 메인 메뉴
		mainMenuLoop: while (true) {
			switch (viewMainMenu()) {
			case "1": // 로그인 선택
				System.out.println(" 1. 로그인 ");
				System.out.println(" ------------------------------------- ");

				Member member = memberAdmin.logIn();// 로그인 화면을 불러옵니다. + 로그인한 멤버 리턴값 받아오기
				customMenu.login(member);
				SeatAdmin.getInstance().login(member);
				if (memberAdmin.getLoginAuthor() == "true") {
					AdminMenu();
					break mainMenuLoop;
				} else if (memberAdmin.getLoginAuthor() == "false") {
					CustomMenu();
					break mainMenuLoop;
				} else {
					break;
				}

			case "2": // 회원가입 선택
				System.out.println(" 2. 회원가입 ");
				System.out.println(" ------------------------------------- ");
				memberAdmin.joinIn(); // 회원가입 화면을 불러옵니다.
				break;

			// ---------------------------------------------------------
			case "3": // 테스트용 단축키
				System.out.println(" 관리자 페이지 ");
				System.out.println(" ------------------------------------- ");
				AdminMenu(); // 관리자 페이지로 이동
				break mainMenuLoop;

			case "4": // 테스트용 단축키
				System.out.println(" 일반 회원 페이지 ");
				System.out.println(" ------------------------------------- ");
				CustomMenu(); // 회원 페이지
				break mainMenuLoop;
			// ---------------------------------------------------------

			default:
				System.out.println(" 잘못된 입력입니다. 다시 입력해주세요. ");
				// 기타 잘못된 입력
			}
		}
	}
	// --------------------------------------------------------------------------------------------
	// 회원 메뉴

	private String viewCustomMenu() { // 회원아이디로 로그인 시
		String selMenu1 = null;
		System.out.println("  * * * * 일맘일조 PC * * * * ");
		System.out.println(" 원하는 메뉴를 선택해 주세요. ");
		System.out.println("");
		System.out.println(" 1. PC 이용 / 2. 음식 구매 / 3. 좌석 이용 종료 / 4. 로그아웃");
		System.out.println(" ------------------------------------- ");
		try {
			selMenu1 = sc.nextLine();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다." + e.getMessage());
		}
		return selMenu1;
	}

	private void CustomMenu() { // 회원메뉴
		customMenuLoop: while (true) {
			switch (viewCustomMenu()) {
			case "1": // PC 이용 선택
				System.out.println(" 1. PC 이용");
				System.out.println(" ------------------------------------- ");
				System.out.println(" 좌석 현황을 불러옵니다. ");
				System.out.println("");
				customMenu.showSeat(); // 좌석 조회를 불러옵니다.
				customMenu.selectSeat(); // 좌석 선택을 불러옵니다.
				customMenu.BuyTime();
				break;

			case "2": // 음식 구매 선택
				System.out.println(" 2. 음식 구매");
				System.out.println(" ------------------------------------- ");
				System.out.println(" 이용 가능한 음식 목록입니다. ");
				System.out.println("");
				customMenu.orderFood();
				break;
				
			case "3": // 좌석 이용 종료 선택
				System.out.println(" 좌석 이용을 종료하시겠습니까? ");
				System.out.println(" 1. 예 / 2. 아니오 ");
				int selMenu1 = Integer.parseInt(sc.nextLine());
				if (selMenu1 == 1) {
					customMenu.logout();
					System.out.println(" 좌석 이용이 종료되었습니다. 이용해주셔서 감사합니다.\n"); // 메세지 출력 후 로그인 화면으로 돌아간다.
					System.out.println(" ------------------------------------- \n");
					mainMenu();
					break customMenuLoop;
					
				} else if (selMenu1 == 2) { // 로그아웃 취소시
					System.out.println("");
					break; // 선택 메뉴로 돌아간다.
					
				} else {
					System.out.println(" 잘못된 입력입니다. 다시 입력해주세요. ");
					break;
				}

			case "4": // 로그아웃 선택
				System.out.println(" 로그아웃 하시겠습니까? ");
				System.out.println(" 1. 예 / 2. 아니오 ");
				int selMenu2 = Integer.parseInt(sc.nextLine());
				if (selMenu2 == 1) {
					memberAdmin.setLoginAuthor("fail");

					System.out.println(" 로그아웃 되었습니다.\n"); // 메세지 출력 후 로그인 화면으로 돌아간다.
					System.out.println(" ------------------------------------- \n");
					mainMenu();
					break customMenuLoop;

				} else if (selMenu2 == 2) { // 로그아웃 취소시
					System.out.println("");
					break; // 선택 메뉴로 돌아간다.

				} else {
					System.out.println(" 잘못된 입력입니다. 다시 입력해주세요. ");
					break;
				}

			default:
				System.out.println(" 잘못된 입력입니다. 다시 입력해주세요. ");
				// 기타 잘못된 입력
			} // witch(viewCustomMenu())

		} // loop : while (true)

	} // private void CustomMenu()

	// --------------------------------------------------------------------------------------------
	// 관리자 메뉴

	private String viewAdminMenu() { // 관리자 아이디로 로그인시
		String selMenu2 = null;
		System.out.println(" **[ 관리자 모드 ]** ");
		System.out.println(" 원하는 메뉴를 선택해 주세요. ");
		System.out.println("");
		System.out.println(" 1. 컴퓨터 좌석 조회 / 2. 회원 관리 / 3. 판매 항목 관리 / 4. 판매 내역 조회 / 5. 로그아웃");
		System.out.println(" ------------------------------------- ");
		try {
			selMenu2 = sc.nextLine();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다." + e.getMessage());
		}
		return selMenu2;
	}

	private void AdminMenu() { // 관리자 메뉴
		adminMenuLoop: while (true) {
			switch (viewAdminMenu()) {
			case "1": // [컴퓨터 좌석 조회] 선택
				System.out.println(" 1. 컴퓨터 좌석 조회");
				System.out.println(" ------------------------------------- ");
				System.out.println(" 좌석 현황을 불러옵니다. ");
				System.out.println("");
				customMenu.showSeat(); // 좌석 조회를 불러옵니다.
				break;

			case "2": // [회원 관리] 선택
				System.out.println(" 2. 회원 관리");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				manageMemMenu(); // 회원 관리 함수 불러오기
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				break;

			case "3": // [판매 항목 관리] 선택
				System.out.println(" 3. 판매 항목 관리");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				manageFoodMenu();
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				break;

			case "4": // [판매 내역 조회] 선택
				System.out.println(" 4. 판매 내역 조회");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				adminMenu.checkOrderList(); // 판매 내역 조회 함수 불러오기
				System.out.println(" ------------------------------------- ");
				System.out.println("");

				break;

			case "5": // [로그아웃] 선택
				System.out.println(" 로그아웃 하시겠습니까? ");
				System.out.println(" 1. 예 / 2. 아니오 ");
				int selMenu3 = Integer.parseInt(sc.nextLine());
				logoutLoop: while (true) {
					if (selMenu3 == 1) {
						memberAdmin.setLoginAuthor("fail");
						System.out.println(" 로그아웃 되었습니다.\n"); // 메세지 출력 후 로그인 화면으로 돌아간다.
						System.out.println(" ------------------------------------- ");
						mainMenu();
						break adminMenuLoop;

					} else if (selMenu3 == 2) { // 로그아웃 취소시
						System.out.println(""); // 선택 메뉴로 돌아간다.
						break;

					} else {
						System.out.println(" 잘못된 입력입니다. 다시 입력해주세요. ");
					}
				}

			default:
				System.out.println(" 잘못된 입력입니다. 다시 입력해주세요. ");
				// 기타 잘못된 입력
			} // witch(viewAdminMenu())

		} // loop : while (true)

	} // private void AdminMenu()

	// --------------------------------------------------------------------------------------------
	// 회원 관리 메뉴
	private String viewManageMemMenu() { // 회원 관리 메뉴
		String selMenu4 = null;
		System.out.println(" *** 회원 관리 *** ");
		System.out.println(" 원하는 메뉴를 선택해 주세요. ");
		System.out.println("");
		System.out.println(" 1. 회원 검색 / 2. 회원 리스트 / 3. 회원 삭제 / 4. 관리자 메뉴로 돌아가기");
		System.out.println(" ------------------------------------- ");
		try {
			selMenu4 = sc.nextLine();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다." + e.getMessage());
		}
		return selMenu4;
	}

	private void manageMemMenu() { // 회원 관리 메뉴
		memMenuLoop: while (true) {
			switch (viewManageMemMenu()) {
			case "1": // [회원 검색] 선택
				System.out.println(" 1. 회원 검색");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				adminMenu.searchMember(); // 회원 검색 함수 불러옵니다.
				break;

			case "2": // [회원 등록 삭제] 선택
				System.out.println(" 2. 회원 리스트");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				memberAdmin.mMemberList(); // 회원 등록 삭제 불러오기
				break;

			case "3": // [관리자 메뉴로 돌아가기] 선택
				System.out.println(" 3. 회원 삭제 ");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				adminMenu.removeMember(); // 회원 등록 삭제 불러오기
				break;

			case "4": // [관리자 메뉴로 돌아가기] 선택
				System.out.println(" 4. 관리자 메뉴로 돌아가기 ");
				System.out.println(" 관리자 메뉴로 돌아갑니다. ");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				break memMenuLoop;

			default:
				System.out.println(" 잘못된 입력입니다. 다시 입력해주세요. ");
				// 기타 잘못된 입력
			} // witch(viewAdminMenu())

		} // loop : while (true)

	} // private void AdminMenu()



//--------------------------------------------------------------------------------------------
	// 판매 항목 관리
	private String viewManageFoodMenu() { // 판매 관리 메뉴
		String selMenu5 = null;
		System.out.println(" *** 판매 항목 관리 *** ");
		System.out.println(" 원하는 메뉴를 선택해 주세요. ");
		System.out.println(" 1. 판매 목록 / 2. 항목 추가 / 3. 항목 삭제 / 4. 관리자 메뉴로 돌아가기");
		System.out.println(" ------------------------------------- ");
		try {
			selMenu5 = sc.nextLine();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다." + e.getMessage());
		}
		return selMenu5;
	}

	private void manageFoodMenu() { // 판매 관리 메뉴
		foodMenuLoop: while (true) {
			switch (viewManageFoodMenu()) {
			case "1": // [판매 목록] 선택
				System.out.println(" 1. 판매 목록");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				adminMenu.showFoodlist();
				break;

			case "2": // [항목 추가] 선택
				System.out.println(" 2. 항목 추가");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				adminMenu.addFood(); // 회원 등록 삭제 불러오기
				break;

			case "3": // [항목 삭제] 선택
				System.out.println(" 3. 항목 삭제 ");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				adminMenu.removeFood(); // 회원 등록 삭제 불러오기
				break;

			case "4": // [관리자 메뉴로 돌아가기] 선택
				System.out.println(" 4. 관리자 메뉴로 돌아가기 ");
				System.out.println(" 관리자 메뉴로 돌아갑니다. ");
				System.out.println(" ------------------------------------- ");
				System.out.println("");
				break foodMenuLoop;

			default:
				System.out.println(" 잘못된 입력입니다. 다시 입력해주세요. ");
				// 기타 잘못된 입력
			} // switch (viewManageFoodMenu())

		} // foodMenuLoop : while (true)

	} // private void viewManageFoodMenu()

}