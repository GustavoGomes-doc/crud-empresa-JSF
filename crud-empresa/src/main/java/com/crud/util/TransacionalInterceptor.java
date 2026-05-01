package com.crud.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor //anotacao do CDI: que dizemos a ele que essa classe (TransacionalInterceptor) é um interceptador
@Transacional //marcando o intercpetador para dizer ao CDI que os metodos ou classes anotados com essa anotacao. //Todo metodo que tiver o transational, na hora que este metodo for invocado por nos, o CDI vai indenficicar isso e vai falar: "opa, tem um interceptador aqui para este metodo (o nosso TransactioalInterceptor) e  antes desse metodo poder ser executado o CDI vai chamar o nosso meotodo do nosso Interceptor (TransatctionalInterceptor), e ele sabe disso por conta do @AroundInvoke 
@Priority(Interceptor.Priority.APPLICATION) //para ativar o nosso interceptador com prioridade de aplicacao, caso nao tivesse isso teria que configurar la no beans.xml
public class TransacionalInterceptor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject //Instancia de EntityManager que vai injetado pelo CDI, depois da chamada do metodo produtor, o metodo que esta devolvendo instancias de entity manager o (createEntityManager()).
	private EntityManager manager;
	
	@AroundInvoke //de fato o nosso metodo interceptador. //Todo metodo que tiver o transational, na hora que este metodo for invocado por nos, o CDI vai indenficicar isso e vai falar: "opa, tem um interceptador aqui para este metodo (o nosso TransactioalInterceptor) e  antes desse metodo poder ser executado o CDI vai chamar o nosso meotodo do nosso Interceptor (TransatctionalInterceptor), e ele sabe disso por conta do @AroundInvoke 
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction(); //pegamos a transacao
		boolean criador = false; //criando a propriedade para que o metodo acima tenha conciencia se foi ele ou nao que abriu e fechou a transacao. Porque é o seguinte, qualquer um pode declarar o EntityManager, qualquer classe, e pode ser que alguma outra classe tenha feito isso e tenha aberto a transacao.
		
		try { //caso algo acontece, o nosso interceptador abaixo vai resolver isso!
			if (!trx.isActive()) {
				//truque para fazer rollback no que ja passou
				//(senao, um futuro commit confiraria até mesmo operacoes sem transacao).
				trx.begin();
				trx.rollback();
				
				// agora sim inicia a transação.
				trx.begin();
				criador = true;
			}
			
			return context.proceed(); //avisando ao CDI que nosso metodo seja processado
			
		} catch (Exception e) {
			if (trx != null && criador) {
				trx.rollback();
			}
			
			throw e;
		} finally {
			if(trx != null && trx.isActive() && criador) {
				trx.commit(); //vai ser commitado apenas se nao houver nenhuma execessao no metodo que esta sendo interceptado: context.proceed()
			}
		}
	}
	
}
