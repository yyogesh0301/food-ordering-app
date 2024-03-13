//package com.example.foodorderback.security;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.example.foodorderback.service.CustomUserDetailService;
//
//
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private CustomUserDetailService customUserDetailService;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//
//        String authorizationHeader = httpServletRequest.getHeader("Authorization");
//
//        String token = null;
//        String userName = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            token = authorizationHeader.substring(7);
//            userName = jwtUtil.extractUsername(token);
//        }
//
//        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
//
//            if (jwtUtil.validateToken(token, userDetails)) {
//
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}
//

package com.example.foodorderback.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.foodorderback.service.CustomUserDetailService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final CustomUserDetailService customUserDetailService;

	public JwtFilter(JwtUtil jwtUtil, CustomUserDetailService customUserDetailService) {
		this.jwtUtil = jwtUtil;
		this.customUserDetailService = customUserDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Extract token from the authorization header, skipping if not present or unnecessary
		String token = extractToken(request);

		// Skip processing if not required or token is absent
		if (!requiresAuthentication(request, token)) {
			filterChain.doFilter(request, response);
			return;
		}

		// Attempt authentication and set context
		if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				String username = jwtUtil.extractUsername(token);
				UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

				if (jwtUtil.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				} else {
					// Handle invalid token (e.g., expired token)
					logger.error("Invalid JWT token");
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					response.getWriter().write("{\"message\": \"Invalid token\"}"); // Informative error message
					return;
				}
			} catch (ExpiredJwtException e) {
				logger.error("Token expired", e);
				SecurityContextHolder.getContext().setAuthentication(null);
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write("{\"message\": \"Token expired\"}"); // Specific error message
				return;
			} catch (Exception e) {
				logger.error("Error while processing JWT token", e);
				SecurityContextHolder.getContext().setAuthentication(null);
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}
		}

		filterChain.doFilter(request, response);
	}

	private String extractToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.substring(7);
		}
		return null;
	}

	private boolean requiresAuthentication(HttpServletRequest request, String token) {
		// Implement logic to determine if specific paths require authentication based on token presence
		// Replace with your specific logic (e.g., checking public endpoints)

		return true;
	}
}
