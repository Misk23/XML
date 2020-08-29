package com.xml.publications.security;;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		
	}


		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity.csrf().disable()
			.authorizeRequests()
			.antMatchers("/review/save").permitAll()
			.antMatchers("/workflow/get_to_review/{username}").permitAll()
			.antMatchers("/workflow/add_reviewer").permitAll()
			.antMatchers("/workflow/reject/{id}").permitAll()
			.antMatchers("/workflow/accept/{id}").permitAll()
			.antMatchers("/workflow/all").permitAll()
			.antMatchers("/scientificPublication/getScientificPublicationPDF/{id}").permitAll()
			.antMatchers("/workflow/all").permitAll()
			.antMatchers("/scientificPublication/all_accepted").permitAll()
			.antMatchers("/scientificPublication/showPublication/{id}").permitAll()
			.antMatchers("/scientificPublication/withdraw/{id}").permitAll()
			.antMatchers("/scientificPublication/my_publications/{username}").permitAll()
			.antMatchers("/coverLetter/save_as_XMLString").permitAll()
			.antMatchers("/coverLetter/save_as_XML").permitAll()
			.antMatchers("/scientificPublication/save_as_XMLString").permitAll()
			.antMatchers("/scientificPublication/save_as_XML").permitAll()
			.antMatchers("/user/all_reviewers").permitAll()
			.antMatchers("/user/register").permitAll()
			.antMatchers("/user/login").permitAll().anyRequest()
					.authenticated().and().exceptionHandling().and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and().httpBasic().and().csrf().disable();
			httpSecurity.cors();
			httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		}

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
		configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}



	/*
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() { System.out.println();
	 * System.out.println(NoOpPasswordEncoder.getInstance().toString()); return
	 * NoOpPasswordEncoder.getInstance(); }
	 */

}
