package com.crud.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.crud.model.Empresa;
import com.crud.repository.Empresas;
import com.crud.util.Transacional;

public class CadastroImpresaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject //injetando o repositorio 
	private Empresas empresas;
	
	@Transacional //operacoes que precisam de um transacao, lembrando que: metodo pesquisar nao precisa!
	public void Salvar(Empresa empresa ) { //executar o try, do TransactionInterceptor
		empresas.guardar(empresa);
	}
	
	//codigo muito mais limpo: nao temos que iniciar e nem fechar as nossas transacoes.
	
	@Transacional
	public void Excluir (Empresa empresa) {
		empresas.remover(empresa);
	}
}
