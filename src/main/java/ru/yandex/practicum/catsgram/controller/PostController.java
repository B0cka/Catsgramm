package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Collection<Post> findAll(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "desc") String sort) {

        if (sort == null) {
            throw new ParameterNotValidException("sort", "\"Некорректный размер выборки. Сортировка должна содержать корректное значение\"");
        }
        if (size <= 0) {
            throw new ParameterNotValidException("size", "Некорректный размер выборки. Размер должен быть больше нуля\"");
        }
        if (from >= 0) {
            throw new ParameterNotValidException("size", "\"Некорректный размер выборки. Размер должен быть больше нуля\"");
        }

        return postService.findAll(size, from, sort);
    }


    @GetMapping("/{postId}")
    public Post findById(@PathVariable long postId) {
        return postService.findById(postId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public Post update(@RequestBody Post post) {
        return postService.update(post);
    }
}
