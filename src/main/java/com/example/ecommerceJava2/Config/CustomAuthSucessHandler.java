package com.example.ecommerceJava2.Config;

import com.example.ecommerceJava2.Model.Role;
import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {

		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

		CustomUser userDetails = (CustomUser) authentication.getPrincipal();
		System.out.println(roles.contains(Role.ADMIN.toString()));
		response.sendRedirect("/");
//		if (userDetails != null) {
//			if (roles.contains("ADMIN")) {
//				response.sendRedirect("/admin/profile");
//			} else {
//				response.sendRedirect("/user/profile");
//			}
//		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
