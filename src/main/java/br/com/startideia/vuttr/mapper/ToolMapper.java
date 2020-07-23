package br.com.startideia.vuttr.mapper;

import br.com.startideia.vuttr.dto.ToolDto;
import br.com.startideia.vuttr.model.Tag;
import br.com.startideia.vuttr.model.Tool;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = { TagMapper.class })
public interface ToolMapper {

    ToolMapper MAPPER = Mappers.getMapper(ToolMapper.class);

    Tool toTool(ToolDto toolDto);

    ToolDto fromTool(Tool tool);

    List<Tool> toToolList(List<ToolDto> toolDtoList);
    List<ToolDto> fromToolList(List<Tool> toolList);

    default List<String> fromTags(List<Tag> tags) {
        List<String> tagsString = new ArrayList<>();

        for (Tag tag: tags) {
            tagsString.add(tag.getName());
        }

        return tagsString;
    }

    default List<Tag> toTags(List<String> tagsString) {
        List<Tag> tags = new ArrayList<>();

        for (String name: tagsString) {
            Tag t = new Tag();
            t.setName(name);
            tags.add(t);
        }

        return tags;
    }

}
