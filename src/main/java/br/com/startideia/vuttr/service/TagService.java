package br.com.startideia.vuttr.service;

import br.com.startideia.vuttr.model.Tag;
import br.com.startideia.vuttr.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }

}
