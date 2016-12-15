package card;

import core.ClientInfo;
import core.Card;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface to define the behaviour of a quotation service.
 * 
 * @author Rem
 *
 */
@WebService
public interface CardService {
	@WebMethod public Card generateCard(ClientInfo info);
}