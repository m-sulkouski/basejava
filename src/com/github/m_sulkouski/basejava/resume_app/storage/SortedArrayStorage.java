package com.github.m_sulkouski.basejava.resume_app.storage;

import com.github.m_sulkouski.basejava.resume_app.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void removeResume(int replacementIndex) {
        if (resumeCounter + 1 - replacementIndex > 0) {
            System.arraycopy(resumeStorage, replacementIndex + 1, resumeStorage, replacementIndex, resumeCounter + 1 - replacementIndex);
        }
        resumeCounter--;
    }

    @Override
    protected void addResume(int replacementIndex, Resume resume) {
        replacementIndex = -replacementIndex - 1;
        if (resumeCounter - replacementIndex > 0) {
            System.arraycopy(resumeStorage, replacementIndex, resumeStorage, replacementIndex + 1, resumeCounter - replacementIndex);
        }
        resumeStorage[replacementIndex] = resume;
        resumeCounter++;
    }

    @Override
    protected int findResumeIndex(String uuid) {
        return Arrays.binarySearch(resumeStorage, 0, resumeCounter, new Resume(uuid));
    }
}
