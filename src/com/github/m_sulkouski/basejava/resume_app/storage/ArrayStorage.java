package com.github.m_sulkouski.basejava.resume_app.storage;

import com.github.m_sulkouski.basejava.resume_app.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void removeResume(int replacementIndex) {
        resumeStorage[replacementIndex] = resumeStorage[resumeCounter - 1];
        resumeStorage[resumeCounter - 1] = null;
    }

    @Override
    protected void addResume(int replacementIndex, Resume resume) {
        resumeStorage[resumeCounter] = resume;
    }

    @Override
    protected int findResumeIndex(String uuid) {
        int resumeIndex = 0;
        while (resumeIndex < resumeCounter) {
            if (resumeStorage[resumeIndex].getUuid().equals(uuid)) {
                return resumeIndex;
            }
            resumeIndex++;
        }
        return -1;
    }
}