package kr.or.bit;

import java.util.Iterator;

public class AdminMenu {
	private MemberAdmin memberAdmin;
	private FoodAdmin foodAdmin;
	
	public AdminMenu() {
		this.memberAdmin = MemberAdmin.getInstance(); 		
		this.foodAdmin = FoodAdmin.getInstance();
	}
	
	//음식 리스트
	public void showFoodlist() {

		foodAdmin.showFoodlist();

	}
	
	// 음식 추가
	public void addFood() {
		foodAdmin.addFood();
	}
	
	// 음식 삭제
	
	public void removeFood() {
		foodAdmin.removeFood();
	}

	public void checkOrderList() {

		int sum = 0;

		if (foodAdmin.getOrderList().size() > 0) {

			Iterator<Order> it = foodAdmin.getOrderList().iterator();
			while (it.hasNext()) {
				Order order = it.next();
				System.out.println(order.toString());
				sum += order.getFood().getfPrice();
			}
			System.out.println("--------------------------------");
			System.out.println("Total price : " + sum);
		} else {
			System.out.println("주문내역이 없습니다");
		}

	}
//////////////////////////////////////////////////
	// 회원 검색
	public void searchMember() {
		memberAdmin.searchMember();
	}
	
	// 회원 리스트
	public void memberList() {
		memberAdmin.mMemberList();
	}
	
	// 회원 삭제
	public void removeMember() {
		memberAdmin.mRemoveMember();
	}

	
	
	
}