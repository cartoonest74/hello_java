package boardTest.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class BoardService implements Service {
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public BoardService(DataOutputStream dos, DataInputStream dis) {
		super();
		this.dos = dos;
		this.dis = dis;
	}

	@Override
	public void boardEnroll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardSelect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardEdit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardDelete() {
		// TODO Auto-generated method stub

	}
}
