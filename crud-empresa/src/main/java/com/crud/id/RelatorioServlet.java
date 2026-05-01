package com.crud.id;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/relatorio")
public class RelatorioServlet extends HttpServlet{
	
	@Inject //relatorio service que agora é uma instancia de RelatorioServlet -> tera uma instancia injetada pelo CDI
	private RelatorioService relatorioService;
	
	@Override //sobreescrever
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(relatorioService.totalPedidoMes());
	}

}

//CDI juntamente com o TomCat quando foi criar a instancia do ServLet ele viu que tinha uma propriedade/uma depedencia do ServLet que estava anotada com @Inject; entao o CDI comecou a preparar uma instancia de RelatorioServlet, e quando ele comecou a preparar essa instancia, ele atraves de reflexao (Reflection Java API), ele viu na classe (Relatorio Service) que existia uma dependencia tambem que estava anotada com @Inject e que portando ele precisava preparar e injetar na propriedade 'pedidos', e se nossa classe Pedidos tambem tivesse alguma dependencia com @Inject ele teria que resolver, e por isso, pela resolucao que CDI vai fazendo nas propriedades que estao anotadas com @Inject, por isso a gente nao chega a ter nullPointerException, como o CDI injeta a instancia para nos (RelatorioServvice), entao quando chama o metodo totalPedidoMes(), eu nao tenho o nullPointerException, e tambem quando chama pedidos.totalPedidosMes() tambem nao tem nullPointerException.
