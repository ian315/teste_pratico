package com.frota.teste_pratico.service;

import com.frota.teste_pratico.dto.veiculo_pneu.InsertPneuNoVeiculoValidandoPosicaoResponse;
import com.frota.teste_pratico.dto.veiculo_pneu.InsertPneuVeiculoRequest;
import com.frota.teste_pratico.dto.veiculo_pneu.RemovePneuFromVeiculoRequest;
import com.frota.teste_pratico.mapper.PneuMapper;
import com.frota.teste_pratico.mapper.VeiculoMapper;
import com.frota.teste_pratico.model.entities.Pneu;
import com.frota.teste_pratico.model.entities.Veiculo;
import com.frota.teste_pratico.model.entities.VeiculoPneu;
import com.frota.teste_pratico.repository.PneuRepository;
import com.frota.teste_pratico.repository.VeiculoPneuRepository;
import com.frota.teste_pratico.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


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
    public InsertPneuNoVeiculoValidandoPosicaoResponse insertPneuVeiculoComPosicao(InsertPneuVeiculoRequest request) {

        Veiculo veiculo = veiculoRepository.findById(request.getVeiculoId()).orElseThrow(()->  new DataIntegrityViolationException("Veiculo não existe"));
        Pneu pneu = pneuRepository.findById(request.getPneuId()).orElseThrow(() -> new DataIntegrityViolationException("Pneu não existe"));

        if (veiculo.getQuantidadeDePneus() < request.getPosicao()) {
            throw new DataIntegrityViolationException("Posição Inválida");
        }

        if (veiculoPneuRepository.findByPosicao(request.getPosicao()).isEmpty()) {
            throw  new DataIntegrityViolationException("Posicao não existe");
        }

        if (veiculoPneuRepository.findByVeiculoIdAndPosicao(request.getVeiculoId(), request.getPosicao()).isPresent()) {
            throw  new DataIntegrityViolationException("Ja existe um pneu nessa posição");
        }

        VeiculoPneu veiculoPneu = new VeiculoPneu(veiculo, pneu, request.getPosicao());
        veiculoPneuRepository.save(veiculoPneu);

        return new InsertPneuNoVeiculoValidandoPosicaoResponse(
                        veiculoMapper.toResponseFromInsertPneuEmVeiculo(veiculo),
                        pneuMapper.toInsertResponsePneuOnVeiculoFromEntity(pneu),
                        request.getPosicao());
    }

    @Transactional
    public void removePneuFromVeiculo(RemovePneuFromVeiculoRequest request) {

        veiculoPneuRepository.deleteByVeiculoIdAndPneuId(request.getVeiculoId(), request.getPneuId());
    }
}
