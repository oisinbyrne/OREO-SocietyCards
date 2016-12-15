package vetting;

import core.ClientInfo;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface defining the expected behaviour of a vetting service.
 * 
 * @author Rem
 *
 */
@WebService
public interface VettingService {
	@WebMethod public boolean vetClient(ClientInfo info);
}