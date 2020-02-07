package com.github.m_sulkouski.basejava.resume_app.storage;

import com.github.m_sulkouski.basejava.resume_app.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void update(Resume resume) {
        int index = findResumeIndex(resume.getUuid());
        if (index >= 0) {
            resumeStorage[index] = resume;
        } else {
            System.out.println("Resume with uuid \"" + resume.getUuid() + "\" not found.");
        }
    }

    @Override
    public void save(Resume resume) {
        if (resumeCounter >= STORAGE_LIMIT) {
            System.out.println("Error saving resume to database. Resume database is already full!");
        } else {
            int index = findResumeIndex(resume.getUuid());
            if (index >= 0) {
                System.out.println("Resume with uuid \"" + resume.getUuid() + "\" already exists.");
            } else {
                index = Math.abs(index) - 1;
                resumeCounter++;
                int i;
                for (i = resumeCounter; i > 0 || i > index; i--) {
                    resumeStorage[i] = resumeStorage[i - 1];
                }
                resumeStorage[index] = resume;
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid \"" + uuid + "\" not found.");
        } else {
            resumeCounter--;
            resumeStorage[index] = resumeStorage[index + 1];
            if (index < resumeCounter - 1) {
                for (int i = index; i < resumeCounter; i++) {
                    resumeStorage[index] = resumeStorage[index + 1];
                }
            }
            resumeStorage[resumeCounter] = null;
        }
    }

    @Override
    protected int findResumeIndex(String uuid) {
        return Arrays.binarySearch(resumeStorage, 0, resumeCounter, new Resume(uuid));
    }
}
