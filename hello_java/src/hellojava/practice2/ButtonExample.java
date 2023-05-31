package hellojava.practice2;

public class ButtonExample{
	public static void main(String[] arg) {
		Button btnOk = new Button();
		
//		class OkListener implements Button.ClickListener{
//			public void onClick() {
//				System.out.println("ok");
//			}
//		}
		
		btnOk.setClickListener(new Button.ClickListener() {
			@Override
			public void onClick() {
				System.out.println("ok");
			}
		});
		
		btnOk.click();
		
		Button btnCl = new Button();
		
		btnCl.setClickListener(new Button.ClickListener() {
			public void onClick() {
				System.out.println("cancle");
			}
		});
//		class cancleListener implements Button.ClickListener{
//			public void onClick() {
//				System.out.println("cancle");
//			}
//		}
//		
//		btnCl.setClickListener(new cancleListener());
		
		btnCl.click();
	}
}
