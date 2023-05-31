package hellojava.practice2;

public abstract class AddrStructur {
	String name;
	int age;
	String sex;
	String tele;
	String addr;
	
	AddrStructur(String pinfo){
		String[] arr_info = pinfo.trim().split(",");
		for(String str:arr_info) {
			System.out.println(str);
		}
	}
}
