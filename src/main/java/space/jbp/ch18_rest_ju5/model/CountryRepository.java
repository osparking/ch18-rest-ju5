package space.jbp.ch18_rest_ju5.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
}
