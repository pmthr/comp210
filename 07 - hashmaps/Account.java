package comp210.assn07;

public class Account <K,V> {

    private K _website;
    private V _password;
    private Account _next;

    public Account(K key, V value) {
        _website = key;
        _password = value;
        _next = null;
    }

    public K getWebsite() {
        return _website;
    }

    public void setWebsite(K website) {
        this._website = website;
    }

    public V getPassword() {
        return _password;
    }

    public void setPassword(V password) {
        this._password = password;
    }

    public Account getNext() {
        return _next;
    }

    public void setNext(Account next) {
        _next = next;
    }
}
