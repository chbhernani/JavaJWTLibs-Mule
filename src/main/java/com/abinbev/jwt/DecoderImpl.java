package com.abinbev.jwt;

import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class DecoderImpl implements Callable {

	protected static Logger logger = Logger.getLogger(JWTDecoder.class);
	protected String token;

	public Object onCall(MuleEventContext eventContext) throws Exception {
		token = eventContext.getMessage().getInboundProperty("token");
		return null;
	}

}
