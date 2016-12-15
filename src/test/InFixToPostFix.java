package test;

import algorithms.*;
import edu.princeton.cs.algs4.StdIn;

public class InFixToPostFix {
	Stack<String> OPTR;//�����ջoperator
	Stack<String> OPND;//������ջoperand
	Queue<String> INFIX;
/*	private void printStack(Stack<String> stk){
		for(String s:stk){
			StdOut.print(s+" ");
		}
		StdOut.println();
	}*/
	public String inFixToPostFix(String in){
		return inFixToPostFix(getInfixQueue(in));
		
	}
	public String inFixToPostFix(Queue<String> in){
		OPTR = new LinkedStack<>();
		OPND = new LinkedStack<>();
		INFIX = in;
		OPTR.push("#");
		INFIX.enQueue("#");
		String current = INFIX.deQueue();
		while(!OPTR.peek().equals("#")||!current.equals("#")){
//			StdOut.print("OPND:");printStack(OPND);
//			StdOut.print("OPTR:");printStack(OPTR);
//			StdOut.println("CURRENT:"+current);
			if(isOperator(current)){
				int p = precede(OPTR.peek(), current);
				if(p<0){
					OPTR.push(current);
					current = INFIX.deQueue();
				}else if(p>0){
					String opt = OPTR.pop();
					String b = OPND.pop();
					String a = OPND.pop();
					OPND.push(operate(a, opt, b));
				}else{
					OPTR.pop();
					current = INFIX.deQueue();
				}
			}else{
				OPND.push(current+"");
				current = INFIX.deQueue();
			}
		}
		return OPND.pop();
	}
	
	private Queue<String> getInfixQueue(String in) {
		Queue<String> q = new LinkedQueue<>();
		int i = 0;
		String next = null;
		while(i < in.length()){
			if(isOperator(in.charAt(i)+"")){
				next = in.charAt(i)+"";
				i++;
			}else{
				int begin = i;
				while(++i<in.length()&&!isOperator(in.charAt(i)+""));
				next = in.substring(begin, i);
			}
			q.enQueue(next);
		}
	return q;
}

	private String operate(String a, String opt, String b){
		return a+" "+b+" "+opt;
	}
	
	private boolean isOperator(String s){
		return "+".equals(s)||"-".equals(s)||"*".equals(s)||"/".equals(s)||"(".equals(s)||")".equals(s)||"#".equals(s);
	}
	
	//precede: ���ȼ���...֮��
	private int precede(String inStack, String current){
		if(("(".equals(inStack)&&")".equals(current)) || ("#".equals(inStack)&&"#".equals(current))){//��������
			return 0;
		}else if(")".equals(current)||"#".equals(current)){//ջ�����������ȼ���
			return 1;
		}else if("(".equals(inStack)||"".equals(current)||"#".equals(inStack)){//������ջ��ߣ�ջ�ڵ�
			return -1;
		}else if("+".equals(inStack)&&"-".equals(inStack)){//�����ң�+-*/
			if("*".equals(current)&&"/".equals(current)){
				return -1;
			} else {
				return 1;
			}
		}else if("*".equals(inStack)&&"/".equals(inStack)){
			return 1;
		}
		return -10;
	}
	
	public static void main(String[] args){
		InFixToPostFix to = new InFixToPostFix();
		EvaluatePostfix e = new EvaluatePostfix();
		String s;
		while(!(s = StdIn.readLine()).toLowerCase().equals("q"))
			System.out.println(e.evaluatePostfix(to.inFixToPostFix(s)));
	}
}
