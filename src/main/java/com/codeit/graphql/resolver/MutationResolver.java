package com.codeit.graphql.resolver;

import com.codeit.graphql.entity.Author;
import com.codeit.graphql.entity.Book;
import com.codeit.graphql.input.CreateAuthorInput;
import com.codeit.graphql.input.CreateBookInput;
import com.codeit.graphql.input.UpdateAuthorInput;
import com.codeit.graphql.input.UpdateBookInput;
import com.codeit.graphql.service.AuthorService;
import com.codeit.graphql.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

// Mutation Resolver는 데이터 변경을 처리합니다.
@Controller
@RequiredArgsConstructor
@Slf4j
public class MutationResolver {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookSubscriptionResolver subscriptionResolver;

    /**
     * 도서 생성
     */
    @MutationMapping
    public Book createBook(@Argument CreateBookInput input) {
        log.info("GraphQL Mutation: createBook({})", input);
        Book book = bookService.createBook(input);

        // 이벤트 발행
        subscriptionResolver.publishBookAdded(book);

        return book;
    }

    /**
     * 도서 수정
     */
    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument UpdateBookInput input) {
        log.info("GraphQL Mutation: updateBook(id={}, {})", id, input);
        return bookService.updateBook(id, input);
    }

    /**
     * 도서 삭제
     */
    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        log.info("GraphQL Mutation: deleteBook(id={})", id);
        return bookService.deleteBook(id);
    }

    /**
     * 저자 생성
     */
    @MutationMapping
    public Author createAuthor(@Argument CreateAuthorInput input) {
        log.info("GraphQL Mutation: createAuthor({})", input);
        return authorService.createAuthor(input);
    }

    /**
     * 저자 수정
     */
    @MutationMapping
    public Author updateAuthor(@Argument Long id, @Argument UpdateAuthorInput input) {
        log.info("GraphQL Mutation: updateAuthor(id={}, {})", id, input);
        return authorService.updateAuthor(id, input);
    }

    /**
     * 저자 삭제
     */
    @MutationMapping
    public Boolean deleteAuthor(@Argument Long id) {
        log.info("GraphQL Mutation: deleteAuthor(id={})", id);
        return authorService.deleteAuthor(id);
    }


}












