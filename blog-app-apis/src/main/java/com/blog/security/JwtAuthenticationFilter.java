package com.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.services.UserDetailsServiceImpl;
import com.blog.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsServiceImpl customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// It stores the authorization header details like Bearer 21342423432423lkjlk
		String header = request.getHeader("Authorization");
		String username = null;
		String token = null;

		// Checking whether the header format is valid or not..
		// Valid authorization header always starts with the Bearer(space)
		if (header != null && header.startsWith("Bearer ")) {

			token = header.substring(7); // stores the token value..
			try {
				username = jwtUtil.extractUsername(token); // extracting username from the given token..
			} catch (UsernameNotFoundException e) {
				System.out.println("username not found");
				e.printStackTrace();
			}
			catch (ExpiredJwtException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				// extracting userdetails from the username which is in the token...
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

				if (jwtUtil.validateToken(token, userDetails) == true) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, // credentials
							userDetails.getAuthorities());
					// setDetails add sessionID info, client ip address to the existing user
					// details...
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}

			} else {
				// throw new Exception("Enter valid token");
				System.out.println("token is not validated");
			}

		} else {
			// throw new Exception("Invalid header");
			System.out.println("Invalid header");
			System.out.print("Under authentication filter class else part");
		}
		filterChain.doFilter(request, response);

	}

}
