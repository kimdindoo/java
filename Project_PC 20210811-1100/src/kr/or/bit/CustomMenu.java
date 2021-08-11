package kr.or.bit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

public class CustomMenu {
	private SeatAdmin seatAdmin;
	private FoodAdmin foodAdmin;
	private Member member;
	private Scanner sc;

	private boolean ispaid;
	private int orderNo;
	private int selecFoodNum;

	public CustomMenu() {

		this.foodAdmin = FoodAdmin.getInstance();
		this.seatAdmin = SeatAdmin.getInstance();
		orderNo = 1000;
		selecFoodNum = 0;
		sc = new Scanner(System.in);

	}

	public void login(Member member) {

		this.member = member;

	}

	public void showFood() {
		foodAdmin.showFoodlist();
	}

	public void orderFood() {
		int orderCount = 0;
		String userSeatNo = "";

		orderNo++; // (이전 주문번호 +1)번 의 주문

		foodOrderLoop: while (true) {
			sc = new Scanner(System.in);
			showFood();
			System.out.println("\n구매하고자 하는 상품의 번호를 입력해주세요.");
			System.out.println("결제를 진행하려면 1010 / 주문을 취소하고 이전 페이지로 돌아가려면 0번을 입력해주세요.");
			selecFoodNum = Integer.parseInt(sc.nextLine());

			if (selecFoodNum == 0) { // 주문 취소
				System.out.println("주문을 취소합니다.");
				System.out.println("");
//				if (foodAdmin.getTempOrderList().size() > 0) {
//					for (Iterator<Order> it = foodAdmin.getTempOrderList().iterator(); it.hasNext();) {
//						Order templist = it.next();
//						if (templist.getOrderNo() == orderNo) {
//							it.remove();
//						}
//					}
//				}
				foodAdmin.getTempOrderList().clear();
				orderNo--;
				break foodOrderLoop;

			} else if (selecFoodNum == 1010) {
				if (orderCount == 0) {
					System.out.println(" 고객님의 장바구니에 상품이 없습니다. ");
					System.out.println("");
				} else {
					payment();
					break foodOrderLoop;
				}

			} else if (selecFoodNum > 0 && selecFoodNum <= foodAdmin.getFoodList().size()) {
				Food food = foodAdmin.getFoodList().get(selecFoodNum - 1);
				Order order = new Order();
				ArrayList<UseSeat> useList = seatAdmin.getUseSeatList();
				for (UseSeat useSeat : useList) {
					if (member.mId.equals(useSeat.getMember().mId)) {
						userSeatNo = useSeat.getSeatNo();
						break;
					}
				}

				order.setFood(food);
				order.setMember(member);
				order.setSeat(userSeatNo);
				order.setOrderNo(orderNo);

				foodAdmin.getTempOrderList().add(order);
				orderCount++;

				System.out.println(" " + food + "을 바구니에 담았습니다.\n");
				printTempOrderList();
				System.out.println(" 상품을 추가로 구매하시겠습니까?");
				System.out.println(" 1. 예 / 2. 아니오 ");

				int needMoreFood = Integer.parseInt(sc.nextLine());

				switch (needMoreFood) {
				case 1:
					break;

				case 2:
					System.out.println(" 결제를 진행합니다. ");
					System.out.println(" ------------------ ");
					payment();
					foodAdmin.getOrderList().addAll(foodAdmin.getTempOrderList());
					foodAdmin.getTempOrderList().clear();
					
					break foodOrderLoop;

				default:
					System.out.println(" 잘못된 입력입니다. ");
				}

			} else {
				System.out.println("잘못된 숫자를 입력하셨습니다");
			}
			System.out.println("");
		}
	}

	public int sum() {

		int sum = 0;

		Iterator<Order> it = foodAdmin.getOrderList().iterator();
		while (it.hasNext()) {
			Order order = it.next();
			sum += order.getFood().getfPrice();
		}

		return sum;
	}

	public void printOrderList() {

		if (foodAdmin.getOrderList().size() > 0) {

			Iterator<Order> it = foodAdmin.getOrderList().iterator();
			while (it.hasNext()) {
				Order order = it.next();
				System.out.println(order.toString());
			}
			System.out.println("--------------------------------");
			System.out.println("Total price : " + sum());
			System.out.println("");

		} else {
			System.out.println("주문내역이 없습니다");
		}
	}

	public int tempSum() {
		
		int tempsum = 0;
		
		Iterator<Order> it = foodAdmin.getTempOrderList().iterator();
		while (it.hasNext()) {
			Order order = it.next();
			tempsum += order.getFood().getfPrice();
		}
		
		return tempsum;
	}
	
	public void printTempOrderList() {
		
		if (foodAdmin.getTempOrderList().size() > 0) {
			
			Iterator<Order> it = foodAdmin.getTempOrderList().iterator();
			while (it.hasNext()) {
				Order order = it.next();
				System.out.print("▶ ");
				System.out.println(order.toString());
			}
			System.out.println("--------------------------------");
			System.out.println("Total price : " + tempSum());
			System.out.println("");
			
		} else {
			System.out.println("주문내역이 없습니다");
		}
	}
	public boolean payment() {
		ispaid = false;

		System.out.printf(" 총 금액은 %d입니다. 카드를 삽입해주세요\n ", tempSum());
		System.out.println("......");
		System.out.println("");
		System.out.println(" 결제가 완료되었습니다. 감사합니다. ");
		System.out.println("");

		return true;
	}

	/// 좌석
	public void showSeat() {
		seatAdmin.showSeat();
	}

	public void selectSeat() {
		seatAdmin.selectSeat();
	}

	public void logout() {
		seatAdmin.logout();
	}

	public void BuyTime() {
		seatAdmin.BuyTime();
	}

}
