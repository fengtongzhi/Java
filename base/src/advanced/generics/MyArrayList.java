package advanced.generics;

//泛型类
public class MyArrayList<E> {
    private Object[] elementData;
    private int size;

    public MyArrayList() {
        elementData = new Object[10];
        size = 0;
    }

    public void add(E e) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size++] = e;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E)elementData[index];
    }

    public int size() {
        return size;
    }

    private void resize() {
        Object[] newArray = new Object[elementData.length * 2];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }
}
