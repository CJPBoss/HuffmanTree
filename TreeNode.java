package kit;

class PTreeNode {
	public char data;
	public PTreeNode l;
	public PTreeNode r;
	public int Probability;
	public int left;
	public int right;
	public String s;/*describe the node, useful of paint a tree*/
	public int level;
	public int roop;
	public PTreeNode(PTreeNode a){/*deep copy*/
		data = a.data;
		left = a.left;
		right = a.right;
		l = a.l;
		r = a.r;
		Probability = a.Probability;
		level = a.level;
		s = new String(a.s);
	}
	
	public PTreeNode(int Probability, PTreeNode l, PTreeNode r){/*Node without data*/
		this.Probability = Probability;
		s = new String("(" + l.s + "+" + r.s + ")");
		level = 0;
		left = l.left + l.right + 1;
		right = r.left + r.right + 1;
		data = '\0';
		this.l = l;
		this.r = r;
	}
	
	public PTreeNode(char data, int Probability)/*leaf node, with data*/{
		level = 0;
		left = 0;
		right = 0;
		this.data = data;
		s = new String(data + "");
		this.Probability = Probability;
		l = null;
		r = null;
	}
}