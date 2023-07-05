package boardTest.board;

public interface Service {
	void boardEnroll(String loginId);
	void boardSelect(String loginId);
	void boardEdit(String loginId);
	void boardDelete(String loginId);
}
