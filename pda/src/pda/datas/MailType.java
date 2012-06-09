package pda.datas;

import pdaNetwork.misc.MailContent ;

public class MailType extends MailContent {
	private String type;
	
	public MailType() {
		super( "" , "" , "");
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String unType) {
		this.type = unType;
	}
}
