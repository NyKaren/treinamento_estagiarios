package com.indracompany.stags.bo;

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
			return midiaDAO.buscarMidia(nome);
		} catch (Exception e) {
			throw new Exception("Erro ao tentar buscar uma m�dia pelo nome -> "+e.getMessage());
		}
	}

	@Override
	public MidiaModel buscar(Long codigo) throws Exception {
		try {
			return midiaDAO.buscar(codigo);
		} catch (Exception e) {
			throw new Exception("Erro ao tentar buscar uma m�dia pelo c�digo -> "+e.getMessage());
		}
	}
}
