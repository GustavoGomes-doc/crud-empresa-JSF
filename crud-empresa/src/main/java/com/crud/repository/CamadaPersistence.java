package com.crud.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.crud.model.Empresa;
import com.crud.model.RamoAtividade;
import com.crud.model.TipoEmpresa;

public class CamadaPersistence {
	
	public static void main(String[] args) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CrudEmpresaPU");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin(); //se nao iniciar uma transacao consegue fazer apenas consultas, qualquer inserção tentada apartir de fora de uma transacao, simplemeste quando for conferir na base de dados ele nao vai estar lá
		
		//declarando os repositórios
		RamoAtividades ramoAtividades = new RamoAtividades(em);
		Empresas empresas = new Empresas(em);
		
		//buscando as informações do banco
		List<RamoAtividade> listaDeRamoAtividades = ramoAtividades.pesquisar("");
		List<Empresa> listaDeEmpresas = empresas.pesquisar("");
		System.out.println(listaDeEmpresas);
		
		//criando uma empresa
		Empresa empresa = new Empresa();		
		empresa.setNomeEmpresa("João da Silva");
		empresa.setCnpj("41.952.519/0001-57");
		empresa.setRazaoSocial("João da Silva 41952519000157");
		empresa.setTipo(TipoEmpresa.MEI);
		empresa.setDataFundacao(new Date());
		empresa.setRamoAtividade(listaDeRamoAtividades.get(0));
		
		//salvando a empresa
		empresas.guardar(empresa);
		
		em.getTransaction().commit(); //commit da transação
		
		//verificando se a inserção funcionou
		List<Empresa> listaDeEmpresas2 = empresas.pesquisar("");
		System.out.println(listaDeEmpresas2);
		
		
		em.close();
		emf.close();
	}

}