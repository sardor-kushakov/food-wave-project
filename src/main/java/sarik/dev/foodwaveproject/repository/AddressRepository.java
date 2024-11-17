package sarik.dev.foodwaveproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwaveproject.entity.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // Shahar bo'yicha manzillarni topish
    List<Address> findByCity(String city);

    // Tuman bo'yicha manzillarni topish
    List<Address> findByDistrict(String district);

    // Mamlakat bo'yicha manzillarni topish
    List<Address> findByCountry(String country);

    // Ko'cha va shahar bo'yicha manzillarni topish
    List<Address> findByStreetAndCity(String street, String city);
}
