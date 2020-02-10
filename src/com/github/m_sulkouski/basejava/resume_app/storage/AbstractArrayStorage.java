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

    public int size() {
        return resumeCounter;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(resumeStorage, resumeCounter);
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index >= 0) {
            return resumeStorage[index];
        }
        System.out.println("Resume with uuid \"" + uuid + "\" not found.");
        return null;
    }

    public void update(Resume resume) {
        int index = findResumeIndex(resume.getUuid());
        if (index >= 0) {
            resumeStorage[index] = resume;
            System.out.println("Resume with uuid \"" + resume.getUuid() + " has been successfully updated.");
        } else {
            System.out.println("Resume with uuid \"" + resume.getUuid() + "\" not found.");
        }
    }

    public void delete(String uuid) {
        if (resumeCounter <= 0) {
            System.out.println("Error deleting entry from database. Resume database is empty!");
        } else {
            int resumeIndex = findResumeIndex(uuid);
            if (resumeIndex >= 0) {
                removeResume(resumeIndex);
            } else {
                System.out.println("Resume with uuid \"" + uuid + "\" not found.");
            }
        }
    }

    public void save(Resume resume) {
        if (resumeCounter >= STORAGE_LIMIT) {
            System.out.println("Error saving resume to database. Resume database is already full!");
        } else {
            int replacementIndex = findResumeIndex(resume.getUuid());
            if (replacementIndex >= 0 && resumeStorage[replacementIndex] != null) {
                System.out.println("Resume with uuid \"" + resume.getUuid() + "\" already exists.");
            } else {
                addResume(replacementIndex, resume);
            }
        }
    }

    protected abstract void removeResume(int replacementIndex);

    protected abstract void addResume(int replacementIndex, Resume resume);

    protected abstract int findResumeIndex(String uuid);
}
