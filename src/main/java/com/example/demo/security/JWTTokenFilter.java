package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.controller.JwtController;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JwtController jwtController;
	
	@Autowired
	JWTUtil jwtutil;
	
	@Autowired
	SecurityUserAunthenticationService userAunthenticationService;

    JWTTokenFilter(JwtController jwtController) {
        this.jwtController = jwtController;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//1 . token available or not
		//Header : Authorization :This Token Value
		
		String JWTToken=request.getHeader("Authorization");
		
		String userId=null;
		
		if(JWTToken!=null && !JWTToken.isEmpty() && JWTToken.isBlank()) {
			System.out.println("OncePerRequestFilter : Token Presented :Incoming Token"+JWTToken);
			userId=jwtutil.getUserIdFromToken(JWTToken);
			System.out.println("Request came from an User:"+userId);
			
			
		}else {
			System.out.println("OncePerRequestFilter : Token Not Presented : Please add Token");
		}
		
		if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null ) {
			System.out.println("OncePerRequestFilter : getting User Info from database by passing userId : Got from the token");
			UserDetails userDetials = userAunthenticationService.loadUserByUsername(userId);
			Boolean isValidToken = jwtutil.validateToken(JWTToken, userDetials.getUsername());
			System.out.println("Once PerRequestFilter : Token Validation Result"+isValidToken);
			if(isValidToken) {
				System.out.println("OncePerRequestFilter : Setting Security Context for that userId");
				
				UsernamePasswordAuthenticationToken authenticationToken=
						new UsernamePasswordAuthenticationToken(userDetials,null);
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}else {
				
				System.out.println("Token is Invalid please provide valid token");
				
			}
			
			System.out.println("OncePerRequestFilter :Now Validating token user Id with database");
			
			
		}
		filterChain.doFilter(request, response);
		
	}

}
