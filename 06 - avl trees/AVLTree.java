package comp210.assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    private int balance(){
        if(isEmpty()){
            return 0;
        }
        return _left.height() - _right.height();
    }

    private AVLTree<T> rebalance() {
        _height = Math.max(_left.height(), _right.height())+1;
        int balance = balance();
        if(balance < -1) {
            if (_right.balance() > 0) {
                _right = this._right.rotateRight();
            }
            return this.rotateLeft();
        }
        if(balance > 1) {
            if (_left.balance() < 0) {
                _left = _left.rotateLeft();
            }
            return this.rotateRight();
        }
        return this;
    }

    private AVLTree<T> rotateLeft() {
        AVLTree<T> yy = _right;
        AVLTree<T> zz = yy._left;
        
        yy._left = this;
        _right = zz;
        _height = Math.max(_left.height(), _right.height())+1;
        _size = _left._size + _right._size + 1;
        
        yy._height = Math.max(yy._left.height(), yy._right.height())+1;
        yy._size = yy._left._size + yy._right._size + 1;
        
        return yy;
    }

    private AVLTree<T> rotateRight() {
        AVLTree<T> xx = _left;
        AVLTree<T> zz = xx._right;
        
        xx._right = this;
        _left = zz;
        _height = Math.max(_left.height(), _right.height())+1;
        _size = _left._size + _right._size + 1;
        
        xx._height = Math.max(xx._left.height(), xx._right.height())+1;
        xx._size = xx._left._size + xx._right._size + 1;

        return xx;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        if(isEmpty()) {
                _value = element;
                _left = new AVLTree<T>();
                _right = new AVLTree<T>();
                _size++;
                _height = Math.max(_left.height(), _right.height()) + 1;
        }
        else if(element.compareTo(getValue()) >= 0) {
            _right = (AVLTree<T>) _right.insert(element);
            _height = Math.max(_left.height(), _right.height()) + 1;
            _size++;
        }
        else{
                _left = (AVLTree<T>) _left.insert(element);
            _height = Math.max(_left.height(), _right.height()) + 1;
            _size++;
            }

        return rebalance();
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        if(_value == null){
            return this;
        }
        
        if (element.compareTo(_value) == 0) {
            if (_left.isEmpty() && _right.isEmpty()) {
                return new AVLTree<T>();
            } else if (_left.isEmpty()) {
                return _right;
            } else if (_right.isEmpty()) {
                return _left;
            } else {
                T successor = _right.findMin();
                _right = (AVLTree<T>) _right.remove(successor);
                _value = successor;
            }
        }
        else if(element.compareTo(_value) < 0){
            _left = (AVLTree<T>) _left.remove(element);
        }
        else if(element.compareTo(_value) > 0) {
            _right = (AVLTree<T>) _right.remove(element);
        }
        
        _height = Math.max(_left.height(), _right.height()) + 1;
        _size = _left._size + _right._size + 1;
        
        return rebalance();
    }

    @Override
    public T findMin() {
        AVLTree<T> current = this;
        if(current.isEmpty()){
            return null;
        }
        
        while (!current._left.isEmpty())
        {
            current = current._left;
        }
        
        return current._value;
    }

    @Override
    public T findMax() {
        AVLTree<T> current = this;
        if(current.isEmpty()){
            return null;
        }
       
        while (!current._right.isEmpty())
        {
            current = current._right;
        }
        
        return current._value;
    }

    @Override
    public boolean contains(T element) {
       if(isEmpty()) {
           return false;
       }
       else if(_value.compareTo(element) == 0){
           return true;
       }
       else if(_value.compareTo(element) > 0) {
           return _left.contains(element);
       }
       else {
           return _right.contains(element);
       }
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }

        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

        return _right;
    }
}
