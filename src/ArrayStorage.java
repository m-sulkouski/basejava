/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] resumeStorage = new Resume[10000];
    private int resumeCounter = 0;

    void clear() {
    }

    void save(Resume resume) {
        resumeStorage[this.resumeCounter] = resume;
        this.resumeCounter++;
        cleanResumeStorage();
    }

    Resume get(String uuid) {
        int index = findResume(uuid);
        if (index != -1)
            return resumeStorage[index];
        return null;
    }

    void delete(String uuid) {
        int index = findResume(uuid);
        if (index != -1)

            ;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return this.resumeStorage;
    }

    int size() {
        return 0;
    }

    private int findResume (String uuid) {
        int counter = 0;
        while (counter < this.resumeCounter) {
            if (resumeStorage[counter].getUuid().equals(uuid)) {
                return counter;
            }
        }
        return -1;
    }

    private void cleanResumeStorage() {
        Resume[] processedResumeStorage = new Resume[this.resumeCounter];
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
