package com.maverickstube.maverickshub.security.filters;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.maverickstube.maverickshub.dtos.request.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
@AllArgsConstructor
@Component
public class CustomUsernamePasswordAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //1. Retrieve authentication credentials from the request body
            InputStream requestBodyStream = request.getInputStream();
            //2. Convert the Json data from 1 to java object(LoginRequest)
            LoginRequest loginRequest = objectMapper.readValue(requestBodyStream, LoginRequest.class);
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            //3. Create an authentication object that is not yet authenticated
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
            //4a. Pass the unauthenticated Authentication object to the AuthenticationManager for autentication

            //4b. Get back the Authentication result from the AuthenticationManager
            Authentication authenticationResult = authenticationManager.authenticate(authentication);
            //5. Put the authentication result in the security context
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            return authenticationResult;
        } catch (IOException e) {
            throw new BadCredentialsException(e.getMessage());
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
