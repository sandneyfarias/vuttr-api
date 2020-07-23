package br.com.startideia.vuttr.dto;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TagDto extends RepresentationModel<TagDto> implements Serializable {

    @NotNull
    private String name;

}
