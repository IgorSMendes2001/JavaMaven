package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;


import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.RelatorioVendas;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedidos {
    public static void main(String[] args) {
        popularBD();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao=new ClienteDao(em);
        Produto produto=produtoDao.buscarPorId(1l);
        Cliente cliente=clienteDao.buscarPorId(1l);
        em.getTransaction().begin();
        Pedido pedido=new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10,pedido,produto));
        PedidoDao pedidoDao=new PedidoDao(em);
        pedidoDao.cadastrar(pedido);
        em.getTransaction().commit();
        BigDecimal totalVendido=pedidoDao.valorTotalVendido();
        System.out.println("Valor total: "+totalVendido);
        // List<Object[]>relatorio=pedidoDao.relatorioVendas();
        // for (Object[] objects : relatorio) {
        //     System.out.println(objects[0]);
        //     System.out.println(objects[1]);
        //     System.out.println(objects[2]);
        // }
        List<RelatorioDeVendasVo> relatorioVendas=pedidoDao.relatorioDeVendas();
        relatorioVendas.forEach(System.out::println);
	}

	private static void popularBD() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
        Cliente cliente=new Cliente("Igor","5466565454");
        ClienteDao clienteDao=new ClienteDao(em);
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();
	
    }
}
