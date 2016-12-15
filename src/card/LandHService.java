package card;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import core.AbstractCardService;
import core.ClientInfo;
import core.Card;

@WebService(name = "CardService")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class LandHService extends AbstractCardService implements CardService {
	
	@Override
	public Card generateCard(ClientInfo info) {
		// Generate the card and send it back
		Card card = new Card();
		// Use the reference generator to create a reference
		card.setReferenceNo(generateReference());
		card.setClientInfo(info);
		
		return card;
	}
}
