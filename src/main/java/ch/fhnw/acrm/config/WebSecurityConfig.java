/*
 * Copyright (c) 2019. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.config;

import ch.fhnw.acrm.business.service.AgentService;
import ch.fhnw.acrm.business.service.DistanceService;
import ch.fhnw.acrm.business.service.TransportCostService;
import ch.fhnw.acrm.data.domain.Agent;
import ch.fhnw.acrm.data.domain.Distance;
import ch.fhnw.acrm.data.domain.TransportCost;
import ch.fhnw.acrm.data.domain.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ch.fhnw.acrm.business.service.UserDetailsServiceImpl;
import onl.mrtn.security.config.EnableTokenSecurity;
import onl.mrtn.security.service.TokenService;
import onl.mrtn.security.web.CSRFRequestMatcher;
import onl.mrtn.security.web.TokenAuthenticationFilter;
import onl.mrtn.security.web.TokenLoginFilter;
import onl.mrtn.security.web.TokenLogoutHandler;

@EnableWebSecurity
@EnableTokenSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AgentService agentService;
	@Autowired
	private DistanceService distanceService;
	@Autowired
	private TransportCostService transportCostService;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenService tokenService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and().requiresChannel()
				.requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null).requiresSecure().and() // If the
																										// X-Forwarded-Proto
																										// header is
																										// present,
																										// redirect to
																										// HTTPS
																										// (Heroku)
				.csrf().requireCsrfProtectionMatcher(new CSRFRequestMatcher())
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().authorizeRequests()
				.antMatchers("/", "/assets/**", "/user/**", "/login/**", "/adminlogin/**", "/aboutus/**",
						"/swagger-ui/**", "/h2-console/**", "/v3/api-docs/**", "/swagger-resources/**")
				.permitAll() // Show Pages without authentication
				.antMatchers(HttpMethod.GET, "/logout").permitAll().antMatchers("/profile/edit")
				.hasAnyRole("USER,ADMINISTRATOR").anyRequest().authenticated().and()
				.addFilter(new TokenLoginFilter(authenticationManagerBean(), tokenService))
				.addFilter(new TokenAuthenticationFilter(authenticationManagerBean(), tokenService)).logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.addLogoutHandler(new TokenLogoutHandler(tokenService));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		try {
			Agent sysAdmin = new Agent();
			sysAdmin.setEmail("system@admin.com");
			sysAdmin.setPassword("password");
			sysAdmin.setRole(UserRole.ADMINISTRATOR);
			sysAdmin.setName("System Administrator");
			agentService.saveAgent(sysAdmin);
		} catch (Exception e) {

		}

		// insert the distance data
		try {
			distanceService.deleteDistance();
			String fromCity = "Zürich";
			String toCity = "Zürich";
			Distance distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(10);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Geneva";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(224);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Basel";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(76);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Lausanne";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(174);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Bern";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(95);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Winterthur";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(20);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Lucerne";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(40);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "St. Gallen";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(62);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Lugano";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(129);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Bienne";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(55);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Thun";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(100);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Bellinzona";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(135);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Köniz";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(90);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Fribourg";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(123);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "La Chaux-de-Fonds";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(80);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Schaffhausen";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(60);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Chur";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(50);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Vernier";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(40);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Uster";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(30);
			distanceService.saveDistance(distance);
			fromCity = "Zürich";
			toCity = "Sion";
			distance = new Distance();
			distance.setFromCity(fromCity);
			distance.setToCity(toCity);
			distance.setKilometers(20);
			distanceService.saveDistance(distance);

			Integer km = 30;
			Integer pal = 1;
			TransportCost transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(58.65);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(87.00);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(114.50);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(137.10);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(160.80);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(150.75);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(223.80);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(294.30);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(352.65);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(413.45);
			transportCostService.saveTransportCost(transportCost);
		} catch (Exception e) {

		}
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}