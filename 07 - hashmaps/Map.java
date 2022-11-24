package comp210.assn07;

import java.util.List;
import java.util.Set;

public interface Map <K, V> {

    void put(K key, V value);
    V get(K key);
    int size();
    Set<K> keySet();
    V remove(K key);

    List<K> checkDuplicate(V value);
    boolean checkMasterPassword(String enteredPassword);
    String generateRandomPassword(int length);
}
