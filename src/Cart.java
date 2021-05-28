
public class Cart {

	private String cartId,cartName;
	private int cartPrice,cartQuantity;
	
	public Cart(String cartId, String cartName, int cartPrice, int cartQuantity) {
		super();
		this.cartId = cartId;
		this.cartName = cartName;
		this.cartPrice = cartPrice;
		this.cartQuantity = cartQuantity;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getCartName() {
		return cartName;
	}

	public void setCartName(String cartName) {
		this.cartName = cartName;
	}

	public int getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}

	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}
	
	public Object[] returnAsObject() {
		Object[] o = new Object[4];
		o[0]=this.cartId;
		o[1]=this.cartName;
		o[2]=this.cartPrice;
		o[3]=this.cartQuantity;
		return o;
		
	}
}
