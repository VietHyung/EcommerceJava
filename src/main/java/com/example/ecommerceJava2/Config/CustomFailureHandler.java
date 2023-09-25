package com.example.ecommerceJava2.Config;

import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Repository.UserRepository;
import com.example.ecommerceJava2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		System.out.println("failure handler");
		String email = request.getParameter("username");

		super.setDefaultFailureUrl("/login?error");
		super.onAuthenticationFailure(request, response, exception);
	}

}
