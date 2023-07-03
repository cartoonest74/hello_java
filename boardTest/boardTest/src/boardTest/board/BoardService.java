package boardTest.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.json.JSONObject;

public class BoardService implements Service {
	private DataOutputStream dos;
	private DataInputStream dis;
	
	public BoardService(DataOutputStream dos, DataInputStream dis) {
		super();
		this.dos = dos;
		this.dis = dis;
	}

	@Override
	public void boardEnroll(JSONObject loginJSON) {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardSelect(JSONObject loginJSON) {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardEdit(JSONObject loginJSON) {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardDelete(JSONObject loginJSON) {
		// TODO Auto-generated method stub

	}
}
