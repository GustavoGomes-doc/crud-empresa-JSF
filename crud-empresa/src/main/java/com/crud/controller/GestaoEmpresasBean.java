package com.crud.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.crud.model.Empresa;
import com.crud.model.TipoEmpresa;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Empresa empresa = new Empresa();
	
	public void Salvar () {
		System.out.println("Razao social: " + empresa.getRazaoSocial() + 
				"- Nome da Empresa: "  + empresa.getNome() +
				"- Tipo: " + empresa.getTipo());
	}
	
	public String ajuda () {
		return "AjudaGestaoEmpresas?faces-redirect=true";
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public TipoEmpresa[] getTiposEmpresa() { //devolvendo um array com as propriedades que estao dentro do ENUM
		return TipoEmpresa.values()
;	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//private static Integer NUMERO = 0;
	
	//public GestaoEmpresasBean() { //toda vez que for criada uma instancia no managed bean  vai dar para saber q uma instancia foi criada
		//NUMERO++;
	//}
	 
	//public Integer getNumero() {
	//	return NUMERO;
	//}

}
