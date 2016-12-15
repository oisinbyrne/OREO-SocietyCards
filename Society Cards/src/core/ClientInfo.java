package core;

public class ClientInfo {
	private String name;
	private int studentNo;
	
	public ClientInfo(String name, int studentNo) {
		this.name = name;
		this.studentNo = studentNo;
	}
	
	public ClientInfo() {}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}
	
	public String getName() {
		return name;
	}
	
	public int getStudentNo() {
		return studentNo;
	}
}
