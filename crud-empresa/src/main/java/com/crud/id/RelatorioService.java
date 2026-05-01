package com.crud.id;

import java.math.BigDecimal;

import javax.inject.Inject;

public class RelatorioService {
	
	@Inject //essa propriedade pedidos sera injetada por uma instancia que vai ser criada pelo CDI 
	private Pedidos pedidos;
	
	public RelatorioService(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
	public RelatorioService() {
	}

	public BigDecimal totalPedidoMes() {
		return pedidos.totalPedidoMes();
	}
	
	public void setPedidos(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
}
