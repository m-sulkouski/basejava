package com.github.m_sulkouski.basejava.resume_app.storage;

import com.github.m_sulkouski.basejava.resume_app.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] resumeStorage = new Resume[STORAGE_LIMIT];
    protected int resumeCounter = 0;

    public void clear() {
        Arrays.fill(resumeStorage, 0, resumeCounter, null);
        resumeCounter = 0;
    }

    public int size() {return resumeCounter;}

    public Resume[] getAll() {
        return Arrays.copyOf(resumeStorage, resumeCounter);
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            return resumeStorage[index];
        }
        System.out.println("Resume with uuid \"" + uuid + "\" not found.");
        return null;
    }

    abstract protected int findResumeIndex(String uuid);
}
