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

    private static Map<Long, Long> store = new HashMap<>(); //static 사용

    private static long sequence = 0L; //static 사용

    public static Long save(Long imgName) {
        store.put(++sequence, imgName);
        return sequence;
    }

    public Long findById(Long id) {
        return store.get(id);
    }

    public static Long findId(Long paramId) {
        return store.get(paramId);
    }

    public List<Long> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}