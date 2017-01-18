package com.abinbev.jwt;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.mule.api.MuleEventContext;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;

/**
 * <p>
 * This model use
 * <a href="https://github.com/auth0/java-jwt">https://github.com/auth0/java-jwt
 * <a> lib to process JWT token.
 * </p>
 * <p>
 * Although this lib has a simple implementation logic, that one doesn't provide
 * a lot tools to handle the token properties.
 * </p>
 * <p>
 * For example: the {@link Claim} object is limited about the way to get
 * properties values. Is must specify that one correct type otherwise the value
 * returned is null.
 * </p>
 * <p>
 * Another negative point it is not be clear how is identified the token secret
 * key.
 * </p>
 * 
 * @author Hernani
 */
public class JWTDecoder extends JWTCallable {

	@Override
	public Map<String, String> onCall(MuleEventContext eventContext) throws Exception {

		super.onCall(eventContext);

		Map<String, String> generatedClaims = new HashMap<String, String>();

		try {

			JWT jwt = JWT.decode(token);
			// Claim claim = claims.get("isAdmin");

			Map<String, Claim> claims = jwt.getClaims();
			for (Entry<String, Claim> claim : claims.entrySet()) {
				generatedClaims.put(claim.getKey(), claim.getValue().asString());
			}

			logger.info("Claims converted successfully");

		} catch (JWTDecodeException e) {
			logger.error(e);
		}

		return generatedClaims;
	}

}
