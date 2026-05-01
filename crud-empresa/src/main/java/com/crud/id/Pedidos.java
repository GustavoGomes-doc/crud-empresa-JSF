package com.crud.id;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class Pedidos { //pedido esta simulando um repositorio (repository) de pedidos
	
	@Inject
	private EntityManager manager;
		
	public BigDecimal totalPedidoMes() {
		return new BigDecimal("100"); //valor ficticio
	}
	
}
