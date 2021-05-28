
public class Menu {

	private String menuid,name;
	private int sellprice,ingredientprice;
	
	public Menu(String menuid, String name, int sellprice, int ingredientprice) {
		super();
		this.menuid = menuid;
		this.name = name;
		this.sellprice = sellprice;
		this.ingredientprice = ingredientprice;
	}
	
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSellprice() {
		return sellprice;
	}
	public void setSellprice(int sellprice) {
		this.sellprice = sellprice;
	}
	public int getIngredientprice() {
		return ingredientprice;
	}
	public void setIngredientprice(int ingredientprice) {
		this.ingredientprice = ingredientprice;
	}

}
