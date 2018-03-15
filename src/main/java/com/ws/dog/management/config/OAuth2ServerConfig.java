package com.ws.dog.management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

public class OAuth2ServerConfig {
	private static final String RESOURCE_ID = "office365doc";
	public static final String[] SCOPES = { "read", "write" };  
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
//			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//			.and()
//			.requestMatchers().anyRequest()
//			.and()
//			.authorizeRequests()
//			.antMatchers("/", "/login", "/login1","/css/**","/image/**", "/authorize.html", "/oauth/**").permitAll()
//			.and()
//			.anonymous()
//			.and()
//			.authorizeRequests()
//			.antMatchers("/Office365Doc").authenticated();
			http.authorizeRequests()
			.antMatchers("/test/get").access("#oauth2.hasScope('read')");
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId(RESOURCE_ID).stateless(true);
		}
		
	}
	
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
		
        @Autowired
        AuthenticationManager authenticationManager;
        
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.allowFormAuthenticationForClients();
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("scp_office")
			.resourceIds(RESOURCE_ID)
			//.authorizedGrantTypes("client_credentials", "refresh_token")
			.authorizedGrantTypes("authorization_code", "refresh_token")
			.scopes(SCOPES)
			.authorities("ROLE_USER")
			.secret("Initial1")
            .accessTokenValiditySeconds(60*30) // 30min  
            .refreshTokenValiditySeconds(60*60*24); // 24h
		}
		
		@Bean
		public TokenStore tokenStore() {
			return new InMemoryTokenStore();
		}
		
	}
}
