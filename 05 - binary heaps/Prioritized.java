package comp210.assn05;

public interface Prioritized<V,P extends Comparable<P>> {
    
    V getValue();
    P getPriority();
}
