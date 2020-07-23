package br.com.startideia.vuttr.service;

import br.com.startideia.vuttr.error.DuplicatedResourceException;
import br.com.startideia.vuttr.error.ResourceNotFoundException;
import br.com.startideia.vuttr.model.Tool;
import br.com.startideia.vuttr.repository.ToolRepository;
import br.com.startideia.vuttr.util.VerificadorUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolService {

    private ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public Tool findById(Long id) {
        var entity = toolRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return entity;
    }

    public Tool create(Tool tool) throws Exception {
        validateCreateOrUpdate(tool);
        return toolRepository.save(tool);
    }

    public Tool update(Tool tool) {
        var entity = toolRepository.findById(tool.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        tool.setId(entity.getId());

        return toolRepository.save(tool);
    }

    public void deleteById(Long id) throws Exception {
        toolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        toolRepository.deleteById(id);
    }

    public List<Tool> findAllOrByTag(String tag) {
        if (VerificadorUtil.estaNuloOuVazio(tag)) {
            return findAll();
        }
        return toolRepository.finAlldByTag(tag);
    }

    public Page<Tool> findAll(Pageable pageable) {
        return toolRepository.findAll(pageable);
    }

    public List<Tool> findAll() {
        return toolRepository.findAllByOrderByTitle();
    }

    private boolean isExist(Tool tool) {
        Long id = tool.getId();

        if (VerificadorUtil.estaNulo(tool.getId())) {
            id = 0l;
        }

        Optional<Tool> entity = toolRepository.findByTitleOrLinkAndIdNot(tool.getTitle(), tool.getLink(), id);

        return entity.isPresent();
    }

    private void validateCreateOrUpdate(Tool tool) {
        if (isExist(tool)) {
            throw new DuplicatedResourceException("The tool informed already exist");
        }
    }

}
