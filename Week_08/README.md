学习笔记-排序算法总结7个
//1.选择排序
//每次找最小值，然后放在待排序的起始位置
public static int[] selectionSort(int[] array) {
    int len = array.length;
    int minIndex , temp;
    for(int i = 0; i < len; i++) {
        minIndex = i;
        for(int j = i +1 ; j < len; j++) {
            if(array[j] < array[minIndex]) {
                minIndex = j;
            }
        }
        temp = array[i];
        array[i] = array[minIndex];
        array[minIndex] = temp;
    }
    return array;
}

//2.插入排序
//从前到后逐渐构建有序序列，对于新来的未排序数据，从已排序序列中从后往前扫描，找到相应的位置插入
public static int[] insertSort(int[] array) {
    int len = array.length;
    int preIndex , current;
    for(int i = 1; i < len; i++) {
        current = array[i];

        //从后往前查找current的合适插入位置
        preIndex = i -1;
        while(preIndex >=0 && current < array[preIndex]){//如果current要往前插，元素要往后移动挪位置
            array[preIndex + 1] = array[preIndex];
            preIndex--;
        }
        array[preIndex+1] = current;
    }
    return array;
}

//3.冒泡排序
//重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
//走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
// 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端，大的元素都会最先沉下去
public static int[] bubbleSort(int[] array) {
    int len = array.length;
    for(int i = 0; i < len - 1 ; i++) {
       for(int j = 0; j < len - 1 - i; j++) {
           if(array[j] > array[j+1]) {
               int temp = array[j];
               array[j]= array[j+1];
               array[j+1] = temp;
           }
       }
    }
    return array;
}

//4.希尔排序
//它是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。
//希尔排序又叫缩小增量排序
public static int[] shellSort(int[] array) {
    int len = array.length;
    for (int gap = (int)Math.floor(len / 2); gap > 0; gap = (int) Math.floor(gap / 2)) {
        for (int i = gap; i < len; i++) {//第一次仅仅是两两对比，第二次，第三次，分组的元素越来越多
            int j = i;
            int current = array[i];
            while (j - gap >= 0 && current < array[j - gap]) {
                array[j] = array[j - gap];
                j = j - gap;
            }
            array[j] = current;
        }
    }
    return array;
}

//5.快速排序
//快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
public int[] quickSort(int[] array, int left, int right) {
    int len = array.length,
            partitionIndex;

    if (left < right) {
        partitionIndex = partition(array, left, right);
        quickSort(array, left, partitionIndex-1);
        quickSort(array, partitionIndex+1, right);
    }
    return array;
}

private int partition(int[] array, int left, int right) {     // 分区操作
    int pivot = left,                  // 设定基准值（pivot） 这里把左边界作为基准
        index = pivot + 1;
    for (int i = index; i <= right; i++) {
        if (array[i] < array[pivot]) {
            swap(array, i, index);
            index++;//一番对比，得到pivot应该排的位置
        }
    }
    swap(array, pivot, index - 1);
    return index-1;
}

private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

//6.归并排序
//核心思想：先使每个子序列有序，再使子序列段间有序，最后将两个有序表合并成一个有序表
public void mergeSort(int[] array, int begin, int end) {
    if(end <= begin) return;
    int mid = (begin + end) >>1;

    mergeSort(array, begin, mid);
    mergeSort(array, mid+1, end);
    merge(array, begin, mid, end);
}

private void merge(int[] array, int begin, int mid, int end) {
    int[] tmp = new int[end-begin+1];
    int i = begin, j = mid + 1, k= 0;

    while(i <= mid && j <= end) {
        tmp[k++] = array[i] <= array[j] ? array[i++] : array[j++];
    }

    while(i <= mid) tmp[k++] = array[i++];
    while(j <= end) tmp[k++] = array[j++];

    System.arraycopy(tmp,0,array,begin,tmp.length);
}



//7.计数排序 模板
//其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中
public static int[] countSort(int[] array) {
    //1.得到数列的最大值与最小值，并算出差值d
    int max = array[0];
    int min = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] > max) {
            max = array[i];
        }
        if(array[i] < min) {
            min = array[i];
        }
    }
    int d = max - min;
    //2.创建统计数组并计算统计对应元素个数
    int[] countArray = new int[d + 1];
    for (int i = 0; i < array.length; i++) {
        countArray[array[i] - min]++;
    }
    //3.统计数组变形，后面的元素等于前面的元素之和
    int sum = 0;
    for (int i = 0; i < countArray.length; i++) {
        sum += countArray[i];
        countArray[i] = sum;
    }
    //4.倒序遍历原始数组，从统计数组找到正确位置，输出到结果数组
    //例如，小绿是95分，找到countArray下标为5的元素，值是4，代表小绿的成绩排名是在第4位。
    //同时给countArray下标是5的元素值减1，从4变成3，代表着下次再遇到95分时，最终95分的排名是第3位。
    int[] sortedArray = new int[array.length];
    for (int i = array.length - 1; i > 0; i--) {
        sortedArray[countArray[array[i] - min] - 1] = array[i];//例如，排第4位，index 是3
        countArray[array[i] - min]--;//查找到之后，计数值减1,下次再遇到就会往前排一位
    }
    return sortedArray;
}

/*
计数排序看上去很强大，但是它存在两大局限性：
1.当数列最大最小值差距过大时，并不适用于计数排序
比如给定20个随机整数，范围在0到1亿之间，此时如果使用计数排序的话，就需要创建长度为1亿的数组，不但严重浪费了空间，而且时间复杂度也随之升高。
2.当数列元素不是整数时，并不适用于计数排序
如果数列中的元素都是小数，比如3.1415，或是0.00000001这样子，则无法创建对应的统计数组，这样显然无法进行计数排序。
正是由于这两大局限性，才使得计数排序不像快速排序、归并排序那样被人们广泛适用。
 */
