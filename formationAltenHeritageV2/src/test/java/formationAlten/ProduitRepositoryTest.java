package formationAlten;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import formationAlten.config.JpaConfig;
import formationAlten.entity.Produit;
import formationAlten.exception.ProduitException;
import formationAlten.repository.ProduitRepository;

@SpringJUnitConfig(JpaConfig.class)
public class ProduitRepositoryTest {
	
	@Autowired
	private ProduitRepository produitRepo;
	
//	@Test
//	void test() {
//		Produit p=produitRepo.findById(100L).orElseThrow(ProduitException::new);
//		assertTrue(p instanceof Produit);
//	}
	
	@Test
	void PageTest() {
		Pageable pageable=PageRequest.of(0, 2);
		Page<Produit>page=produitRepo.findAll(pageable);
		System.out.println(page);
		page.forEach(p->{
			System.out.println(p.getId());
		});
		page=produitRepo.findAll(page.nextOrLastPageable());
		System.out.println(page);
		page.forEach(p->{
			System.out.println(p.getId());
		});
	}

}
