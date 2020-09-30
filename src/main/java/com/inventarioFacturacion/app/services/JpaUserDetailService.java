/*
 * Fecha: 09-24-2019
 * @Jaime_Ramirez
 */
package com.inventarioFacturacion.app.services;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.inventarioFacturacion.app.dao.IPerfilEmpleado;
import com.inventarioFacturacion.app.dao.IRolPerfil;
import com.inventarioFacturacion.app.model.entity.PerfilEmpleado;
import com.inventarioFacturacion.app.model.entity.RolPerfil;

@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IPerfilEmpleado iperfil;

	@Autowired
	private IRolPerfil irolPerfil;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException{
		
		PerfilEmpleado empleado = iperfil.findAllByNombrePerfil(username);
		
		Collection<RolPerfil> rolPerfilList = null;
		User user = null;
		if (empleado != null) {
			
			rolPerfilList = irolPerfil.findByEmpleadoId(empleado.getId());
			
			if(rolPerfilList!=null) {
				user = new User(empleado.getNombre(),
						empleado.getPassword(),
						mapRolesToAuthories(rolPerfilList));
			}
		}
		return user;

	}
	
	/**
	 * Create collections.
	 *
	 * @param roles the roles
	 * @return Collection<? extends GrantedAuthority>
	 * 
	 * Add Pre Fix ROLE_ to RoleName, Example ROLE_ADMIN
	 */
	private Collection<? extends GrantedAuthority> mapRolesToAuthories(Collection<RolPerfil> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRol().getNombre()))
				.collect(Collectors.toList());
	}

}
