package com.indracompany.stags.bo;

import java.text.DecimalFormat;
import java.util.List;

import com.indracompany.stags.bo.ab.MidiaBOIf;
import com.indracompany.stags.dao.MidiaDAOIf;
import com.indracompany.stags.dao.memory.MidiaDAOMemory;
import com.indracompany.stags.model.MidiaModel;

public class MidiaBO implements MidiaBOIf{
	private MidiaDAOIf midiaDAO; 
	
	public MidiaBO(){
		midiaDAO = new MidiaDAOMemory();
	}

	@Override
	public void inserir(MidiaModel pModel) throws Exception {
		try {
			validate(pModel);
			midiaDAO.inserir(pModel);
		} catch (Exception e) {
			throw new Exception("Erro ao tentar adicionar uma m�dia -> "+e.getMessage());
		}
	}

	@Override
	public void editar(MidiaModel pModel) throws Exception {
		try {
			validate(pModel);
			midiaDAO.editar(pModel);
		} catch (Exception e) {
			throw new Exception("Erro ao tentar editar uma m�dia -> "+e.getMessage());
		}
	}

	@Override
	public boolean validateCampoNumero(CharSequence pModel) throws Exception {
		boolean b;
		java.util.regex.Pattern r = java.util.regex.Pattern.compile("^[0-9]+$");
		java.util.regex.Matcher m = r.matcher(pModel);
		b =  m.matches();
		return b;
	}

	@Override
	public void excluir(MidiaModel pModel) throws Exception {
		try {
			validate(pModel);
			midiaDAO.excluir(pModel);
		} catch (Exception e) {
			throw new Exception("Erro ao tentar adicionar uma m�dia -> "+e.getMessage());
		}
	}

	@Override
	public List<MidiaModel> listar() throws Exception {
		try {
			return midiaDAO.listar();
		} catch (Exception e) {
			throw new Exception("Erro ao tentar listar m�dia -> "+e.getMessage());
		}
	}

	@Override
	public MidiaModel buscar(MidiaModel pModel) throws Exception {
		try {
			if(pModel.getCodigo() == null || pModel.getCodigo().equals("")){
				throw new Exception("Campo n�o pode ser nulo");
			}
			return midiaDAO.buscar(pModel);
		} catch (Exception e) {
			throw new Exception("Erro ao tentar buscar uma m�dia -> "+e.getMessage());
		}
	}
	
	@Override
	public void validate(MidiaModel pModel)throws Exception {
		if(pModel != null){
			try {
				if(pModel.getNome() == null || pModel.getNome().equals("")){
					throw new Exception("Nome � um campo obrigat�rio");
				}
				
				if(pModel.getDescricao() == null || pModel.getDescricao().equals("")){
					throw new Exception("Descri��o � um campo obrigat�rio");
				}
				
				if(pModel.getCodigoBarras() == null || pModel.getCodigoBarras().equals("")){
					throw new Exception("C�digo de barras � um campo obrigat�rio");
				}
				
				if(pModel.getCodigoBarras() == null || pModel.getCodigoBarras().equals("")){
					throw new Exception("C�digo de barras � um campo obrigat�rio");
				}
				
				if(pModel.getQuantidadeEstoque() == null || pModel.getQuantidadeEstoque().equals("")){
					throw new Exception("Quantidade em estoque � um campo obrigat�rio");
				}
				if(pModel.getQuantidadeEstoque() < 0){
					throw new Exception("Quantidade em estoque n�o pode ser negativa");
				}
				if(pModel.getValorAluguel() <= 0){
					throw new Exception("Valor de aluguel n�o pode ser negativa ou igual a zero");
				}
				if(pModel.getValorVenda() <= 0){
					throw new Exception("Valor de venda n�o pode ser negativa ou igual a zero");
				}
			} catch (Exception e) {
				throw new Exception("Erro ao tentar validar uma m�dia -> "+e.getMessage());				
			}
		}
	}

	public static void validatePosicaoLista(int posicaoLista)throws Exception {
		try {
			if(posicaoLista < 0){
				throw new Exception("Posi��o na lista tem de ser positiva. ");
			} 
		} catch (Exception e) {
			throw new Exception("Erro ao tentar validar uma m�dia -> "+e.getMessage());
		}
	}
	
	@Override
	public MidiaModel buscarMidia(String nome) throws Exception {
		try {
			for(MidiaModel midia  : listar()){
				if(midia.getNome().equalsIgnoreCase(nome)){
					System.out.println("M�dia encontrado: "+midia.toString());
					return midia;
				}
			}
			throw new Exception("M�dia n�o encontrada. ");
		} catch (Exception e) {
			throw new Exception("Erro ao tentar buscar uma m�dia pelo nome -> "+e.getMessage());
		}
	}

