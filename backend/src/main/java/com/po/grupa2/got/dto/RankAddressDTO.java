package com.po.grupa2.got.dto;

import com.po.grupa2.got.model.Address;
import com.po.grupa2.got.model.BadgeRank;

public class RankAddressDTO {

	private Address address;
	
	private BadgeRank rank;

	public RankAddressDTO() {
	}

	public RankAddressDTO(Address address, BadgeRank rank) {
		this.address = address;
		this.rank = rank;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BadgeRank getRank() {
		return rank;
	}

	public void setRank(BadgeRank rank) {
		this.rank = rank;
	}
}
