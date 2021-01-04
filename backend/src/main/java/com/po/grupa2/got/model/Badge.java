package com.po.grupa2.got.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "badges")
public class Badge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private Kind kind;
	
	private String description;
	
    @JsonIgnore
	@OneToMany(mappedBy = "badge")
	private Set<BadgeRank> ranks;

	public Badge() {
	}

	public Badge(Kind kind, String description) {
		this.kind = kind;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<BadgeRank> getRanks() {
		return ranks;
	}

	public void setRanks(Set<BadgeRank> rank) {
		this.ranks = rank;
	}

	public static enum Kind {
		IN_THE_MOUNTAINS, POPULAR, SMALL, GREAT, FOR_PERSISTENCE
	}
}
