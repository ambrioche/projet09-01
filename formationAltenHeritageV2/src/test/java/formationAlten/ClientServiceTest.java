package formationAlten;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import formationAlten.config.JpaConfig;
import formationAlten.entity.Client;
import formationAlten.exception.ClientException;
import formationAlten.service.ClientService;

@SpringJUnitConfig(JpaConfig.class)
@Transactional
class ClientServiceTest {
	
		@Autowired
		private ClientService clientService;
	@Test
	void test() {
		assertNotNull(clientService);
	}
	@Test
	@Commit
	void insert() {
		Client c = new Client("karim", "karim", null, null, null, null);
		clientService.create(c);
		assertNotNull(clientService.getById(c.getId()));
	
		c.setNom("update");
		clientService.update(c);
		clientService.delete(c);
		clientService.getAll().forEach( v -> { System.out.println(v.getId());});
		assertThrows(ClientException.class, ()-> {clientService.getById(100L);});
	}

}
