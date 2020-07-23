package br.com.startideia.vuttr.controller;

import br.com.startideia.vuttr.dto.ToolDto;
import br.com.startideia.vuttr.mapper.ToolMapper;
import br.com.startideia.vuttr.model.Tag;
import br.com.startideia.vuttr.model.Tool;
import br.com.startideia.vuttr.service.TagService;
import br.com.startideia.vuttr.service.ToolService;
import br.com.startideia.vuttr.util.VerificadorUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/tools")
public class ToolController {

    private ToolService toolService;
    private TagService tagService;

    public ToolController(ToolService toolService, TagService tagService) {
        this.toolService = toolService;
        this.tagService = tagService;
    }

    @Operation(summary = "Return a tool data identified by id")
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ToolDto findById(@PathVariable("id") Long id) throws Exception {
        Tool tool = toolService.findById(id);
        ToolDto toolDto = ToolMapper.MAPPER.fromTool(tool);
        toolDto.add(linkTo(methodOn(ToolController.class).findById(id)).withSelfRel());
        return toolDto;
    }

    @Operation(summary = "Return all tools and it's data")
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<List<ToolDto>> findAll(@RequestParam(value="tag", required=false) String tag) {
        List<Tool> allTools = toolService.findAllOrByTag(tag);

        List<ToolDto> toolDtoList = ToolMapper.MAPPER.fromToolList(allTools);

        return new ResponseEntity<>(toolDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Return paged tools and it's data")
    @GetMapping(path = "/page", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedModel<Tool>> findAllPageable(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "limit", defaultValue = "12") int limit,
                                                    @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                    PagedResourcesAssembler assembler) {
        var sortDirection = "desc".equalsIgnoreCase((direction)) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, sortDirection, "title");

        Page<Tool> tools = toolService.findAll(pageable);
        tools.stream().forEach(f -> {
            try {
                f.add(linkTo(methodOn(ToolController.class)
                        .findById(f.getId())).withSelfRel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new ResponseEntity<>(assembler.toModel(tools), HttpStatus.OK);
    }

    @Operation(summary = "Insert tool data")
    @PostMapping(produces = {"application/hal+json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<ToolDto> create(@Valid @RequestBody ToolDto toolDto) throws Exception {
        Tool tool = convertToEntity(toolDto);
        tool = toolService.create(tool);
        ToolDto toolDtoCreated = ToolMapper.MAPPER.fromTool(tool);
        toolDtoCreated.add(linkTo(methodOn(ToolController.class).findById(tool.getId())).withSelfRel());
        return new ResponseEntity<>(toolDtoCreated, HttpStatus.CREATED);
    }

    @Operation(summary = "Update tool data")
    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ToolDto update(@Valid @RequestBody ToolDto toolDto) throws Exception {
        Tool tool = convertToEntity(toolDto);
        tool = toolService.update(tool);
        ToolDto toolDtoUpdated = ToolMapper.MAPPER.fromTool(tool);
        toolDtoUpdated.add(linkTo(methodOn(ToolController.class).findById(tool.getId())).withSelfRel());
        return toolDtoUpdated;
    }

    @Operation(summary = "Delete a tool by id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws Exception {
        toolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Tool convertToEntity(ToolDto toolDto) {
        Tool tool = ToolMapper.MAPPER.toTool(toolDto);
        List<Tag> tags = new ArrayList<>();

        for (Tag tag: tool.getTags()) {
            Tag entity = tagService.findByName(tag.getName());

            if (VerificadorUtil.naoEstaNuloOuVazio(entity)) {
                tags.add(entity);
            } else {
                tags.add(tag);
            }
        }

        tool.setTags(tags);

        return tool;
    }

}
