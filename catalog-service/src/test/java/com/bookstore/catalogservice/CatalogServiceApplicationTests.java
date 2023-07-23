package com.bookstore.catalogservice;

import com.bookstore.catalogservice.repository.BookRepository;
import com.bookstore.catalogservice.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//@SpringBootTest
//@Testcontainers
@ExtendWith(MockitoExtension.class)
class CatalogServiceApplicationTests {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookServiceImpl;

//    @BeforeEach
//    void setupService() {
//        bookRepository = mock(BookRepository.class);
//        bookService = new BookService(bookRepository);
//    }

//    @Container
//    static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres");

    @Test
    void shouldCreateBook() {
//        Book book = new Book("121", "Oksss", "OOO", BigDecimal.valueOf(12));
//        lenient().when(bookRepository.findById("121")).thenReturn(Optional.of(book));
//
//        BookRequest bookRequest = new BookRequest("121", "Oksss", "OOO", BigDecimal.valueOf(12));
//        bookServiceImpl.createBook(bookRequest);
    }

}
