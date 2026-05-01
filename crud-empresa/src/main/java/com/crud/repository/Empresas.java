package com.crud.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.crud.model.Empresa;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject //injetando para deixar tudo na mao do CDI
	private EntityManager manager; //Apartir desta classe, que vamos realizar todas as operações.
	
	public Empresas() {}
	
	public Empresas(EntityManager manager) {
		this.manager = manager;
	}
	
	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}
	
	public List<Empresa> pesquisar(String nome) {
		String jpql = "from Empresa e where e.nome like :nomeEmpresa"; //pode buscar o nome das empresas sem o nome completo
		
		TypedQuery<Empresa> query = manager
				.createQuery(jpql, Empresa.class);
		
		query.setParameter("nomeEmpresa", nome + "%"); //buscar pela primeira letra
		
		return query.getResultList();
	}
	
	public Empresa guardar (Empresa empresa) {
		return manager.merge(empresa); //merge -> vai atualizar com o DB, por ex
									//se eu to passando uma empresa e ela ainda nao existe, ele vai salvar essa empresa (INSERT)
									//agora se eu to passando uam empresa que ja existe, ela irá atualizar (UPDATE)
	}
	
	public void remover (Empresa empresa) {
		empresa = porId(empresa.getId());
		manager.remove(empresa);
	
	}
}