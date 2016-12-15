package core;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface for defining the behaviours of the broker service
 * @author Rem
 *
 */
@WebService
public interface SocietiesService {
	@WebMethod public List<Card> getCards(ClientInfo info) throws Exception;
}
