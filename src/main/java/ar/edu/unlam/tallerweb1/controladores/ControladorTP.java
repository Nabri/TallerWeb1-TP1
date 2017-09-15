package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Controller	
public class ControladorTP {

	@RequestMapping("/{operacion}/{valor1}/{valor2}")
	public ModelAndView operacionVariable(@PathVariable String operacion, @PathVariable Integer valor1, @PathVariable Integer valor2){
		ModelMap model = new ModelMap();
		Integer rtdo;
		
		if (operacion.equals("sumar")) {
			rtdo = valor1 + valor2;
		} else if (operacion.equals("restar")) {
			rtdo = valor1 - valor2;
		} else if (operacion.equals("multiplicar")) {
			rtdo = valor1 * valor2;
		} else if (operacion.equals("dividir")) {
			if (valor2 != 0) {
				rtdo = valor1 / valor2;
			} else {
				return new ModelAndView("redirect:/errorOperacion");
			}
		} else {
			return new ModelAndView("redirect:/errorOperacion");
		}
		
		model.put("operation", operacion);
		model.put("primerValor", valor1);
		model.put("segundoValor", valor2);
		model.put("resultado", rtdo);
		
		return new ModelAndView("resultado", model);
	}
	
	@RequestMapping("/errorOperacion")
	public ModelAndView irALogin() {
		ModelMap model = new ModelMap();
		return new ModelAndView("errorOperacion", model);
	}
}
