package com.jonaschagas.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonaschagas.domain.AtrasoPagamento;
import com.jonaschagas.domain.ContaDespesa;
import com.jonaschagas.dto.ContaDespesaDTO;
import com.jonaschagas.dto.ContaDespesaNewDTO;
import com.jonaschagas.repository.AtrasoPagamentoRepository;
import com.jonaschagas.repository.ContaDespesaRepository;

@Service
public class ContaDespesaService {

	@Autowired
	ContaDespesaRepository contaDespesaRepository;

	@Autowired
	AtrasoPagamentoRepository atrasoPagamentoRepository;

	@Autowired
	AtrasoPagamentoService atrasoPagamentoService;

	/**
	 * metodo listar contas de desepesa. O metodo utiliza o padrao DTO, onde expõe
	 * somente as informações solicitadas pelo cliente.
	 * 
	 * @return
	 */
	public List<ContaDespesaDTO> listar() {

		List<ContaDespesa> list = new ArrayList<>();
		List<ContaDespesaDTO> contasDTO = new ArrayList<>();

		list = contaDespesaRepository.findAll();

		for (ContaDespesa conta : list) {
			
			double valorMultaJuro = 0.00;
			int dias = 0;
			
			Optional<AtrasoPagamento> atraso = atrasoPagamentoRepository.findByConta(conta);
			if (atraso.isPresent()) {
				valorMultaJuro = calculaAtraso(atraso.get(), conta.getValorOriginal());
				dias = atraso.get().getDiasAtraso();
			}
			ContaDespesaDTO contaDTO = new ContaDespesaDTO(conta, dias, 
					(conta.getValorOriginal() + valorMultaJuro));
			contasDTO.add(contaDTO);
		}
		return contasDTO;
	}

	/**
	 * metodo de calcular atraso de pagamento. O metodo recebe por parametro as
	 * informações relacionadas ao calculo do atraso, realiza e retorna o resultado.
	 * 
	 * @param atraso
	 * @param valorOriginal
	 * @return
	 */
	public Double calculaAtraso(AtrasoPagamento atraso, double valorOriginal) {
		BigDecimal bd;
		double calculoJuros = 0;
		double calculoMulta = 0;

		for (int i = 0; i < atraso.getDiasAtraso(); i++) {
			calculoJuros += (valorOriginal * atraso.getTipoCalculo().getJuros()) / 100;
		}
		calculoMulta = (valorOriginal * atraso.getTipoCalculo().getMulta()) / 100;
		bd = new BigDecimal(calculoJuros + calculoMulta);
		return bd.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
	}

	/**
	 * metodo que recebe uma contaDespesaDTO e transforma em objeto ContaDespesa
	 * 
	 * @param contaDespesaNewDTO
	 * @return
	 */
	public ContaDespesa fromDTO(ContaDespesaNewDTO contaDespesaNewDTO) {
		return new ContaDespesa(contaDespesaNewDTO.getNome(), contaDespesaNewDTO.getValorOriginal(),
				contaDespesaNewDTO.getDataVencimento(), contaDespesaNewDTO.getDataPagamento());
	}

	/**
	 * metodo que salva no banco de dados uma conta e verifica se esta atrasada. Se
	 * esta encaminha a conta para AtrasoPagamentoService construir as regras de
	 * atraso.
	 * 
	 * @param conta
	 * @return
	 */
	@Transactional
	public ContaDespesa salvar(ContaDespesa conta) {

		conta = contaDespesaRepository.save(conta);
		if (conta.getDataPagamento().after(conta.getDataVencimento())) {
			AtrasoPagamento atraso = atrasoPagamentoService.constroiRegraAtraso(conta);
			atraso.setConta(conta);
			atrasoPagamentoRepository.save(atraso);
		}
		return conta;
	}
}
