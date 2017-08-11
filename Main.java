package kit;

import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		String str = new String("My name is Barry Allen. And I'm the fastest man in the world. I'm the Flash!");
		Huffman hf = new Huffman(str);
		hf.start();
		System.out.println(hf.encodeString);
		drawtree dt = new drawtree(hf.getTree());
		dt.draw();
	}
}
/*System.out.println("Input the string you want to decode by Huffman");
Scanner in = new Scanner(System.in);
String str = in.nextLine().toString();
String str = new String("My name is Barry Allen. And I'm the fastest man in the world. I'm the Flash!");
Huffman hf = new Huffman(str);
hf.start();
System.out.println("encode : " + hf.encodeString);
hf.showAllTree();
//hf.drawTree();

String code = in.nextLine().toString();
DHuffman dhf = new DHuffman(code, hf.Tree);
System.out.println(dhf.decode());
in.close();*/
/*
My name is Barry Allen. And I'm the fastest man in the world. I'm the Flash!
abcdefghijklmnopqrstuvwxyz
0100011001000101111001111111010001101111100001001111001110011100100100001110010101011101101111101000011101011011110011101101100111110010101000110100100111011001111010101101111101010001111111001011000110110110010101000110100100110100111111100010101111111010001110110110011111001010100011010001000001011100111101000010010
110 10 10 10 10 111111
i    ee
*/
/*
class A{
	public int data;
	public A(int i){
		data = i;
	}
	public A(A a){
		data = a.data;
	}
}
A a = new A(10);
A b = new A(a);//deep copy
b.data = 9;
System.out.println(a.data);
*/