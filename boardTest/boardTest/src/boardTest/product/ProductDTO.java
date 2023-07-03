package boardTest.product;

public class ProductDTO {
	private int nid;
	private String name;
	private String price;
	private String content;

	public ProductDTO() {}
	
	public ProductDTO(int nid, String name, String price, String content) {
		super();
		this.nid = nid;
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

	@Override
	public String toString() {
		return "ProductDTO [nid=" + nid + ", name=" + name + ", price=" + price + ", content=" + content + "]";
	}

}
