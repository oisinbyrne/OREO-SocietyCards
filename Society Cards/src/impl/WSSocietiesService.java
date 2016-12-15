package impl;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import card.CardService;
import core.Card;
import core.ClientInfo;
import core.SocietiesService;
import vetting.VettingService;

/**
 * Web Services implementation of the broker service.
 * 
 * @author Oisín
 *
 */
public class WSSocietiesService implements SocietiesService {
	// Ports for each Web Service
	static VettingService vet;
	static CardService LandH;

	public static void main(String[] args) throws Exception {
		WSSocietiesService societies = new WSSocietiesService();
		contactServices();	// Get ports for each Web Service

		for (ClientInfo info : clients) {
			// Skip client if they do not pass vetting service
			if(vet.vetClient(info)) {
				System.out.println("Name: " + info.getName());
				for(Card card : societies.getCards(info)) {
					System.out.println("Reference: " + card.getReferenceNo());
				}
			}
			else continue;
		}
	}

	@Override
	public List<Card> getCards(ClientInfo info) throws Exception {
		List<Card> cards = new LinkedList<Card>();
		cards.add(LandH.generateCard(info));
		return cards;
	}

	public static void contactServices() throws Exception {
		URL LandHurl = new URL("http://localhost:9000/CardService/generateCard?wsdl");
		URL veturl = new URL("http://localhost:9001/VettingService/vetClient?wsdl");
		
		QName LandHqname = new QName("http://card/", "LandHServiceService");
		QName vetqname = new QName("http://vetting/", "LocalVettingServiceService");

		Service LandHservice = Service.create(LandHurl, LandHqname);
		Service vetservice = Service.create(veturl, vetqname);

		LandH = LandHservice.getPort(CardService.class);
		vet = vetservice.getPort(VettingService.class);
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