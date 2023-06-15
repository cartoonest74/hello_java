package hello_java.accountReview;

public class Kpop {
	private int no;
	private String name;
	private String album;
	private int trackList;
	
	public Kpop() {}
	public Kpop(int no, String name, String album, int trackList) {
		super();
		this.no = no;
		this.name = name;
		this.album = album;
		this.trackList = trackList;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public int getTrackList() {
		return trackList;
	}
	public void setTrackList(int trackList) {
		this.trackList = trackList;
	}
	
}
