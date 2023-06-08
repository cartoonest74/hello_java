package hello_java.lamda;

public class ButtonExample {

	public static void main(String[] args) {
		Button btnok = new Button();
		btnok.setClickListener(()->System.out.println("ok 버튼을 클릭했습니다")); 
		btnok.click();
		
		Button btncancle = new Button(); 
		btncancle.setClickListener(()->System.out.println("cancle 버튼을 클릭했습니다")); 
		btncancle.click();
	}

}
