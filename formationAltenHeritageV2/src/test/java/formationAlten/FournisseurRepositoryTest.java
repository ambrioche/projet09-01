package formationAlten;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import formationAlten.config.JpaConfig;
import formationAlten.entity.Fournisseur;
import formationAlten.exception.FournisseurException;
import formationAlten.repository.FournisseurRepository;

@SpringJUnitConfig(JpaConfig.class)
public class FournisseurRepositoryTest {

	@Autowired
	private FournisseurRepository fournisseurRepo;
//	
//	@Test
//	void test() {
//		Fournisseur f=fournisseurRepo.findById(69L).orElseThrow(FournisseurException::new);
//		assertTrue(f instanceof Fournisseur);
//	}
	
	@Test
	void PageTest() {
		Pageable pageable=PageRequest.of(0, 2);
		Page<Fournisseur>page=fournisseurRepo.findAll(pageable);
		System.out.println(page);
		page.forEach(f->{
			System.out.println(f.getId());
		});
		page=fournisseurRepo.findAll(page.nextOrLastPageable());
		System.out.println(page);
		page.forEach(f->{
			System.out.println(f.getId());
		});
	}
}
