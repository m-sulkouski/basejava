package com.github.m_sulkouski.basejava.resume_app.storage;

import com.github.m_sulkouski.basejava.resume_app.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void shiftResumeStorage(int replacementIndex, Resume resume) {
        if (resume == null) {
            resumeStorage[replacementIndex] = resumeStorage[resumeCounter - 1];
            resumeStorage[resumeCounter - 1] = null;
            resumeCounter--;
        } else {
            resumeStorage[resumeCounter] = resumeStorage[replacementIndex];
            resumeStorage[replacementIndex] = resume;
            resumeCounter++;
        }
    }

    protected int findResumeIndex(String uuid) {
        int resumeIndex = 0;
        while (resumeIndex < resumeCounter) {
            if (resumeStorage[resumeIndex].getUuid().equals(uuid)) {
                return resumeIndex;
            }
            resumeIndex++;
        }
        return -resumeIndex - 1;
    }
}