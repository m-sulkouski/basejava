package com.github.m_sulkouski.basejava.resume_app.storage;

import com.github.m_sulkouski.basejava.resume_app.model.Resume;

public interface Storage {
    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(Resume resume);

    Resume[] getAll();

    int size();
}
