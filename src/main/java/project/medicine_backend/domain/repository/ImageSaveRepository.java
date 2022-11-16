package project.medicine_backend.domain.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ImageSaveRepository {

    private static Map<Long, String> store = new HashMap<>(); //static 사용

    private static long sequence = 0L; //static 사용

    public long save(String imgName) {
        store.put(++sequence, imgName);
        return sequence;
    }

    public String findById(Long id) {
        return store.get(id);
    }

    public String findId(long paramId) {
        return store.get(paramId);
    }

    public List<String> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}