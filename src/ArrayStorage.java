/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int resumeCounter = 0;
    private Resume[] resumeStorage = new Resume[1000];

    void clear() {
        int counter = 0;
        while (counter < resumeCounter) {
            resumeStorage[counter] = null;
            counter++;
        }
        resumeCounter = 0;
    }

    void save(Resume resume) {
        resumeStorage[this.resumeCounter] = resume;
        this.resumeCounter++;
        sortResumeStorage();
    }

    Resume get(String uuid) {
        int index = findResume(uuid);
        if (index != -1)
            return resumeStorage[index];
        return null;
    }

    void delete(String uuid) {
        int index = findResume(uuid);
        if (index != -1) {
            resumeStorage[index] = null;
            sortResumeStorage();
            resumeCounter--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[resumeCounter];
        int counter = 0;
        while (resumeStorage[counter] != null) {
            resumes[counter] = resumeStorage[counter];
            counter++;
        }

        return resumes;
    }

    int size() {
        return resumeCounter;
    }

    private int findResume(String uuid) {
        int counter = 0;
        while (counter < this.resumeCounter) {
            if (resumeStorage[counter].getUuid().equals(uuid)) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    private void sortResumeStorage() {
        Resume[] processedResumeStorage = new Resume[1000];
        int processedIndex = 0;
        for (int i = 0; i < resumeCounter; i++, processedIndex++) {
            if (resumeStorage[i] == null) {
                if (i < resumeCounter - 1)
                    i++;
                else {
                    break;
                }
            }
            processedResumeStorage[processedIndex] = resumeStorage[i];
        }
        this.resumeStorage = processedResumeStorage;
    }
}
