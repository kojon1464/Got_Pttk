package com.po.grupa2.got.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String email;
	
	@JsonIgnore
	private String password;
	
	private boolean isEnabled;
	
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<BadgeAchievement> achievents;

	public User() {}
	
	public User(String email, String password, boolean isEnabled) {
		super();
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<BadgeAchievement> getAchievents() {
		return achievents;
	}

	public void setAchievents(Set<BadgeAchievement> achievents) {
		this.achievents = achievents;
	}
}
