package kr.or.bit;
import java.util.Calendar;
import java.util.Scanner;

public class Time { 
	

	Calendar cal = Calendar.getInstance();
	
	String now = cal.get(Calendar.HOUR ) + "시" + cal.get(Calendar.MINUTE) + "분" ;
	
	void BuyTime() {
		
		
	Scanner sc = new Scanner(System.in);

	boolean payCard;
	
	System.out.println("이용할 시간을 입력하세요.");
	int inputTime = sc.nextInt();
	//String usingTime = "";
	
	System.out.println("카드를 넣어서 결제해주세요");
	if(payCard =true) {
		System.out.println("결제가 완료 됐습니다.");
		System.out.println((int)(cal.get(Calendar.HOUR )+ inputTime) + "시" + cal.get(Calendar.MINUTE) + "분 까지 이용가능합니다."); 
		} else {
			System.out.println("카드 결제가 돼지않았습니다.");
		}
	
	 
	}
}