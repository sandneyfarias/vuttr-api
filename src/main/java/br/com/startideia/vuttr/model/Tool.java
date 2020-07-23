package br.com.startideia.vuttr.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Tool extends RepresentationModel<Tool> implements Serializable {

    private static final long serialVersionUID = 5885629913717631391L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_id")
    private Long id;

    @EqualsAndHashCode.Include
    @Size(min = 1, max = 200, message = "Tool title length must be between {min} and {max}")
    @NotBlank(message = "Tool title cannot be empty.")
    private String title;

    @EqualsAndHashCode.Include
    @Column(length = 200)
    @Size(min = 1, max = 200, message = "Tool link length must be between {min} and {max}")
    @NotBlank(message = "Tool link cannot be empty")
    @URL(regexp = "^(http|ftp|htps).*", message = "Please insert a valida link!")
    private String link;

    @Column(length = 200)
    @NotBlank(message = "Tool description cannot be empty")
    private String description;

    @NotEmpty(message = "Tool tag list cannot be empty.")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tool_tag",
            joinColumns = {@JoinColumn(name = "tool_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;

}
