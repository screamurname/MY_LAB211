
import java.util.Random;

public class Model {

    private int[] array;

    public void GenerateArray(int size) { // tao mang random 
        array = new int[size];
        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rd.nextInt(size);
        }
    }

    public void BubbleSort(int[] array) { // thuat toan sap xep noi bot
        //thuc hien n - 1 buoc, duyet tu i = 0 cho toi n - 2
        for (int i = 0; i < array.length - 1; i++) {
            // dung 1 bien de luu chi so cua phan tu nho nhat 
            int minIndex = i;
            //duyet tat ca cac phan tu dung sau phan tu hien tai, va cap nhat chi so cua phan tu nho nhat
            for (int j = i + 1; j < array.length; j++) {
                //neu phan tu j - dang xet nho hon phan tu duoc cho la nho nhat(minIndex)
                if (array[j] < array[minIndex]) {
                    minIndex = j;  // cap nhat
                }
            } // sau vong lap for nay da tim thay vi tri cua phan tu co gia tri nho nhat
            //swap thang o chi so hien tai(i) va thang duoc coi la nho nhat ( minIndex)
            if (minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }

        }

    }

    public int[] getArray() { // lay mang hien tai
        return array;
    }

    public void setArray(int[] array) { // thiet lap mang moi
        this.array = array;
    }

}//end class
