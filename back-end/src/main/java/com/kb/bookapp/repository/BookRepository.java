package com.kb.bookapp.repository;

import com.kb.bookapp.business.component.UserApi.UserBook;
import com.kb.bookapp.repository.custom.CustomBookRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<UserBook, Long>, CustomBookRepository {
}
