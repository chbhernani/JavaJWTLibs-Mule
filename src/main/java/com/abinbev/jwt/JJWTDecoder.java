package com.abinbev.jwt;

import org.mule.api.MuleEventContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;

/**
 * <p>
 * This model use
 * <a href="https://github.com/jwtk/jjwt">https://github.com/jwtk/jjwt <a> lib
 * to process JWT token.
 * </p>
 * <p>
 * This lib was implemented with fluent pattern what provides a simple
 * codification. In addition, was made available a clear way to set a token
 * secret key, provided that one be encoded to BASE64.
 * </p>
 * <p>
 * Another positive point is the transformed {@link Claims} object return with
 * the correct values, without efforts.
 * </p>
 * 
 * @author Hernani
 */
public class JJWTDecoder extends JWTCallable {

	@Override
	public Claims onCall(MuleEventContext eventContext) throws Exception {
		
		// set token value
		super.onCall(eventContext);
		
		String uncodedKey = eventContext.getMessage().getInvocationProperty("uncodedKey");
		String signedKey = TextCodec.BASE64.encode(uncodedKey);
		
		logger.info("token: " + token);
		logger.info("uncodedKey: " + uncodedKey);
		logger.info("signedKey: " + signedKey);

		Claims body = null;
		try {
			
			body = Jwts.parser().setSigningKey(signedKey).parseClaimsJws(token).getBody();

			logger.info("Claims converted successfully");
			logger.info("body: \n" + body);
			
		} catch (Exception e) {
			logger.error(e);
		}

		return body;
	}
}

