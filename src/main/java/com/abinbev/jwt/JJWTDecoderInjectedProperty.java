package com.abinbev.jwt;

import java.util.Map;

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
public class JJWTDecoderInjectedProperty extends DecoderImpl {

	private String uncodedKey;

	@Override
	public Claims onCall(MuleEventContext eventContext) throws Exception {

		super.onCall(eventContext);

		Claims body = null;
		try {
			// TextCodec.BASE64.encode("secret");
			// DatatypeConverter.parseBase64Binary("secret")

			String signedKey = TextCodec.BASE64.encode(uncodedKey);
			body = Jwts.parser().setSigningKey(signedKey).parseClaimsJws(token).getBody();

			logger.info("Claims converted successfully");
			
		} catch (Exception e) {
			logger.error(e);
		}

		return body;
	}

	public void setUncodedKey(String uncodedKey) {
		this.uncodedKey = uncodedKey;
	}
}
