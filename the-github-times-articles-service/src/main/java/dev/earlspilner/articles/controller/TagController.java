package dev.earlspilner.articles.controller;

import dev.earlspilner.articles.dto.TagDto;
import dev.earlspilner.articles.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<TagDto> addTag(@RequestBody TagDto tagDto) {
        return new ResponseEntity<>(tagService.addTag(tagDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> getTag(@PathVariable Integer id) {
        return new ResponseEntity<>(tagService.getTag(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TagDto>> getTags(Pageable pageable) {
        return new ResponseEntity<>(tagService.getTags(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDto> updateTag(@PathVariable Integer id, @RequestBody TagDto tagDto) {
        return new ResponseEntity<>(tagService.updateTag(id, tagDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TagDto> deleteTag(@PathVariable Integer id) {
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
