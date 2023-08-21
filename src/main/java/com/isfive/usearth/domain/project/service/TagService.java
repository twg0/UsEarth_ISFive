package com.isfive.usearth.domain.project.service;

import com.isfive.usearth.domain.project.repository.ProjectRepository;
import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.entity.Tag;
import com.isfive.usearth.domain.project.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    public List<String> createTagList(String hashTag) {
        List<String> hashList = Arrays.stream(hashTag.split("#"))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());

        return hashList;
    }

    public List<Tag> convertTagStrToEntity(List<String> hashList, Project project) {
        List<Tag> tagList = new ArrayList<>();
        for (String hash : hashList) {
            Tag tag = Tag.builder()
                    .name(hash)
                    .build();
            tag.setProject(project);
            tagList.add(tag);
        }
        tagRepository.saveAll(tagList);

        return tagList;
    }

}
