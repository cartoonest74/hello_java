package service;

import java.util.Scanner;

import vo.addres;

public class AddresService {
	private int index;
	private addres a[] = new addres[10];

	// 배열의 추가된 요소가 몇개인지 저장하는 변수
	/**
	 * 학교 구성원을 추가하는 메서드 객체가 넘어오면 배열에 추가하고 index++한다.
	 */
	public void addPerson(addres a) {
		this.a[index] = a;
		index++;
	}

	/**
	 * p 배열의 요소 모두를 출력 toString()을 오버라이딩 했으므로 배열 요소(ex)p[0])만 출력하면 된다. index를
	 * 이용해 배열 모두가 아니라 추가된 요소까지만 출력 해야 한다.
	 */
	public void printAll() {
		for (int i = 0; i < index; i++) {
			System.out.println(a[i]);
		}
	}

	/**
	 * 전화번호로 학교 구성원을 검색하는 메서드 넘어온 번호와 배열요소의 전화번호가 일치하면 해당 배열 요소를 리턴한다. 만약 일치되는
	 * 배열 요소가 없을 경우 null을 반환한다.
	 */
	public addres findPerson(String name) {
		addres per = null;
		for (int i = 0; i < index; i++) {
			if (a[i].getName().equals(name)) {// 이름이 같은지 비교
				per = a[i];// per 변수에 배열요소의 주소값을 할당
			}
		}
		return per;
	}

	public addres findUpdate(String name) {
		
		addres up = findPerson(name);
		System.out.println("전화번호 입력:");
		Scanner sc = new Scanner(System.in);
		up.setTel(sc.next());
		System.out.println("주소 입력:");
		up.setAddr(sc.next());
		return up;
		
	}
	
}
