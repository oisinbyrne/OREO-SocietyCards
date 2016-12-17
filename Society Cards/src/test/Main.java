package test;
import basicServer.AdminClass;
import core.ClientInfo;
import core.Card;
import impl.WSSocietiesService;

public class Main {
	public static void main(String[] args) throws Exception {
	    
        // Create the Societies Service and runs the test data
//        WSSocietiesService societies = new WSSocietiesService();
//        WSSocietiesService.contactServices();
//        for (ClientInfo info : clients) {
//        	System.out.println("Name: " + info.getName());
//			for(Card card : societies.getCards(info)) {
//				System.out.println("Reference: " + card.getReferenceNo());
//			}
//        }
//		AdminClass LHSociety = new AdminClass("L&H Society");
		AdminClass FoodSociety = new AdminClass("UCD Food Society");
		FoodSociety.addStudent(14207364);
		FoodSociety.addStudent(15437649);
		FoodSociety.addStudent(16495765);
		FoodSociety.checkMembership(14207364);
		FoodSociety.checkMembership(54648787);

	}

	/**
	 * Test client data
	 */
	public static final ClientInfo[] clients = {
			new ClientInfo("Oisin Byrne", 13437368),
			new ClientInfo("Evin Murphy", 14207364),			
			new ClientInfo("Rucha Sohoni", 13385581),			
			new ClientInfo("Owen Phelan", 13437592)
	};
}
