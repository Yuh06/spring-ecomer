package vn.t3h.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CardInfo {
	private int productId;
	private int quality;
	private int price;
	private String image;
	private String name;
}