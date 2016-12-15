package algorithms;

import java.util.Iterator;

public class LinkedQueue<Item> implements Queue<Item>, Iterable<Item> {
	/**�ṹ:
	 * O-> O -> ... -> O -> O
	 * first                last
	 * ����                               ��β
	 * */
	int N;
	Node first;//���ף�������ӵ�Ԫ��
	Node last;//��β�������ӵ�Ԫ��
	private class Node{
		Item item;
		Node next;
	}
	
	//���п�: 1.���ӳ�����(first��ָ��null) 2.δ��ӽڵ�(ͬ��)
	@Override
	public boolean isEmpty() {
		//����Ϊ��
		return first == null;
		//N == 0 ���
	}

	@Override
	public int size() {
		return N;
	}

	//��ӣ���žɶ�β����βָ��ָ���½ڵ㣬�ɶ�βnextָ���¶�β
	@Override
	public void enQueue(Item item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		//��last���ܲ�����
		if(isEmpty()) first = last;
		else oldLast.next = last;
		N++;
	}

	//���ӣ�����ָ�����
	@Override
	public Item deQueue() {
		if(isEmpty()) return null;
		Item item = first.item;
		first = first.next;
		//�ӿգ���βָ��ҲӦ�ÿ�
		if(isEmpty()) last = null;
		N--;
		return item;
	}
	
	private class LinkedQueueIterator implements Iterator<Item>{
		Node node = first;
		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public Item next() {
			Item item = node.item;
			node = node.next;
			return item;
		}
		
	}

	@Override
	public Iterator<Item> iterator() {
		return new LinkedQueueIterator();
	}
}
