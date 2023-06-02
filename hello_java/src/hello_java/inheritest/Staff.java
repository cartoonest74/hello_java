package hello_java.inheritest;

public class Staff extends Person {
	private String job;
	
	public void print_job() {
		System.out.println("job:"+job);
	}
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
}
