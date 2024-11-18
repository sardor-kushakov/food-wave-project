package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	@Column(nullable = false , unique = true)
	private String categoryName;

//	@OneToMany(mappedBy = "category", cascade =  CascadeType.ALL )
//	private List<Product> products;
}
