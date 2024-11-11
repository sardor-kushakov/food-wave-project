package sarik.dev.foodwaveproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId; // unique ID

    @Column(nullable = false)
    private String street; // Ko'cha nomi yoki uy manzili (e.g., uy raqami, ko'cha nomi)

    @Column(nullable = false)
    private String city; // Shahar nomi

    @Column(nullable = false)
    private String district; // Tuman nomi (maxalliy hududni ifodalaydi)

    @Column(nullable = true)
    private String apartment; // Kvartira raqami (e.g., ko'p qavatli uy uchun)

    @Column(nullable = false)
    private String country; // Mamlakat nomi

    @Column(nullable = true)
    private String additionalInfo; // Qo'shimcha ma'lumotlar (e.g., qo'shimcha ko'rsatmalar yoki eshik raqami)

    // To'liq manzilni qaytaruvchi yordamchi metod
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder(street);

        if (apartment != null && !apartment.isEmpty()) {
            fullAddress.append(", Kvartira: ").append(apartment);
        }

        fullAddress.append(", ").append(district)
                .append(", ").append(city)
                .append(", ").append(country);

        if (additionalInfo != null && !additionalInfo.isEmpty()) {
            fullAddress.append(" (").append(additionalInfo).append(")");
        }

        return fullAddress.toString();
    }
}
