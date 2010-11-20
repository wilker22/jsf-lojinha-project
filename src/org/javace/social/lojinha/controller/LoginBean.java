package org.javace.social.lojinha.controller;

import org.javace.social.lojinha.model.Usuario;
import org.javace.social.lojinha.service.UsuarioService;
import org.javace.social.lojinha.util.FacesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class LoginBean {

	private String login;
	private String senha;
	
	private final UsuarioWeb usuarioWeb;
	private final UsuarioService usuarioService;
	private final FacesUtils facesUtils;
	
	@Autowired
	public LoginBean(UsuarioWeb usuarioWeb, UsuarioService usuarioService, FacesUtils facesUtils) {
		this.usuarioWeb = usuarioWeb;
		this.usuarioService = usuarioService;
		this.facesUtils = facesUtils;
	}

	public String logar() {
		Usuario usuario = usuarioService.autentica(login, senha);
		if (usuario != null) {
			usuarioWeb.loga(usuario);
			return "home";
		}
		facesUtils.adicionaMensagemDeErro("Login ou senha inválida.");
		return null;
	}
	
	public String sair() {
		usuarioWeb.logout();
		return "login";
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
