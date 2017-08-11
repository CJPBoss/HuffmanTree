package kit;

import java.awt.geom.*;
import java.awt.geom.Line2D.Float;
import java.awt.*;
import javax.swing.*;


class drawtree extends JFrame{
	public PTreeNode tree;
	private String Tstr;
	drawtree(PTreeNode tree){
		this.tree = tree;
		Tstr = new String("");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		MapPane map = new MapPane(tree);
		add(map, BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);
	}
	
	private void LDR(PTreeNode t, int level){
		if (t == null){
			return ;
		}
		LDR(t.l, level + 1);
		if (t.data != '\0'){
			Tstr = Tstr + t.data;
		}
		t.level = level;
		LDR(t.r, level + 1);
	}
	
	private void LDRshowTree(PTreeNode t, String str){
		if (t == null){
			return ;
		}
		LDRshowTree(t.l, str + "0");
		
		System.out.format("Data : %c, way : %s, num_left : %d, num_right : %d, level : %d", t.data, str, t.left, t.right, t.level);
		if (t.data == '\0'){
			System.out.println(" <null>");
		}
		else{
			System.out.println("");
		}
		LDRshowTree(t.r, str + "1");
	}
	
	public void draw(){
		LDR(tree, 1);
		System.out.println(Tstr);
		String s = new String("");
		//LDRshowTree(tree, s);
		/*
		for (int i = 1; i < Tstr.length(); i += 2){
			System.out.print(Tstr.charAt(i));
		}
		System.out.println("hhh");
		*/
	}
}

class MapPane extends JPanel {
	PTreeNode tree;
	private int size;
	private int count;
	private int level;
	public MapPane(PTreeNode tree){
		this.tree = tree;
		size = 20; 
		level = 0;
		count = 0;
		LDR(tree);
	}
	
	private void drawNode(Graphics2D comp2D, float x, float y){
		comp2D.setColor(Color.blue);
		Rectangle2D.Float a = new Rectangle2D.Float((float)x, (float)y, (float)size, (float)size);
		comp2D.draw(a);
	}
	
	private void DLRdraw(Graphics2D comp2D, PTreeNode tree, float xx, float yy){
		if (tree == null){
			return ;
		}
		float x, y;
		x = (getWidth() - size) * tree.roop / count + size;
		y = getHeight() * tree.level / level - 2 * size;
		System.out.println(x + "," + y + " : " + tree.data + " : " + tree.level + " : " + tree.roop);
		
		GradientPaint gp = new GradientPaint(0F, 0F, Color.green, 350F, 350F, Color.orange, true);
		comp2D.setPaint(gp);
		if (xx >= 0){
			Line2D l = new Float(xx + size / 2, yy + size , x + size / 2, y);
			comp2D.draw(l);
		}
		
		drawNode(comp2D, x, y);

		comp2D.drawString(tree.data + "", x + size / 3, y + size / 2 + size / 5);
		if (tree.data == '\0'){
			comp2D.drawString("null", x, y + size / 2 + size / 5);
		}
		DLRdraw(comp2D, tree.l, x, y);
		DLRdraw(comp2D, tree.r, x, y);
	}
	
	private void LDR(PTreeNode tree){
		if (tree == null){
			return ;
		}
		LDR(tree.l);
		if (level < tree.level){
			level = tree.level;
		}
		tree.roop = count;
		count++;
		LDR(tree.r);
	}
	
	public void paintComponent(Graphics comp){
		System.out.println("Scene" + getHeight() + ", " + getWidth());
		Graphics2D comp2D = (Graphics2D)comp;
		comp2D.setColor(Color.blue);
		comp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		count = 0;
		LDR(tree);
		DLRdraw(comp2D, tree, -1, 0);
	}
}