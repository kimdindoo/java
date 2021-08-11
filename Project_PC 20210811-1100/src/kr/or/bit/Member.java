package kr.or.bit;

import java.io.Serializable;


public class Member implements Serializable {
	static final long serialVersionUID = 42L; // 직렬화 명시

	final String mId;
	final String mPw;
	private String mName;
	private String mPhone;

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmId() {
		return mId;
	}

	public String getmPw() {
		return mPw;
	}

	public Member(String mId, String mPw, String mName, String mPhone) {
		super();
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mPhone = mPhone;
	}

	@Override
	public String toString() {
		return " 아이디 : " + mId + " / 비밀번호 : " +  mPw + " / 이름 : " + mName + " / 휴대폰 번호 : " + mPhone;
	}
	
	
}

