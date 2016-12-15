package vetting;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import core.ClientInfo;

/**
 * @author oisin
 *
 * Checks if student is from UCD before signing up?
 *
 */
@WebService(name = "VettingService")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class LocalVettingService implements VettingService {

	@Override
	public boolean vetClient(ClientInfo info) {
		return true;
	}
}
