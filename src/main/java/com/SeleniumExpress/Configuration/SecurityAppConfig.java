package com.SeleniumExpress.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig {

	@Autowired
	HttpSecurity httpSecurity;

	/*
	 * @Bean public PasswordEncoder password() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
	@Bean
	public InMemoryUserDetailsManager userdetailsmanager() {/*
															 * SimpleGrantedAuthority role1=new
															 * SimpleGrantedAuthority("admin"); SimpleGrantedAuthority
															 * role2=new SimpleGrantedAuthority("User");
															 * 
															 * ArrayList<GrantedAuthority>authorities=new
															 * ArrayList<GrantedAuthority>(); authorities.add(role2);
															 * authorities.add(role1);
															 * 
															 * UserDetails shekhar=new
															 * User("shekhar","shekhar",authorities);
															 * 
															 * 
															 * 
															 * InMemoryUserDetailsManager inMemoryUserDetailsManager=new
															 * InMemoryUserDetailsManager();
															 * inMemoryUserDetailsManager.createUser(shekhar); return
															 * inMemoryUserDetailsManager;
															 */

		UserDetails Akhil = User.withUsername("Akhil")
				// .password("{noop}Akhil")
				.password("{bcrypt}$2a$10$wbZIGDUD4e3Nh8dwIpqgoONWLfodzqStpWmHK3bygR58zWUnaVjz.").roles("Admin", "User")
				.build();

		return new InMemoryUserDetailsManager(Akhil);

	}

	@Bean
	SecurityFilterChain settingUpHttpFilterChain() throws Exception {
		/*
		 * httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
		 * httpSecurity.formLogin(); httpSecurity.httpBasic();
		 */

		httpSecurity.authorizeHttpRequests(customizer -> {
			customizer.requestMatchers("/Hello").permitAll();
			customizer.requestMatchers("/Hi").authenticated();

		}

		);
		httpSecurity.formLogin(Customizer.withDefaults());

		httpSecurity.httpBasic(Customizer.withDefaults());

		return httpSecurity.build();

	}

}
