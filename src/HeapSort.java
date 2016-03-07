import java.util.Random;

public class HeapSort {

    //Количество элементов в массиве
    private static int heapSize = 10;

    public static void main(String[] args) {
        int[] array = new int[heapSize];
        Random random = new Random();

        System.out.println("Before sort:");

        //Генерация случайных чисел от 0 до 20
        for(int i = 0; i < heapSize; i++) {
            array[i] = random.nextInt(20);
            System.out.println("array[" + i + "] = " + array[i]);
        }

        sort(array);

        System.out.println("After sort:");
        for(int i = 0; i < array.length; i++) {
            System.out.println("array[" + i + "] = " + array[i]);
        }
    }

    /**
     * Сортировка с помощью кучи.
     * Сначала формируется куча:
     * Максимальный элемент массива находится в корне кучи. Его нужно
     * поменять местами с последним элементом и убрать из кучи (уменьшить
     * размер кучи на 1). Теперь в корне кучи находится элемент, который раньше
     * был последним в массиве. Нужно переупорядочить кучу так, чтобы
     * выполнялось основное условие кучи - array[parent]>=array[child]:
     * После этого в корне окажется максимальный из оставшихся элементов.
     * Повторить до тех пор, пока в куче не останется 1 элемент
     *
     * @param array сортируемый массив
     */
    public static void sort(int[] array) {
        buildHeap(array);
        while (heapSize > 1) {
            swap(array, 0, heapSize - 1);
            heapSize--;
            shiftDown(array, 0);
        }
    }

    /**
     * Формирование кучи. Поскольку элементы с номерами начиная с array.length / 2 + 1
     * это листья, то нужно переупорядочить поддеревья с корнями в индексах
     * от 0 до array.length / 2 (метод shiftDown вызывать в порядке убывания индексов)
     *
     * @param array - массив, из которого формируется куча
     */
    private static void buildHeap(int[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            shiftDown(array, i);
        }
    }

    /**
     * Переупорядочивает поддерево кучи начиная с узла i так, чтобы выполнялось
     * основное свойство кучи - array[parent] >= array[child].
     *
     * @param array - массив, из которого сформирована куча
     * @param i - корень поддерева, для которого происходит переупорядочивание
     */
    private static void shiftDown(int[] array, int i) {
        int left = left(i);
        int right = right(i);
        int largest = i;
        if (left < heapSize && array[largest] < array[left]) {
            largest = left;
        }
        if (right < heapSize && array[largest] < array[right]) {
            largest = right;
        }
        if (i != largest) {
            swap(array, i, largest);
            shiftDown(array, largest);
        }
    }

    /**
     * Возвращает индекс правого потомка текущего узла
     *
     * @param i индекс текущего узла кучи
     * @return индекс правого потомка
     */
    private static int right(int i) {
        return 2 * i + 2;
    }

    /**
     * Возвращает индекс левого потомка текущего узла
     *
     * @param i индекс текущего узла кучи
     * @return индекс левого потомка
     */
    private static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Меняет местами два элемента в массиве
     *
     * @param a массив
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
