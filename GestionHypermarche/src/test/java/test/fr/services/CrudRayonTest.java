package test.fr.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.models.Rayon;
import fr.services.impl.CrudRayonImpl;

public class CrudRayonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	

	public class CrudRayonT{

		
		@Test
		public void testAllRayon() {
			CrudRayonImpl crudr=new CrudRayonImpl();
			List<Rayon> lrayon=crudr.allRayon();
			assertNotNull(lrayon);
		}
	}

}
