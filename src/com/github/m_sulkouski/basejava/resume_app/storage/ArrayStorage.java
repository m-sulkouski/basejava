package com.github.m_sulkouski.basejava.resume_app.storage;

import com.github.m_sulkouski.basejava.resume_app.model.Resume;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int resumeCounter = 0;
    private final Resume[] resumeStorage = new Resume[10000];

    public void clear() {
        Arrays.fill(resumeStorage, null);
    }

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

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            return resumeStorage[index];
        }
        System.out.println("Resume with uuid \"" + uuid + "\" not found.");
        return null;
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

    public void update(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            System.out.println("Please enter new uuid: ");
            Scanner input = new Scanner(System.in);
            String updatedUUID = "";
            while (updatedUUID.equals("")) {                             // Make sure updated uuid is not empty string
                updatedUUID = input.nextLine();
            }
            resumeStorage[index] = new Resume(updatedUUID);
        } else {
            System.out.println("Resume with uuid \"" + uuid + "\" not found.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(resumeStorage, resumeCounter);
    }

    public int size() {
        return resumeCounter;
    }

    private int findResumeIndex(String uuid) {
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
