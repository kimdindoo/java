package kr.or.bit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodAdmin {
	private List<Order> tempOrderList;
	private List<Order> orderList;
	private List<Food> foodList;
	private File file;
	private String escape;
	private Scanner sc;

	private static FoodAdmin instance;

	public static FoodAdmin getInstance() {
		if (instance == null) {
			instance = new FoodAdmin();
		}
		return instance;
	}

	private FoodAdmin() {
		tempOrderList = new ArrayList<Order>();
		orderList = new ArrayList<Order>();
		foodList = new ArrayList<Food>();
		file = new File("foodInfo.txt");
		escape = "exit";
		sc = new Scanner(System.in);

	}

	public List<Order> getTempOrderList() {
		return tempOrderList;
	}

	public void setTempOrderList(List<Order> tempOrderList) {
		this.tempOrderList = tempOrderList;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}

	// 음식 리스트
	public void showFoodlist() {
		mReadFoodList();
		for (int i = 0; i < foodList.size(); i++) {
			Food temp = foodList.get(i);
			System.out.printf("▶ %d : [%s - %d원]\n", i + 1, temp.getfName(), temp.getfPrice());
		}
		System.out.println("");
	}

	// 음식 추가하기
	public Food addFood() {
		String fName;
		int fPrice;

		mWriteFoodList();

		addFoodLoop: while (true) {
			inputNameLoop: while (true) {
				showFoodlist();
				System.out.println(" 추가하고자 하는 상품명을 입력해주세요.");
				System.out.println(" (관리자 페이지로 돌아가려면 exit를 입력해주세요)\n");

				fName = sc.nextLine();

				if (fName.equals(escape)) {
					System.out.println("");
					break addFoodLoop;
				}

				if (check(fName)) { // 중복 상품 체크
					System.out.println("\n 이미 존재하는 상품입니다.");
					System.out.println(" ----------------------------- ");
					System.out.println("");
					continue;
				} else {
					break inputNameLoop;
				}
			}

			System.out.println("\n 가격을 입력해 주세요");

			fPrice = Integer.parseInt(sc.nextLine());

			Food food = new Food(fName, fPrice);
			foodList.add(food);

			mWriteFoodList();

			System.out.println("\n 상품이 추가되었습니다.");
			System.out.println("▼");
			System.out.println(food.toString());
			System.out.println("");

			break addFoodLoop;
		} // while
		return null;
	} // addFood

	// 음식 삭제
	public void removeFood() {
		mReadFoodList();
		int num;

		for (int i = 0; i < foodList.size(); i++) {
			Food temp = foodList.get(i);
			System.out.printf("▶ %d : [%s - %d원]\n", i + 1, temp.getfName(), temp.getfPrice());
		}

		System.out.println(" 삭제할 상품 번호를 입력해주세요. 중단을 원하면 0을 눌러주세요.");
		num = Integer.parseInt(sc.nextLine());
		if (num == 0) {
		} else if (num <= foodList.size()) {
			foodList.remove(num - 1);
			mWriteFoodList();
			System.out.println(" 삭제되었습니다.");
		} else {
			System.out.println(" 해당 상품이 존재하지 않습니다.");
		}
		System.out.println("");
	}

	// List foodList 안에 중복 fName 확인 함수
	private Food FindName(String fName) {
		mReadFoodList();
		for (Food food : foodList) {
			if (food.getfName().equals(fName)) {
				return food; // 찾으면 food 를 반환
			}
		}
		return null; // 맞는 food 없으면 없다
	}

	// fName 체크
	private boolean check(String fName) {
		mReadFoodList();
		boolean check = true;
		Food food = FindName(fName);
		if (food == null) {
			check = false;
		}
		return check;
	}

	// -----------------------------------------------------------------------------
	// 파일 넘기기
	private void mWriteFoodList() {

		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		ObjectOutputStream oout = null;

		try {
			fout = new FileOutputStream(file);
			bout = new BufferedOutputStream(fout);
			oout = new ObjectOutputStream(bout);

			oout.writeObject(foodList);

		} catch (Exception e) {
			System.out.println(" 해당하는 파일이 존재하지 않습니다.");
		} finally {
			try {
				oout.close();
				bout.close();
				fout.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// -----------------------------------------------------------------------------
	// 리스트 파일에서 읽어오기

	public void mReadFoodList() {
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;

		try {
			fin = new FileInputStream(file);
			bin = new BufferedInputStream(fin);
			oin = new ObjectInputStream(bin);

			foodList = (List<Food>) oin.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oin.close();
				bin.close();
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}