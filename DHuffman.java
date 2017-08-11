package kit;

class DHuffman {
	public String code;
	public PTreeNode tablet;
	private String str;
	
	public DHuffman(String code, PTreeNode tablet){
		this.code = code;
		str = new String("");
		this.tablet = tablet;
	}
	
	public static String decode(String code, PTreeNode tablet){
		String str = new String("");
		PTreeNode t = new PTreeNode(tablet);
		for (int i = 0; i < code.length(); i++){
			if (code.charAt(i) == '0'){
				t = t.l;
			}
			else{
				t = t.r;
			}
			if (t.data != '\0'){
				str += t.data;
				t = new PTreeNode(tablet);
			}
		}
		return str;
	}
	
	public String decode(){
		PTreeNode t = new PTreeNode(tablet);
		for (int i = 0; i < code.length(); i++){
			if (code.charAt(i) == '0'){
				t = t.l;
			}
			else{
				t = t.r;
			}
			if (t.data != '\0'){
				str += t.data;
				t = new PTreeNode(tablet);
			}
		}
		
		return str;
	}
}
