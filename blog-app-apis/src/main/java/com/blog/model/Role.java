package com.blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {

	@Id
	private int id;
	private String name;
	@ManyToMany(mappedBy="roles")
	private List<User> users = new ArrayList<> ();
	
	public String getName() {
		return this.name;
	}

	public void setUser(User user) {
			users.add(user);
	}
}
