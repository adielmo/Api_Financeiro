package com.rabelo.financeiro.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.filter.CorsFilter;

import com.rabelo.financeiro.token.CustomTokenEnhacer;

@Profile("oauth-security")
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CorsFilter corsFilter;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	 clients.inMemory()
		.withClient("react")
		.secret("$2a$10$Jcf/fGVpAReHNCRMVG9uP.DEk41QibyaRO3Jr8Bnq97/.1Cfv2qIi")//re@ct
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(300)
		.refreshTokenValiditySeconds(360 * 24)
	.and()
		.withClient("moblie")
		.secret("$2a$10$1ZHeuJgyqcoDLH1F1qIbK.m4kv34vuXXHnPY4qKD8QOsLhlZCSlVu")//m0blie
		.scopes("read")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(300)
		.refreshTokenValiditySeconds(360 * 24);
	
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhacer(), accessTokenConverter()));
		
		endpoints
		    .tokenStore(tokenStore())
		    //.accessTokenConverter(this.accessTokenConverter())
		    .tokenEnhancer(tokenEnhancerChain)
		    .reuseRefreshTokens(false)
		    .userDetailsService(this.userDetailsService)
			.authenticationManager(this.authenticationManager);
	}
	
	@Bean
	public TokenEnhancer tokenEnhacer() {
		
		return new CustomTokenEnhacer();
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
	security.addTokenEndpointAuthenticationFilter(this.corsFilter);
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("financeiro");

		return accessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	
}
