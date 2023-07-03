package boardTest.board;

public class BoardDTO {
	private int nid;
	private String writer;
	private String title;
	private String content;

	public BoardDTO() {}
	
	public BoardDTO(int nid, String writer, String title, String content) {
		super();
		this.nid = nid;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BoardDTO [nid=" + nid + ", writer=" + writer + ", title=" + title + ", content=" + content + "]";
	}
}
