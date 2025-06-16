package model.builder;

import model.entity.Consulta;
import model.entity.Prontuario;

public interface IProntuarioBuilder {
	public void buildId(Integer id);
	public void buildConsulta (Consulta consulta);
	public void buildObservacao (String observacao);
	public void buildProcedimento (String procedimento);
	public void buildExames(String exames);
	public void buildEvolucao (String evolucao);
	
	Prontuario buildProntuario();
}
