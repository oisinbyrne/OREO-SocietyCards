package test;
import javax.xml.ws.Endpoint;
import card.LandHService;
import vetting.LocalVettingService;

/**
 * Deploys each of the four web services on ports 9000-9003
 * 
 * @author Oisín
 *
 */
public class Deploy {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9000/CardService/generateCard", new LandHService());
		Endpoint.publish("http://localhost:9001/VettingService/vetClient", new LocalVettingService());
    }
}
