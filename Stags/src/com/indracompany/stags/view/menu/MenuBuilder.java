package com.indracompany.stags.view.menu;
import java.util.Scanner;

import com.indracompany.stags.model.ClienteModel;
import com.indracompany.stags.model.MidiaModel;

public class MenuBuilder {
		
		public String executarMenuInicial() {
			
			System.out.println("\t\tMenu de op��es:");
			System.out.println("\t1. Buscar Cliente");
			System.out.println("\t2. Buscar M�dia");
			System.out.println("\t3. Adicionar cliente");
			System.out.println("\t4. Adicionar m�dia");
			System.out.println("\t5. Editar cliente");
			System.out.println("\t6. Editar m�dia");
			System.out.println("\t7. Remover cliente");
			System.out.println("\t8. Remover m�dia");
			System.out.println("\t9. Alugar m�dia");
			System.out.println("\t10. Vender m�dia");
			System.out.println("\t0. Sair");
			
			return pedirEntrada("\nInsira sua op��o: ");
		}

		public void buscarCliente(ClienteModel cliente) throws Exception {
			String busca;
			busca = pedirEntrada("Digite o nome: ");
			System.out.printf("Buscando cliente " + busca + "...");
			cliente.getNomeBusca(busca);
			if(cliente != null){
				System.out.println("Cliente encontrado " + cliente.toString() + "...");
			}else{
				throw new Exception("Cliente n�o encontrado.");
			}
		}
		
		public void buscarMidia(MidiaModel midia) throws Exception {
			String busca;
			busca = pedirEntrada("Digite o nome: ");
			System.out.printf("Buscando m�dia " + busca + "...");
			midia.getNomeBusca(busca);
			if(midia != null){
				System.out.println("M�dia encontrada " + midia.toString() + "...");
			}else{
				throw new Exception("M�dia n�o encontrada.");
			}
		}
		
//		public void adicionarContato(Agenda agenda) throws Exception {
//			String nome = pedirEntrada("\n Digite o Nome: ");
//			String aniversario = pedirEntrada("\n Digite o anivers�rio: ");
//			
//			Pessoa pessoa = new Pessoa(nome,aniversario);
//			Contato contatoNovo = new Contato(pessoa);
//			String telefone = null;
//			do{
//				telefone = pedirEntrada("\n Digite o telefone (para cancelar, digite N): ");
//			    if(!telefone.equalsIgnoreCase("N")){
//			    	contatoNovo.addNumeroTelefone(telefone);
//			    }		                    
//			}while(telefone != null && !telefone.equalsIgnoreCase("N"));
//			try {
//				agenda.salvarContato(contatoNovo);
//				System.out.println("Contato inserido com sucesso: "+contatoNovo.toString());
//			} catch (Exception e) {
//				throw new Exception("Erro ao tentar adicionar um contato na agenda -> "+e.getMessage());
//			}
//		}
//		
//		public void excluirContato(Agenda agenda) throws Exception {
//			String nomeExcluir = pedirEntrada("\n Digite o Nome: ");
//			try {
//				Contato contatoExcluir = agenda.getContato(nomeExcluir);
//				agenda.removerContato(contatoExcluir);
//				System.out.println("Contato removido com sucesso: "+nomeExcluir.toString());		
//			} catch (Exception e) {
//				throw new Exception("Erro ao tentar excluir um contato na agenda -> Contato est�: "+e.getMessage());
//			}
//		}

//		public void editarContato(Agenda agenda) throws Exception {
//			
//			String nomeEditar = pedirEntrada("\n Digite o Nome: ");
//			try {
//				if(nomeEditar != null && !nomeEditar.equals("")){
//					
//					Contato contatoEditar = agenda.getContato(nomeEditar);
//					if(contatoEditar != null && !nomeEditar.equals("")){			
//						String opcaoSelecionadaMenuEditar = executarMenuEditar();
//						boolean continuar = true;
//						do{
//							switch(opcaoSelecionadaMenuEditar){
//							case "1":
//								String opcao1 = pedirEntrada("\n Digite o nome (para cancelar, digite 0): ");
//								if(opcao1 != null && !opcao1.equals("") && !opcao1.equals("0")){
//									contatoEditar.getPessoa().setNome(opcao1);
//								}else{
//									return;
//								}
//								break;
//							case "2":
//								String opcao2 = pedirEntrada("\n Digite o anivers�rio (para cancelar, digite 0): ");
//								if(opcao2 != null && !opcao2.equals("") && !opcao2.equals("0")){
//									contatoEditar.getPessoa().setAniversario(opcao2);
//								}else{
//									return;
//								}
//								break;	
//							case "3":
//								System.out.println("\t\tDigite o c�digo do telefone que deseja editar: ");
//								for(int i=0;i<contatoEditar.getNumeroTelefone().size();i++){
//									System.out.println("\t"+(i+1)+": Editar telefone: "+contatoEditar.getNumeroTelefone().get(i));
//								}
//								String opcaoTelefone = pedirEntrada("\n Digite o c�digo (para cancelar, digite 0): ");
//								if(opcaoTelefone != null && !opcaoTelefone.equals("") && !opcaoTelefone.equals("0")){
//									String novoTelefone = pedirEntrada("\n Digite o novo telefone: ");
////									Integer.valueOf(opcaoTelefone); //outra forma de escrever string -> integer
////									new Integer(opcaoTelefone); //outra forma de escrever string -> integer
//									contatoEditar.getNumeroTelefone().set((Integer.parseInt(opcaoTelefone)-1),novoTelefone);
//								}else{
//									return;
//								}							
//								break;
//							case "0":
//								break;
//							default: 
//								break;
//							}						
//						}while(continuar);
//					}else{
//						throw new Exception("Contato n�o encontrado");
//					}
//				}else{
//					throw new Exception("Nome inv�lido.");
//				}
//			}catch(Exception ex){
//				
//			}
//		}
		
		public String pedirEntrada(String mensagemEntrada) {
			Scanner entrada = new Scanner(System.in);
			System.out.print(mensagemEntrada);
			return entrada.nextLine();
		}
		
//		public String executarMenuEditar() {
//		       System.out.println("\t\tMenu de op��es:");
//		       System.out.println("\t1. Editar nome do contato");
//		       System.out.println("\t2. Editar anivers�rio do contato");
//		       System.out.println("\t3. Editar telefone do contato");
//		       System.out.println("\t0. Sair");
//		        		
//		       return pedirEntrada("\nInsira sua op��o: ");
//		}
}
