package boardTest.product;

public class ProductDTO {
	private int nid;
	private String id;
	private String name;
	private String price;
	

	private String content;

	public ProductDTO() {}
	
	public ProductDTO(int nid, String id, String name, String price, String content) {
		super();
		this.nid = nid;
		this.id = id;
		this.name = name;
		this.price = price;
		this.content = content;
	}
	

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProductDTO [nid=" + nid + ", id=" + id + ", name=" + name + ", price=" + price + ", content=" + content
				+ "]";
	}


}