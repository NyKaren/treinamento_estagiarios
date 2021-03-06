package com.indracompany.stags.test;

import java.util.List;

import com.indracompany.stags.bo.ClienteBO;
import com.indracompany.stags.bo.ab.ClienteBOIf;
import com.indracompany.stags.model.ClienteModel;

public class ClienteTest {
	
	private static ClienteBOIf clienteBO;
	
	static{
		clienteBO = new ClienteBO();
	}
		
	public void inserirCliente() throws Exception{
		
		ClienteModel lCliente1 = new ClienteModel();
		lCliente1.setNome("Ant�nio Vision�rio");		
		
		ClienteModel lCliente2 = new ClienteModel();
		lCliente2.setNome("Luis Cardoso");
		
		ClienteModel lCliente3 = new ClienteModel();
		lCliente3.setNome("Caio");
		
		ClienteModel lCliente4 = new ClienteModel();
		lCliente4.setNome("Karen");
		
		ClienteModel lCliente5 = new ClienteModel();
		lCliente5.setNome("Matheus");
		
		clienteBO.inserir(lCliente1);
		clienteBO.inserir(lCliente2);
		clienteBO.inserir(lCliente3);
		clienteBO.inserir(lCliente4);
		clienteBO.inserir(lCliente5);
		
	}
	
	public void excluirCliente() throws Exception{
		ClienteModel lCliente = new ClienteModel();
		lCliente.setCodigo(2L);
		
		clienteBO.excluir(lCliente);
	}
	
	
	public void editarCliente() throws Exception{
		ClienteModel lCliente = new ClienteModel();
		lCliente.setCodigo(2L);		
		
		ClienteModel lClienteBusca = clienteBO.buscar(lCliente);
		
		if(lClienteBusca !=null){
			lClienteBusca.setNome("Luis de Jah");
			clienteBO.editar(lClienteBusca);
		}
	}
	
	public void exibirCliente()throws Exception{
		System.out.println("*******************************************************");
		List<ClienteModel> lListaCliente = clienteBO.listar();
		if(lListaCliente !=null && lListaCliente.size() > 0){
			for(ClienteModel lCliente:lListaCliente){
				System.out.println(lCliente);
			}
		}
	}
}
