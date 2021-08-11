package kr.or.bit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MemberAdmin {

	private Map<String, Member> memberInfo;
	private Admin manager;
	private Scanner sc;
	private String escape;
	private String loginAuthor;
	public File file;

	private static MemberAdmin instance;

	MemberAdmin() {
		memberInfo = new HashMap<String, Member>();
		sc = new Scanner(System.in);
		manager = new Admin();
		escape = "exit";
		loginAuthor = "fail";
		file = new File("memberInfo.txt");

	}

	public Map<String, Member> getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(Map<String, Member> memberInfo) {
		this.memberInfo = memberInfo;
	}

	public String getLoginAuthor() {
		return loginAuthor;
	}

	public void setLoginAuthor(String loginAuthor) {
		this.loginAuthor = loginAuthor;
	}

	public static MemberAdmin getInstance() {
		if (instance == null) { // null >> 아직 메모리를 갖지 않았다면
			instance = new MemberAdmin(); // 객체 생성 (heap 메모리에 객체가 생성)
		}
		return instance;

	}

	// -----------------------------------------------------------------------------
	// 로그인
	public Member logIn() {

		String id;
		String pw;

		logInLoop: while (true) {
			inputIdLoop: while (true) {
				System.out.print(" 아이디 : ");
				id = sc.nextLine().trim();
				mReadMemberList();
				if (id.equals(escape)) {
					System.out.println("");
					break logInLoop;

				} else if (!memberInfo.containsKey(id)) { // id X
					if (id.equals(manager.getAdminId())) {
						// 멤버 리스트에 없으나 관리자 아이디일때 루프 탈출하여 패스워드 입력받음
						break inputIdLoop;
					}
					System.out.println("\n 입력하신 아이디가 존재하지 않습니다. 다시 입력해주세요. (메인 페이지로 돌아가려면 exit를 입력해주세요)");
					System.out.println("-----------------------------");
					System.out.println("");
					continue;
				} else {
					break inputIdLoop;
				}

			} // inputIdLoop : while(true)

			inputPwdLoop: while (true) {
				System.out.print("\n 패스워드 : ");
				pw = sc.nextLine().trim();

				if (pw.equals(escape)) {
					System.out.println("");
					break logInLoop;

				} else if (manager.getAdminId().equals(id) && manager.getAdminPw().equals(pw)) {
					System.out.println("\n 관리자 님 환영합니다. 로그인 되었습니다. \n");
					loginAuthor = "true";
					break logInLoop;

				} else if (memberInfo.get(id).mPw.equals(pw)) { // pw O
					System.out.println("\n " + id + " 님 환영합니다. 로그인 되었습니다. \n");
					loginAuthor = "false";
					
					return memberInfo.get(id);

				} else { // pw X
					System.out.println("\n 패스워드가 맞지 않습니다. 다시 입력해주세요. (메인 페이지로 돌아가려면 exit를 입력해주세요) \n");
				}

			} // inputPwdLoop : while(true)
		} // logInLoop : while(true)
		return null;

	} // public void logIn()

	// -----------------------------------------------------------------------------
	// 회원가입
	public void joinIn() {
		// 아이디 입력
		String id;
		String pw;
		String name;
		String phoneNo;
		
		mWriteMemberList();
		
		joinInLoop: while (true) {

			// -----------------------------------------------------------------------------
			// 아이디
			fineIdLoop: while (true) {
				// ------------ 아이디 입력
				System.out.println("\n 원하는 아이디를 이메일 형식으로  입력하세요 (예: 1mam1jo@naver.com)");
				System.out.println(" (메인 페이지로 돌아가려면 exit를 입력해주세요)\n");

				id = sc.nextLine();

				if (id.equals(escape)) {
					System.out.println("");
					break joinInLoop;
				}

				// ------------- 중복아이디 체크

//				if (memberInfo.containsKey(id)) {
//					System.out.println("이미 존재하는 아이디 입니다.");
//					System.out.println("-----------------------------");
//					System.out.println("");
//					continue;
//				}

				mReadMemberList();
				if (memberInfo.containsKey(id)) {
						System.out.println("\n 이미 존재하는 아이디 입니다.");
						System.out.println("-----------------------------");
						System.out.println("");
						continue;
				}
				

				// ------------ 아이디 형식 체크
				if (!id.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,}$")) {
					System.out.println("\n 올바른 아이디 형식이 아닙니다. 다시 입력해주세요.");
					System.out.println("-----------------------------");
					System.out.println("");
					continue;
				} else {
					break fineIdLoop;
				}
			}
			// -----------------------------------------------------------------------------
			// 비밀번호
			finePwdLoop: while (true) {
				// ------------ 비밀번호 입력
				System.out.println("\n 원하는 비밀번호를 입력해 주세요(최소 8 자, 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자)");
				System.out.println(" (메인 페이지로 돌아가려면 exit를 입력해주세요)\n");
				pw = sc.nextLine();

				if (pw.equals(escape)) {
					System.out.println("");
					break joinInLoop;
				}

				// ------------ 비밀번호 형식 체크
				if (!pw.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")) {
					System.out.println("\n 알맞는 비밀번호 형식이 아닙니다. 다시 입력해 주세요.");
					System.out.println(" -----------------------------");
					System.out.println("");
				} else {
					break finePwdLoop;
				}
			}

			// -----------------------------------------------------------------------------
			// 이름
			System.out.println("\n 이름을 입력해 주세요.");
			System.out.println(" (메인 페이지로 돌아가려면 exit를 입력해주세요)\n");
			name = sc.nextLine();

			if (name.equals(escape)) {
				System.out.println("");
				break joinInLoop;
			}
			// -----------------------------------------------------------------------------
			// 핸드폰 번호
			finephoneNoLoop: while (true) {
				// ------------ 핸드폰 번호 입력
				System.out.println("\n 핸드폰 번호를 입력해 주세요. (예: 000-0000-0000)");
				System.out.println(" (메인 페이지로 돌아가려면 exit를 입력해주세요)\n");
				phoneNo = sc.nextLine();

				if (phoneNo.equals(escape)) {
					System.out.println("");
					break joinInLoop;
				}

				// ------------ 핸드폰 번호 형식
				if (!phoneNo.matches("^[0][1]\\d\\-\\d{3,4}\\-\\d{4}$")) {
					System.out.println("\n 알맞는 핸드폰 번호 형식이 아닙니다. 다시 입력해 주세요");
					System.out.println("-----------------------------");
					System.out.println("");
				} else {
					break finephoneNoLoop;
				}
			}

			// ------------ 입력된 값으로 새로운 객체 만들기
			Member member = new Member(id, pw, name, phoneNo);
			// HashMap<id, member(pw,name,phoneNo)> 넣기
			memberInfo.put(id, member);

			mWriteMemberList();

			System.out.println("\n 회원가입이 완료되었습니다.");
			System.out.println(member.toString());
			System.out.println("");

			break joinInLoop;

		}
	}

	// -----------------------------------------------------------------------------
	// 파일 넘기기
	private void mWriteMemberList() {

		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		ObjectOutputStream oout = null;

		try {
			fout = new FileOutputStream(file);
			bout = new BufferedOutputStream(fout);
			oout = new ObjectOutputStream(bout);

			oout.writeObject(memberInfo);

		} catch (Exception e) {
			System.out.println(" 해당하는 파일이 존재하지 않습니다.\n");
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
	// 회원 검색
	public void searchMember() {
		mReadMemberList();
		String id;
		System.out.println("\n 검색할 회원 아이디 : ");
		id = sc.nextLine();
		System.out.println("");

		if (memberInfo.containsKey(id)) {
			Member member = memberInfo.get(id);
			System.out.println("\n 검색 결과 >> \n" + member.toString());
			System.out.println("");
		} else {
			System.out.println("\n 입력하신 아이디가 존재하지 않습니다.\n");
		}
	}

	// -----------------------------------------------------------------------------
	// 회원 리스트 파일에서 읽어오기
	
	public void mReadMemberList() {
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;

		try {
			fin = new FileInputStream(file);
			bin = new BufferedInputStream(fin);
			oin = new ObjectInputStream(bin);

			memberInfo = (Map<String, Member>) oin.readObject();
			
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
	// -----------------------------------------------------------------------------
		// 회원 리스트 불러오기
	public void mMemberList() {
		mReadMemberList();
		for (Member member : memberInfo.values()) {
			System.out.println(member.toString());
		}
	}


	// -----------------------------------------------------------------------------
	// 회원삭제
	public void mRemoveMember() {
		mReadMemberList();
		String id;
		System.out.println("삭제할 아이디를 입력해주세요.");
		id = sc.nextLine();
		System.out.println("");

		if (memberInfo.containsKey(id)) {
			memberInfo.remove(id);
			mWriteMemberList();
			System.out.println("삭제되었습니다.\n");
		} else {
			System.out.println("입력하신 아이디가 존재하지 않습니다.\n");
		}
	}

}
