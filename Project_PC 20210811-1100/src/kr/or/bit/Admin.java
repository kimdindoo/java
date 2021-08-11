package kr.or.bit;

public class Admin {
	private String adminId = "admin";
	private String adminPw = "1234";
	
	public String getAdminId() {
		return adminId;
	}
	
	public String getAdminPw() {
		return adminPw;
	}

	@Override
	public String toString() {
		return "[아이디 = " + adminId + ", 패스워드 = " + adminPw + "]";
	}	
	
}