
public class TransactionHeader {

	private String transactionid,staffid,transactiondate;

	public TransactionHeader(String transactionid, String staffid, String transactiondate) {
		super();
		this.transactionid = transactionid;
		this.staffid = staffid;
		this.transactiondate = transactiondate;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(String transactiondate) {
		this.transactiondate = transactiondate;
	}
	
}
