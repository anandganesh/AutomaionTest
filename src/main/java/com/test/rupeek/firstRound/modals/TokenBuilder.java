package com.test.rupeek.firstRound.modals;

import com.test.rupeek.firstRound.base.APIBase;

public class TokenBuilder extends APIBase {

	Token token;

	public Token getToken(String username, String password) {
		token = new Token();
		token.withUsername(username).withPassword(password);
		return token;
	}
}
