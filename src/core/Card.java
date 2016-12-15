package core;

public class Card {
	private int referenceNo;
	private ClientInfo client;
	
	public Card(int referenceNo, ClientInfo client) {
		this.referenceNo = referenceNo;
		this.client = client;
	}
	
	public Card() {}
	
	public void setReferenceNo(int referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	public void setClientInfo(ClientInfo client) {
		this.client = client;
	}
	
	public int getReferenceNo() {
		return referenceNo;
	}
	
	public ClientInfo getClientInfo() {
		return client;
	}
}
