package com.webproject.notebook.repo;

import com.webproject.notebook.models.Entry;
import org.springframework.data.repository.CrudRepository;

public interface EntryRepository extends CrudRepository<Entry, Long> {
}
