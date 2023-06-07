package hello_java.collation;

import java.util.ArrayList;
import java.util.List;

public class BoardDao {
	
	public List<Board> getBoardList() {
		List<Board> board = new ArrayList<>();
		board.add(new Board("gd","rap"));
		board.add(new Board("top","rap"));
		return board;
	}
}
