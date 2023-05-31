package hellojava.practice2;

public class TestMemberVo {
	public static void main(String[] arg) {
		MemberVo vo = new MemberVo("GD","GD","GD","GD","GD");
		
		vo.setName("vip");
		System.out.println(vo.getName());
	}
}
