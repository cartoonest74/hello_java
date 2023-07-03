package boardTest.product;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ProductService implements Service {
	private DataOutputStream dos;
	private DataInputStream dis;

	public ProductService(DataOutputStream dos, DataInputStream dis) {
		super();
		this.dos = dos;
		this.dis = dis;
	}

	@Override
	public void productEnroll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void productSelect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void productEdit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void productDelete() {
		// TODO Auto-generated method stub

	}

}
