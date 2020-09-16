package vn.t3h.data;

import java.util.ArrayList;
import java.util.List;

public class CardManager {
	
	public static final String KEY_IN_CARD = "SHOPPING_CARD";
	
	private List<CardInfo> items;
	
	public CardManager()
	{
		items = new ArrayList<CardInfo>();
	}
	
	public void clearItems()
	{
		items = new ArrayList<CardInfo>();
	}
	
	/*
	public void addItem(Product product) {
		for (CardInfo lineItem : items) {
			if(lineItem.productId().equals(product.getId())){
				lineItem.quality = lineItem.quality + 1;
				return;
			}
		}
		LineItem item = new LineItem(product, 1);
		this.items.add(item);		
	}
	*/
}
