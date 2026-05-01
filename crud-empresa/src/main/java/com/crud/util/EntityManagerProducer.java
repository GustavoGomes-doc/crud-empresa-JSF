package com.crud.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		this.factory = Persistence.createEntityManagerFactory("CrudEmpresaPU");
	}

	@Produces //Estamos dizendo ao CDI que o metodo createEntityManager é o metodo produtor de EntityManager entao toda vez que uma classe pedir uma instancia de EntityManager o CDI vai vir neste metodo aqui e vai invocar, e metodo vai devolver esta instancia 
	@RequestScoped	//Scopo do instancia que este metodo criar, o CDI vai gerenciar ele dentro do scopo de requisicao, ou seja, a cadaa requisacao vai ser uma instancia nova 	
	public EntityManager createEntityManager() {
		return this.factory.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager manager) { //Metodo diz: que toda vez que o EntityManager for encerrado, que acabar o Scopo dele que no caso é de requisicao, o CDI vai chamar este metodo, (tenq chamar por conta da anotacao @Dispose), e a gente na implementacao do metodo vai fechar o nosso EntityManager
		manager.close();
	}  
}