	@Override
	public MidiaModel buscar(Long codigo) throws Exception {
		try {
			for(MidiaModel midia  : listar()){
				if(midia.getCodigo().equals(codigo)){
					System.out.println("M�dia encontrada: "+midia.toString());
					return midia;
				}
			}
			throw new Exception("M�dia n�o encontrada. ");
		} catch (Exception e) {
			throw new Exception("Erro ao tentar buscar uma m�dia pelo c�digo -> "+e.getMessage());
		}
	}
	
	@Override
	public String mediaPrecoVenda() throws Exception {
		try {
			Integer quantidadeMidia = 0;
			Double mediaPrecoVenda = (double) 0;
			for(MidiaModel midia  : listar()){
				if (midia.getAtivo()) {
					mediaPrecoVenda += midia.getValorVenda();
					quantidadeMidia++;					
				}
			}		
			DecimalFormat df = new DecimalFormat("###,##0.00");
			return df.format(mediaPrecoVenda/quantidadeMidia);
		}catch (Exception e) {
			throw new Exception("Erro ao tentar exibir -> "+e.getMessage());
		}
	}
	
	@Override
	public String mediaPrecoAluguel() throws Exception {
		try {
			Integer quantidadeMidia = 0;
			Double mediaPrecoAluguel = (double) 0;
			for(MidiaModel midia  : listar()){
				if (midia.getAtivo()) {
					mediaPrecoAluguel += midia.getValorAluguel();
					quantidadeMidia++;
				}
			}		
			DecimalFormat df = new DecimalFormat("###,##0.00");
			return df.format(mediaPrecoAluguel/quantidadeMidia);
		}catch (Exception e) {
			throw new Exception("Erro ao tentar exibir -> "+e.getMessage());
		}
	}
	
	@Override
	public MidiaModel midiaMaisCaraVenda() throws Exception {	
		try {	
			MidiaModel auxiliar = new MidiaModel();
			auxiliar.setValorVenda((double) 0);
			for(MidiaModel midia  : listar()){
				if (midia.getAtivo()) {
					if(midia.getValorVenda() > auxiliar.getValorVenda()) {
						auxiliar = midia;
					}
				}
			}
			return auxiliar;			
		}catch (Exception e) {
			throw new Exception("Erro ao tentar exibir -> "+e.getMessage());
		}
	}

	@Override
	public MidiaModel midiaMaisCaraAluguel() throws Exception {
		try {
			MidiaModel auxiliar = new MidiaModel();
			auxiliar.setValorAluguel((double) 0);
			for(MidiaModel midia  : listar()){
				if (midia.getAtivo()) {
					if(midia.getValorAluguel() > auxiliar.getValorAluguel()) {
						auxiliar = midia;
					}
				}
			}
			return auxiliar;			
		}catch (Exception e) {
			throw new Exception("Erro ao tentar exibir -> "+e.getMessage());
		}
	}
	
	@Override
	public MidiaModel midiaMaisBarataVenda() throws Exception {
		try {
			MidiaModel auxiliar = new MidiaModel();
			auxiliar.setValorVenda(100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.7);
			for(MidiaModel midia  : listar()){
				if (midia.getAtivo()) {
					if(midia.getValorVenda() < auxiliar.getValorVenda()) {
						auxiliar = midia;
					}
				}
			}
			return auxiliar;			
		}catch (Exception e) {
			throw new Exception("Erro ao tentar exibir -> "+e.getMessage());
		}
	}

	@Override
	public MidiaModel midiaMaisBarataAluguel() throws Exception {
		try {
			MidiaModel auxiliar = new MidiaModel();
			auxiliar.setValorAluguel(100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.7);
			for(MidiaModel midia  : listar()){
				if (midia.getAtivo()) {
					if(midia.getValorAluguel() < auxiliar.getValorAluguel()) {
						auxiliar = midia;
					}
				}
			}
			return auxiliar;			
		} catch (Exception e) {
			throw new Exception("Erro ao tentar exibir -> "+e.getMessage());
		}
	}
	
	@Override
	public String exibirMidiaPrecoVendaAluguel() throws Exception {
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("\n\nM�dia de pre�o das m�dias cadastradas ").append("\nVenda: ").append(mediaPrecoVenda()).append("\nAluguel: ").append(mediaPrecoAluguel() + "\n");
			return  buffer.toString();			
		}catch (Exception e) {
			throw new Exception("Erro ao tentar exibir -> "+e.getMessage());
		}
	}

	@Override
	public String exibirMidiaPrecoMaisBaratoCaroVendaAluguel() throws Exception {
		try {
			StringBuilder buffer = new StringBuilder();
			buffer.append("\n\nM�dias com maior e menor pre�o do cadastro ").append("\nVenda: ").append("\nMais caro: ").append(midiaMaisCaraVenda() + "\n").append("\nMais barato: ").append(midiaMaisBarataVenda()).append("\n\n\nAluguel: ").append("\nMais caro: ").append(midiaMaisCaraAluguel() + "\n").append("\nMais barato: ").append(midiaMaisBarataAluguel() + "\n");
			return  buffer.toString();			
		} catch (Exception e) {
			throw new Exception("Erro ao tentar exibir -> "+e.getMessage());
		}
	}
}
