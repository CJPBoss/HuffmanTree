package kit;

class Huffman {
	public String str;
	public String encodeString;
	public int[] Probability;/*Probability of char to come up*/
	public String[] encode;
	public int len;/*the rest TreeNode of tnList*/
	public PTreeNode tnList[];/**/
	public PTreeNode Tree;/*Huffman Tree*/
	
	public Huffman(String str){
		len = 0;
		this.str = str;
		encodeString = "";
		Probability = new int[256];
		tnList = null;
		Tree = null;
		encode = new String[256];
	}
	
	public Huffman(){
		len = 0;
		this.str = "";
		Probability = new int[256];
		tnList = null;
		Tree = null;
	}
	
	public String encode(String str){
		this.str = str;
		return start();
	}
	
	private void getProbability(){
		for (int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if (Probability[c] == 0){
				len++;
			}
			Probability[c]++;
		}
	}
	
	private void sortTreebyProbability(){
		for (int i = 0; i < len; i++){
			for (int j = i + 1; j < len; j++){
				if (tnList[i] == null){
					tnList[i] = tnList[j];
					tnList[j] = null;
					continue;
				}
				if (tnList[i].Probability > tnList[j].Probability){
					PTreeNode t = tnList[i];
					tnList[i] = tnList[j];
					tnList[j] = t;
				}
			}
		}
	}
	
	private void mixTreeNode(int i, int j){
		PTreeNode newTreeNode = new PTreeNode(tnList[i].Probability + tnList[j].Probability, tnList[i], tnList[j]);
		tnList[i] = newTreeNode;
		tnList[j] = tnList[len - 1];
		tnList[len - 1] = null;
		len--;
		sortTreebyProbability();
	}
	
	public void DLR(PTreeNode t){
		if (t == null){
			return ;
		}
		if (t.data == '\0'){
			System.out.format("<%s> ", "null");
		}else{
			System.out.format("[%c] ", t.data);
		}
		DLR(t.l);
		DLR(t.r);
	}
	
	public void LDR(PTreeNode t){
		if (t == null){
			return ;
		}
		LDR(t.l);
		if (t.data == '\0'){
			System.out.format("<%s> ", "null");
		}else{
			System.out.format("[%c] ", t.data);
		}
		LDR(t.r);
	}
	
	public void LRD(PTreeNode t, String list){
		if (t == null){
			return ;
		}
		LRD(t.l, list + "" + 0);
		LRD(t.r, list + "" + 1);
		if (t.data != '\0'){
			encode[(int)t.data] = new String(list);
		}
	}
	
	public void showAllTree(){
		for (int i = 0; i < len; i++){
			System.out.print(tnList[i].s + " ");
		}
		System.out.println("");
	}
	
	public PTreeNode getTree(){
		if (Tree != null){
			return Tree;
		}
		getProbability();
		tnList = new PTreeNode[len];
		int l = 0;
		for (int i = 0; i < 256; i++){
			encode[i] = new String("");
			if (Probability[i] == 0){
				continue;
			}
			tnList[l] = new PTreeNode((char)i, Probability[i]);
			l++;
		}
		sortTreebyProbability();
		
		while (len != 1){
			mixTreeNode(0, 1);
		}
		Tree = tnList[0];
		return Tree;
	}
	
	public void drawTree(){
		String s = Tree.s;
		int[] pos = new int[s.length()];
		int loc = 0;
		for (int i = 0; i < s.length(); i++){
			if (s.charAt(i) != '('&& s.charAt(i) != ')'){
				pos[i] = loc;
				loc++;
			}else{
				pos[i] = 0;
			}
		}
		for (int i = 0; i < s.length(); i++){
			System.out.print(pos[i] + "");
		}
		
	}
	
	public String[] getTablet(){
		if (Tree == null){
			getTree();
		}
		String list = "";
		LRD(Tree, list);
		return encode;
	}
	
	public String getCode(){
		if (Tree == null){
			getTree();
		}
		for (int i = 0; i < str.length(); i++){
			encodeString += encode[str.charAt(i)];
		}
		return encodeString;
	}
	
	public String start(){
		getTree();
		getTablet();
		return getCode();
	}
}
