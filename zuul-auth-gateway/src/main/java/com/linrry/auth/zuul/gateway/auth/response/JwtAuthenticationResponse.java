package com.linrry.auth.zuul.gateway.auth.response;


public class JwtAuthenticationResponse {

    private static final long serialVersionUID = 1250162333152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

}
