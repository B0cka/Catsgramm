package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


// Указываем, что класс PostService - является бином и его
// нужно добавить в контекст приложения
@Service
public class PostService {
    private final Map<Long, Post> posts = new HashMap<>();


    public Collection<Post> findAll(int size, int from, String sort) {
        if (size <= 0 || from < 0) {
            throw new ConditionsNotMetException("Параметры size и from должны быть положительными");
        }

        return posts.values().stream()

                .sorted((p1, p2) ->
                        sort.equalsIgnoreCase("asc") ?
                                p1.getPostDate().compareTo(p2.getPostDate()) :
                                p2.getPostDate().compareTo(p1.getPostDate())
                )
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());
    }

    @GetMapping("/posts/{postId}")
    public Post findById(@PathVariable long postId) {
        Post post = posts.get(postId);
        if (post == null) {
            throw new NotFoundException("Юзер с id = " + postId + " не найден");
        }
        return post;
    }

    public Post create(Post post) {
        if (post.getDescription() == null || post.getDescription().isBlank()) {
            throw new ConditionsNotMetException("Описание не может быть пустым");
        }

        post.setId(getNextId());
        post.setPostDate(LocalDate.now());
        posts.put(post.getId(), post);
        return post;
    }

    public Post update(Post newPost) {
        if (newPost.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        if (posts.containsKey(newPost.getId())) {
            Post oldPost = posts.get(newPost.getId());
            if (newPost.getDescription() == null || newPost.getDescription().isBlank()) {
                throw new ConditionsNotMetException("Описание не может быть пустым");
            }
            oldPost.setDescription(newPost.getDescription());
            return oldPost;
        }
        throw new NotFoundException("Пост с id = " + newPost.getId() + " не найден");
    }

    private long getNextId() {
        long currentMaxId = posts.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}