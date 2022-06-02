/*
 * Copyright (c) 2019. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.config;

import ch.fhnw.GenZ.business.service.AgentService;
import ch.fhnw.GenZ.business.service.DistanceService;
import ch.fhnw.GenZ.business.service.TransportCostService;
import ch.fhnw.GenZ.data.domain.Agent;
import ch.fhnw.GenZ.data.domain.Distance;
import ch.fhnw.GenZ.data.domain.TransportCost;
import ch.fhnw.GenZ.data.domain.UserRole;

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
import ch.fhnw.GenZ.business.service.UserDetailsServiceImpl;
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
			km = 30;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(181.65);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(201.45);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(220.30);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(238.35);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(255.35);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(271.45);
			transportCostService.saveTransportCost(transportCost);
			km = 30;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(286.55);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(67.00);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(99.40);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(130.85);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(156.75);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(183.80);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(207.55);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(230.25);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(251.85);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(272.40);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(291.90);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(310.25);
			transportCostService.saveTransportCost(transportCost);
			km = 60;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(327.50);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(75.40);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(111.90);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(147.15);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(176.35);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(206.65);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(233.50);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(259.00);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(283.35);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(306.45);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(328.40);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(348.95);
			transportCostService.saveTransportCost(transportCost);
			km = 90;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(368.40);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(83.75);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(124.30);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(163.50);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(195.90);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(229.70);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(259.45);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(287.70);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(314.85);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(340.50);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(364.75);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(387.80);
			transportCostService.saveTransportCost(transportCost);
			km = 120;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(409.40);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(92.15);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(136.70);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(179.80);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(215.50);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(252.60);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(285.30);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(316.50);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(346.30);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(374.50);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(401.25);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(426.50);
			transportCostService.saveTransportCost(transportCost);
			km = 150;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(450.35);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(100.55);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(149.20);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(196.15);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(235.15);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(275.60);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(311.25);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(345.35);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(377.75);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(408.60);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(437.75);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(465.35);
			transportCostService.saveTransportCost(transportCost);
			km = 180;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(491.25);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(108.95);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(161.55);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(212.50);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(253.90);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(298.60);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(337.15);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(374.15);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(108.95);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(442.60);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(474.25);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(504.05);
			transportCostService.saveTransportCost(transportCost);
			km = 210;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(532.15);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(117.30);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(174.05);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(228.90);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(274.25);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(321.55);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(363.15);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(402.90);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(440.75);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(476.70);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(510.75);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(542.90);
			transportCostService.saveTransportCost(transportCost);
			km = 240;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(573.15);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(125.65);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(186.45);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(245.30);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(293.90);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(344.55);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(389.10);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(431.70);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(472.20);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(510.75);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(547.20);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(581.60);
			transportCostService.saveTransportCost(transportCost);
			km = 270;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(614.05);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(134.05);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(198.85);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(267.60);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(313.45);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(367.50);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(415.00);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(460.45);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(503.70);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(544.70);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(583.65);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(620.35);
			transportCostService.saveTransportCost(transportCost);
			km = 300;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(655.05);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 1;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(142.40);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 2;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(211.30);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 3;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(277.95);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 4;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(332.95);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 5;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(390.45);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(441.05);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(489.20);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(535.20);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(578.85);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(620.15);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(659.20);
			transportCostService.saveTransportCost(transportCost);
			km = 330;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(695.95);
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
			km = 360;
			pal = 6;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(466.90);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 7;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(518.00);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 8;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(566.70);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 9;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(612.85);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 10;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(656.65);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 11;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(697.95);
			transportCostService.saveTransportCost(transportCost);
			km = 360;
			pal = 12;
			transportCost = new TransportCost();
			transportCost.setKm(km);
			transportCost.setPal(pal);
			transportCost.setCost(736.90);
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