package Collection1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 深入理解迭代器的原理 --》一个容器可以创建多个迭代器对象
 * 途径: 使用了内部类|匿名内部类
 *
 * 深入：
 * 1，使用内部类
 * 2.使用Iterable 实现foreach迭代
 * 3.加入末尾添加元素的方法
 * Created by lenovo on 2017/7/7.
 */
public class DeepList implements java.lang.Iterable{
    //数组存储
    private String elem[] = new String[5];
    //实际大小
    private int size = 0;
    //计数器 -->指针 游标
    private int coursor = -1;

//    在末尾添加元素


    public void add(String str) {
        if (this.size == elem.length) { //容量不足 --> 扩容
            elem = Arrays.copyOf(elem,elem.length*2+1);
        }

        elem[size] = str;//数组中加入元素 最后
        size++;//实际大小加一
    }
    private class MyIterator implements Iterator {
        //判断是否有下一个
        public boolean hasNext() {
            return coursor + 1 < size;
        }

        public int size() {
            return size = elem.length;
        }

        //    获取下一个
        public String next() {
            coursor++;
            return elem[coursor];
        }

        //    删除下一个
        public void remove() {
            //移动数组元素
            System.arraycopy(elem, coursor + 1, elem, coursor,/*可省略DeepList.this.*/size - (coursor + 1));
            //实际大小减一
           /*可省略DeepList.this.*/
            size--;
            //游标回退一步
           /*可省略DeepList.this.*/
            coursor--;
        }
    }


    public Iterator iterator1() {
        return new MyIterator();
    }


    //使用了内部类实现
    public Iterator iterator2() {
         class MyIterator implements Iterator {
            //判断是否有下一个
            public boolean hasNext() {
                return coursor + 1 < size;
            }

            public int size() {
                return size = elem.length;
            }

            //    获取下一个
            public String next() {
                coursor++;
                return elem[coursor];
            }

            //    删除下一个
            public void remove() {
                //移动数组元素
                System.arraycopy(elem, coursor + 1, elem, coursor,/*可省略DeepList.this.*/size - (coursor + 1));
                //实际大小减一
           /*可省略DeepList.this.*/
                size--;
                //游标回退一步
           /*可省略DeepList.this.*/
                coursor--;
            }
        }
        return new MyIterator();
    }

    //使用匿名内部类实现
    public Iterator iterator() {
        return new Iterator() { //创建Iterator迭代器接口 实现类（匿名）的对象

                //判断是否有下一个
                public boolean hasNext() {
                    return coursor + 1 < size;
                }

                public int size() {
                    return size = elem.length;
                }

                //    获取下一个
                public String next() {
                    coursor++;
                    return elem[coursor];
                }

                //    删除下一个
                public void remove() {
                    //移动数组元素
                    System.arraycopy(elem, coursor + 1, elem, coursor,/*可省略DeepList.this.*/size - (coursor + 1));
                    //实际大小减一
           /*可省略DeepList.this.*/
                    size--;
                    //游标回退一步
           /*可省略DeepList.this.*/
                    coursor--;
                }
        };
    }

    public static void main(String[] args) {
        DeepList list = new DeepList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("c");
        Iterator it = list.iterator();
        for (Object object:list) {//foreach增强for循环
            System.out.println(""+object);
        }
//        while (it.hasNext()) {
//            System.out.println("" + it.next());
//        }
        System.out.println("*********"+list.size);

        ArrayList list1 = new ArrayList();
        list1.add("A");
        list1.add("A");
        list1.add("A");
        for (Object object:list1) {//foreach增强for循环
            System.out.println(""+object);
        }

    }
}
