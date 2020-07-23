package br.com.startideia.vuttr.mapper;

import br.com.startideia.vuttr.dto.TagDto;
import br.com.startideia.vuttr.model.Tag;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {

    TagMapper MAPPER = Mappers.getMapper(TagMapper.class);

    Tag toTag(TagDto tagDto);

    @InheritInverseConfiguration
    TagDto fromTag(Tag tag);

}
