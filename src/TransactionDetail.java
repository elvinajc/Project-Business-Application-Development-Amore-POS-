
public class TransactionDetail {

	private String transactionid,menuid;
	private int quantity;
	
	public TransactionDetail(String transactionid, String menuid, int quantity) {
		super();
		this.transactionid = transactionid;
		this.menuid = menuid;
		this.quantity = quantity;
	}
	
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
