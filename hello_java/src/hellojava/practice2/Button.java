package hellojava.practice2;

public class Button {
	public static interface ClickListener{
		void onClick();
	}
	
	private ClickListener clickListener;
	
	public void setClickListener(ClickListener clicklistener) {
		this.clickListener = clicklistener;
	}
	
	public void click() {
		this.clickListener.onClick();
	}
	
}
