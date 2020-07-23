package br.com.startideia.vuttr.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Tag  extends RepresentationModel<Tool> implements Serializable {

    private static final long serialVersionUID = -2842425318528732512L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @EqualsAndHashCode.Include
    @NotBlank(message = "Tag name cannot be empty.")
    @Column(unique=true, length = 100)
    @Size(min = 1, max = 100, message = "Tag name length must be between {min} and {max}")
    private String name;

    public Tag(String name) {
        this.name = name;
    }

}
