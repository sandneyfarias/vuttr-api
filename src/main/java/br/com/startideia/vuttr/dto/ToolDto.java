package br.com.startideia.vuttr.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@JsonPropertyOrder({"title", "link","description", "tags", "id" })
public class ToolDto extends RepresentationModel<ToolDto> implements Serializable {

    private static final long serialVersionUID = -378900554659242550L;

    private Long id;
    @NotNull(message = "Tool title cannot be empty.")
    private String title;
    @NotNull(message = "Tool link cannot be empty.")
    @URL(regexp = "^(http|ftp|htps).*", message = "Please insert a valid link with protocol: http, https or ftp!")
    private String link;
    @NotNull(message = "Tool description cannot be empty.")
    private String description;
    @NotNull(message = "Tool tag list cannot be empty.")
    private List<String> tags;

}
