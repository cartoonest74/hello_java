package addr_manager.service;

import java.util.Scanner;

import addr_manager.vo.Addres;

public class AddresService {
	private int serachCount = 0;
	private int index = 0;
	// 출력부분 카운터
	private int out_count = 0;
	//수정부분 카운터
	private int edit_count = 0;

	
	private Addres a[] = new Addres[10];
	private String[] arr_baseInfo = {"이름", "나이", "성별", "전화번호", "주소"};
	
	private String userName;
	private String userAge;
	private String userTele;
	private String userAddr;
	
	// 등록카운트
	
	// TODO USER 등록
	public void enrollPerson(Addres en_per) {
		out_count = index;
		this.a[index] = en_per;
		System.out.println(a[index]);
		index ++;
	}
	
	// TODO ID 유효한지 검증
	public int searchUser(String fiUser) {
		System.out.println(this.a[serachCount].getID() + fiUser);
		try {
			if(this.a[serachCount].getID().equals(fiUser)) {
				System.out.println(this.a[serachCount].getID() + "");
			}else {
				serachCount ++;
				searchUser(fiUser);
			}
		}catch (Exception e){
			return serachCount ++;
		}
		return serachCount;
	}
	
	// TODO 수정부분
	public void userEdit(String editId) {
		int editIdNum = searchUser(editId);
		this.serachCount = 0;
		System.out.println("editnum" + editIdNum + "index" + this.index);
		if(editIdNum >= this.index) {
			System.out.println("ID 없어 꺼져 ");
		}else {
			String editComent = """
					수정을 원하시는 번호를 입력하시오. 
					1.이름
					2.나이
					3.성별
					4.전화번호
					5.주소
					ex) 1,2,3 or 2,3,5 식으로 순차적으로 입력하시오.
					""";
			System.out.println(editComent);
			Scanner sc = new Scanner(System.in);
			String str_editNum = sc.next();
			String[] arr_edit = str_editNum.trim().split(",");
			//수정부분 배열수
			int edit_len = arr_edit.length;
			userEditInspect(arr_edit, editIdNum);
		}
	}
	
	//  TODO 수정부분 검증
	public void userEditInspect(String[] edits, int arr_len){
		arr_len -- ;
		int edits_num = Integer.parseInt(edits[arr_len]);
		String str_editComent;
		switch (arr_baseInfo[edits_num]) {
			case("이름"):
					str_editComent = editStrTake(arr_baseInfo[edits_num]);
					this.a[arr_len].setName(str_editComent);
				break;
			case("성별"):
					str_editComent = editStrTake(arr_baseInfo[edits_num]);
					this.a[arr_len].setGender(str_editComent);
				break;
			case("나이"):
					str_editComent = editStrTake(arr_baseInfo[edits_num]);
					this.a[arr_len].setAge(str_editComent);
				break;
			case("전화번호"):
					str_editComent = editStrTake(arr_baseInfo[edits_num]);
					this.a[arr_len].setTele(str_editComent);
				break;
			case("주소"):
					str_editComent = editStrTake(arr_baseInfo[edits_num]);
					this.a[arr_len].setAddr(str_editComent);
				break;
			default:
				System.out.println("수정내용 확인");
				one_outPut(edits_num);
				break;
		}
	}
	
	// TODO 수정 부분 멘트 공통처리
	public String editStrTake(String editComment) {
		Scanner sc = new Scanner(System.in);
			System.out.println("수정할 "+ editComment +"를 입력해주세요");
			String str_edit = sc.next();
		return str_edit;
	}
	
	
	// TODO 일반 출력 부분
	public void one_outPut(int int_output) {
				userName = this.a[int_output].getName();
				userAge = this.a[int_output].getAge();
				userTele = this.a[int_output].getTele();
				userAddr = this.a[int_output].getAddr();			
				System.out.printf("이름: %s 나이: %s 전화번호: %s 주소: %s \n", userName, userAge, userTele, userAddr);
		}
	
	// TODO 전체 출력 부분
	public void All_outPut() {
		try{
			userName = this.a[out_count].getName();
			userAge = this.a[out_count].getAge();
			userTele = this.a[out_count].getTele();
			userAddr = this.a[out_count].getAddr();			
			out_count --;
			System.out.printf("이름: %s 나이: %s 전화번호: %s 주소: %s \n", userName, userAge, userTele, userAddr);
			All_outPut();
		}
		catch (Exception e){
			out_count = this.index - 1;
			System.out.println(e.getMessage() + out_count);
		}
	}
}
