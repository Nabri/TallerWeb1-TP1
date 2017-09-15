package ar.edu.unlam.tallerweb1.modelo;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.List;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.User;
import junit.framework.Assert;

public class TestTP extends SpringTest {

	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void testQueBuscaTodasLasFarmaciasDeTurnoLosDiasMartes() {
		Farmacia farmacia1 = new Farmacia("Farmacity", "12345678");
		Farmacia farmacia2 = new Farmacia("Dr Remedio", "12378945");
		Farmacia farmacia3 = new Farmacia("El remedio loco", "74859612");
		Farmacia farmacia4 = new Farmacia("El Dr Chapatin", "64157896");

		farmacia1.setDiaDeTurno("Martes");
		farmacia2.setDiaDeTurno("Jueves");
		farmacia3.setDiaDeTurno("Domingo");
		farmacia4.setDiaDeTurno("Martes");

		getSession().save(farmacia1);
		getSession().save(farmacia2);
		getSession().save(farmacia3);
		getSession().save(farmacia4);

		List<Farmacia> listaDeFarmacias = getSession().createCriteria(Farmacia.class)
				.add(Restrictions.eq("diaDeTurno", "Martes"))
				.list();
		
		String esperado = "Martes";
		String obtenido = listaDeFarmacias.get(0).getDiaDeTurno();
		
		Assert.assertEquals(esperado, obtenido);
		assertThat(listaDeFarmacias).hasSize(2);
	}

	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void testQueBuscaTodasLasFarmaciasDeUnaCalle() {
		Farmacia farmacia1 = new Farmacia("Farmacity", "12345678");
		Farmacia farmacia2 = new Farmacia("Dr Remedio", "12378945");
		Farmacia farmacia3 = new Farmacia("El remedio loco", "74859612");
		Farmacia farmacia4 = new Farmacia("El Dr Chapatin", "64157896");
		Barrio barrioX = new Barrio("Barrio X");
		Direccion direccionA = new Direccion("CalleA", "123", barrioX);
		Direccion direccionB = new Direccion("CalleA", "456", barrioX);
		Direccion direccionC = new Direccion("CalleB", "123", barrioX);
		Direccion direccionD = new Direccion("CalleC", "456", barrioX);

		farmacia1.setDireccion(direccionA);
		farmacia2.setDireccion(direccionB);
		farmacia3.setDireccion(direccionC);
		farmacia4.setDireccion(direccionD);

		getSession().save(barrioX);
		getSession().save(farmacia1);
		getSession().save(farmacia2);
		getSession().save(farmacia3);
		getSession().save(farmacia4);

		List<Farmacia> listaDeFarmacias = getSession().createCriteria(Farmacia.class)
				.createAlias("direccion", "dir")
				.add(Restrictions.eq("dir.calle", "CalleA"))
				.list();

		String esperado = "CalleA";
		String obtenido = listaDeFarmacias.get(0).getDireccion().getCalle();
		
		Assert.assertEquals(esperado, obtenido);		
		assertThat(listaDeFarmacias).hasSize(2);
	}

	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void testQueBuscaTodasLasFarmaciasDeUnBarrio() {
		Farmacia farmacia1 = new Farmacia("Farmacity", "12345678");
		Farmacia farmacia2 = new Farmacia("Dr Remedio", "12378945");
		Farmacia farmacia3 = new Farmacia("El remedio loco", "74859612");
		Farmacia farmacia4 = new Farmacia("El Dr Chapatin", "64157896");
		Barrio barrioX = new Barrio("Barrio X");
		Barrio barrioY = new Barrio("Barrio Y");
		Barrio barrioZ = new Barrio("Barrio Z");
		Direccion direccionA = new Direccion("CalleA", "123", barrioX);
		Direccion direccionB = new Direccion("CalleB", "456", barrioY);
		Direccion direccionC = new Direccion("CalleC", "789", barrioZ);
		Direccion direccionD = new Direccion("CalleD", "101", barrioX);

		farmacia1.setDireccion(direccionA);
		farmacia2.setDireccion(direccionB);
		farmacia3.setDireccion(direccionC);
		farmacia4.setDireccion(direccionD);

		getSession().save(direccionA);
		getSession().save(direccionB);
		getSession().save(direccionC);
		getSession().save(direccionD);
		getSession().save(barrioX);
		getSession().save(barrioY);
		getSession().save(barrioZ);
		getSession().save(farmacia1);
		getSession().save(farmacia2);
		getSession().save(farmacia3);
		getSession().save(farmacia4);

		List<Farmacia> listaDeFarmacias = getSession().createCriteria(Farmacia.class, "farm")
				.createAlias("farm.direccion", "dir")
				.createAlias("dir.barrio", "bar")
				.add(Restrictions.eq("bar.nombre", "Barrio X"))
				.list();

		String esperado = "Barrio X";
		String obtenido = listaDeFarmacias.get(0).getDireccion().getBarrio().getNombre();
		
		Assert.assertEquals(esperado, obtenido);		
		assertThat(listaDeFarmacias).hasSize(2);
	}
}
