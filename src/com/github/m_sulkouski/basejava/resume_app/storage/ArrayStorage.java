package com.github.m_sulkouski.basejava.resume_app.storage;

import com.github.m_sulkouski.basejava.resume_app.model.Resume;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {


    public void save(Resume resume) {
        if (findResumeIndex(resume.getUuid()) != -1) {
            System.out.println("Resume with uuid \"" + resume.getUuid() + "\" already exists.");
        } else if (resumeCounter >= resumeStorage.length) {
            System.out.println("Error saving resume to database. Resume database is already full!");
        } else {
            resumeStorage[resumeCounter] = resume;
            resumeCounter++;
        }
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            resumeStorage[index] = resumeStorage[resumeCounter - 1];
            resumeStorage[resumeCounter - 1] = null;
            resumeCounter--;
        } else {
            System.out.println("Resume with uuid \"" + uuid + "\" not found.");
        }
    }

    public void update(Resume resume) {
        int index = findResumeIndex(resume.getUuid());
        if (index != -1) {
            resumeStorage[index] = resume;
        } else {
            System.out.println("Resume with uuid \"" + resume.getUuid() + "\" not found.");
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
        return -1;
    }
}