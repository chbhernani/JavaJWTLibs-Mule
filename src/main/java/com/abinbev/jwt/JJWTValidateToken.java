package com.abinbev.jwt;

import org.mule.api.MuleEventContext;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.TextCodec;

public class JJWTValidateToken extends JWTCallable {

	@Override
	public Boolean onCall(MuleEventContext eventContext) throws Exception {
		
		// set token value
		super.onCall(eventContext);

		String uncodedKey = eventContext.getMessage().getInvocationProperty("uncodedKey");
		String signedKey = TextCodec.BASE64.encode(uncodedKey);

		logger.info("token: " + token);
		logger.info("uncodedKey: " + uncodedKey);
		logger.info("signedKey: " + signedKey);

		try {
			Jwts.parser().setSigningKey(signedKey).parseClaimsJws(token);

			logger.info("OK, we can trust this JWT.");

			return true;

		} catch (SignatureException e) {

			logger.info("Don't trust this JWT!");

			return false;
		} catch (Exception e) {
			logger.error(e);
		}

		return false;
	}

}
