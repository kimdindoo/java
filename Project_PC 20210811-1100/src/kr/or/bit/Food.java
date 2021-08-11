package kr.or.bit;

import java.io.Serializable;
import java.util.ArrayList;

public class Food implements Serializable{
	static final long serialVersionUID = 42L; // 직렬화 명시
	
	private String fName;
	private int fPrice;
	
	
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public int getfPrice() {
		return fPrice;
	}

	public void setfPrice(int fPrice) {
		this.fPrice = fPrice;
	}
	
	public Food(String fName, int fPrice) {
		this.fName = fName;
		this.fPrice = fPrice;
	}
	
	public Food() {

	}

	@Override
	public String toString() {
		return "[ 품목 = " + fName + ", 단가 = " + fPrice + "원 ]";
	}
	

}

