学习笔记 之分析 Queue 和 Priority Queue 的源码
1. Queue 接口, “先入先出”, 主要有三类方法：
1)插入 (add, offer)
2)删除 (remove, poll) 
3)抛head元素 (peek, element)

其中 （add，offer） 类似,如果capacity限制，都会抛异常

(remove，poll) 与 （element，peek），都存在是否抛异常的差别
 

2.PriorityQueue 是一个实体类，不是接口. 它的默认长度是11, DEFAULT_INITIAL_CAPACITY = 11
Priority该词体现在类中使用了平衡二叉堆，提高了查询效率.
数据由内部的一个 Object 数组(queue)存储，这个数组本质上是一个二叉堆,queue[n]的两个子节点分别是queue[2n+1]和queue[2*(n+1)].这个优先队列通过自定义的comparator或者数值的自然顺序排序：该排序作用于堆中的每个节点n，以及n的子节点 d， n<=d. 只要队列不是空的，显然queue[0]就是最小值.

1)插入 add和offer 是一样的，插入查出capacity 超出时，会利用grow函数自动增加容量 （Double size if small than 64; else grow by 50%）,免除了异常的风险
此外会调用一个 siftUp 函数重新平衡堆

private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x, queue, comparator);
        else
            siftUpComparable(k, x, queue);
    }

//操作是这样的：尝试在初始位置k插入值x
//先找到k的parent, 然后插入值根parent对比。大，直接做儿子；小，则父子index互移，继续选举，直到停止
private static <T> void siftUpComparable(int k, T x, Object[] es) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = es[parent];//父值
            if (key.compareTo((T) e) >= 0)
                break;
            es[k] = e;//父值放入目标k位置
            k = parent;//parent index 变成目标位置，重新选举，新插入值可能还需要继续往上升
        }
        es[k] = key;//最终升不动了，x值放入最后停止的index位置
    }

2)poll 操作，做了很大增强; 没有了remove 这个函数

以poll函数为例，分析: 出列本质上是删除二叉堆的根节点，然后把堆存储的最后那个节点移到填在根节点处，再从上而下调整父节点与它的子节点。
这里不明白，为什么是最后那个节点变成了根节点？ 因为队列变短了,这里size自减1了，但是queue[size-1]的数值不能湮灭，所以从它开始平衡。


在出列操作时，首先移除数组的最后一个元素，然后调用siftDownComparable或siftDownUsingComparator方法进行二叉堆的重组，最后返回队列的第一个元素。
public E poll() {
        final Object[] es;
        final E result;

        if ((result = (E) ((es = queue)[0])) != null) {
            modCount++;
            final int n;
            final E x = (E) es[(n = --size)];
            es[n] = null;
            if (n > 0) {
                final Comparator<? super E> cmp;
                if ((cmp = comparator) == null)
                    siftDownComparable(0, x, es, n); //没有比较器走这里
                else
                    siftDownUsingComparator(0, x, es, n, cmp);//定义了比较器
            }
        }
        return result;
   }


//这里的pop把最小的queue[0]抛出去了，相当于根节点就没有了，整个堆要重新调整
//siftDownComparable 函数的作用:把x = queue[size - 1] 插在位置0上，通过不断把x沉降，至到它小于等于它的子节点，或者变成一个叶子，来保持整个堆的平衡

    private static <T> void siftDownComparable(int k, T x, Object[] es, int n) {
        // assert n > 0;
        Comparable<? super T> key = (Comparable<? super T>)x;
        int half = n >>> 1;           // loop while a non-leaf n/2
        while (k < half) { //k=0 left = 1 right =2
            int child = (k << 1) + 1; // assume left child is least
            Object c = es[child]; //c = left
            int right = child + 1; //right = left + 1
            if (right < n && 
                ((Comparable<? super T>) c).compareTo((T) es[right]) > 0) //if left > right , switch right to child and c
                c = es[child = right];
            if (key.compareTo((T) c) <= 0) //if es[size - 1] 少于 根节点的最小子节点，则结束寻找,则queue[size-1]就直接是根节点
                break;
            es[k] = c; //es[size -1] 太大，则1或者2会充当充当根节点位置es[0],
            k = child; //1或者2重新下一论新的平衡, while将遍历到所有节点
        }
        es[k] = key;
    }


