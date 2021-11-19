/*
 * Copyright © 2021 iotdb-quality developer group (iotdb-quality@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.thu.iotdb.quality.util;

/**
 * 存放引用类型的循环队列
 *
 * @author Wang Haoyu
 */
public class CircularQueue<E> {

  private static int INITCAP = 64;

  private int head, tail, size, minLen;
  private E[] data;

  public CircularQueue(int capacity) {
    head = tail = size = 0;
    data = (E[]) new Object[capacity];
    minLen = Math.max(INITCAP, capacity);
  }

  public CircularQueue() {
    this(INITCAP);
  }

  /**
   * 向循环队列的队尾加入元素
   *
   * @param value 准备加入的元素
   */
  public void push(E value) {
    // 先判断队列是否满了，满了要扩容
    if (isFull()) {
      resize(data.length * 2);
    }
    data[tail] = value;
    // tail以循环的方式向后移一位
    tail = (tail + 1) % data.length;
    size++;
  }

  /**
   * 弹出循环队列的队头
   *
   * @return 队头元素
   */
  public E pop() {
    if (isEmpty()) {
      throw new IllegalArgumentException("Error: Queue is Empty!");
    }
    E ret = data[head];
    // head以循环的方式向后移一位
    head = (head + 1) % data.length;
    size--;
    // 缩容操作
    if (size < data.length / 4 && data.length / 2 >= minLen) {
      resize(data.length / 2);
    }
    return ret;
  }

  /**
   * 返回循环队列的队头
   *
   * @return 队头元素
   */
  public E getHead() {
    if (isEmpty()) {
      throw new IllegalArgumentException("Error: Queue is Empty!");
    }
    E ret = data[head];
    return ret;
  }

  /**
   * 判断循环队列是否为空
   *
   * @return 循环队列为空时返回true，否则返回false
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * 判断循环队列中的data数组是否已满
   *
   * @return 数组已满时返回true，否则返回false
   */
  private boolean isFull() {
    return size == data.length;
  }

  /**
   * 改变循环队列中data数组的大小
   *
   * @param newLength 新的数组大小
   */
  private void resize(int newLength) {
    E[] newData = (E[]) new Object[newLength];
    // 遍历循环队列的一种方式
    for (int i = 0; i < size; i++) {
      newData[i] = data[(head + i) % data.length];
    }
    data = newData;
    head = 0;
    tail = size; // 下一个待进队的位置
  }

  /**
   * 返回循环队列中指定索引的元素
   *
   * @param index 索引
   * @return 指定索引的元素
   */
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    return data[(head + index) % data.length];
  }

  /**
   * 返回循环队列中的元素个数
   *
   * @return 元素个数
   */
  public int getSize() {
    return size;
  }
}
