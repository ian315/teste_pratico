package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.veiculo_pneu.InserirPneuNoVeiculoValidandoPosicaoResponse;
import com.frota.teste_pratico.dto.veiculo_pneu.InserirPneuVeiculoRequest;
import com.frota.teste_pratico.dto.veiculo_pneu.RemoverPneuDoVeiculoRequest;
import com.frota.teste_pratico.mapper.PneuMapper;
import com.frota.teste_pratico.mapper.VeiculoMapper;
import com.frota.teste_pratico.model.entities.Pneu;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.model.entities.VeiculoPneu;
import com.frota.teste_pratico.model.exceptions.VeiculoPneuException;
import com.frota.teste_pratico.repository.PneuRepository;
import com.frota.teste_pratico.repository.VeiculoPneuRepository;
import com.frota.teste_pratico.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VeiculoPneuService {

    @Autowired
    private VeiculoMapper veiculoMapper;

    @Autowired
    private PneuMapper pneuMapper;

    @Autowired
    private VeiculoPneuRepository veiculoPneuRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private PneuRepository pneuRepository;

    @Transactional
    public InserirPneuNoVeiculoValidandoPosicaoResponse insertPneuVeiculoComPosicao(InserirPneuVeiculoRequest request) {

        Veiculo veiculo = veiculoRepository.findById(request.getVeiculoId()).orElseThrow(() -> new VeiculoPneuException("Veiculo não existe"));
        Pneu pneu = pneuRepository.findById(request.getPneuId()).orElseThrow(() -> new VeiculoPneuException("Pneu não existe"));

        if (veiculo.getQuantidadeDePneus() < request.getPosicao()) {
            throw new VeiculoPneuException("Posição Inválida");
        }

        if (veiculoPneuRepository.findByVeiculoIdAndPosicao(request.getVeiculoId(), request.getPosicao()).isPresent()) {
            throw new VeiculoPneuException("Ja existe um pneu nessa posição");
        }

        if (veiculoPneuRepository.findByPneuId(request.getPneuId()).isPresent()) {
            throw new VeiculoPneuException("Ja existe um veiculo com esse pneu");
        }

        VeiculoPneu veiculoPneu = new VeiculoPneu(veiculo, pneu, request.getPosicao());
        veiculoPneuRepository.save(veiculoPneu);

        return new InserirPneuNoVeiculoValidandoPosicaoResponse(
                        veiculoMapper.toResponseFromInsertPneuEmVeiculo(veiculo),
                        pneuMapper.toInsertResponsePneuOnVeiculoFromEntity(pneu),
                        request.getPosicao());
    }

    @Transactional
    public void removePneuFromVeiculo(RemoverPneuDoVeiculoRequest request) {

        if(veiculoPneuRepository.findByVeiculoIdAndPneuId(request.getVeiculoId(), request.getPneuId()).isEmpty())
            throw  new VeiculoPneuException("Esse veículo e pneu não estão vinculados");

        veiculoPneuRepository.deleteByVeiculoIdAndPneuId(request.getVeiculoId(), request.getPneuId());
    }
}
