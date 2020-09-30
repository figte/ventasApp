/*
 * Fecha: 09-23-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

// TODO: Auto-generated Javadoc
/**
 * The Class EmpleadoPerfilController.
 */
@Controller
public class HomeController {

	@GetMapping(value = "/")

	public String home() {
		return "views/Dashboard/dashboard";

	}

	@GetMapping(value = { "/login?logout=true", "/login" })
	public String login(Model model, Principal principal, RedirectAttributes flash,
			@RequestParam(value = "logout", required = false) String logout) {
		if (principal != null) {
			flash.addFlashAttribute("Info", "La sesión ya esta iniciada");
			return "redirect:/dashboard/index";
		}
		return "views/administration/login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}

}
