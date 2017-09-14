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

public class UserTest extends SpringTest{

	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void testUsuario(){
		User miUser = new User();
		miUser.setNombre("Jose");
		miUser.setApellido("Perez");
		miUser.setEdad(30);
		User miUser2 = new User();
		miUser2.setNombre("Jose");
		miUser2.setApellido("Rodriguez");
		miUser2.setEdad(43);
		User miUser3 = new User();
		miUser3.setNombre("Manuel");
		miUser3.setApellido("Lee");
		miUser3.setEdad(30);
		getSession().save(miUser);
		getSession().save(miUser2);
		getSession().save(miUser3);
		List<User> todo = getSession().createCriteria(User.class)
				.list();
		assertEquals(3, todo.size());
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void testDeTodosLosQueSeLlamenJose(){
		User miUser = new User();
		miUser.setNombre("Jose");
		miUser.setApellido("Perez");
		miUser.setEdad(30);
		User miUser2 = new User();
		miUser2.setNombre("Jose");
		miUser2.setApellido("Rodriguez");
		miUser2.setEdad(43);
		User miUser3 = new User();
		miUser3.setNombre("Manuel");
		miUser3.setApellido("Lee");
		miUser3.setEdad(30);
		getSession().save(miUser);
		getSession().save(miUser2);
		getSession().save(miUser3);
		
		List<User> listJose = getSession().createCriteria(User.class)
				.add(Restrictions.eq("nombre" , "Jose"))
				.add(Restrictions.or(Restrictions.eq("edad", 30), (Restrictions.eq("edad", 43))))
				.list();
		
		assertThat(listJose).hasSize(2);
		
	}
}
