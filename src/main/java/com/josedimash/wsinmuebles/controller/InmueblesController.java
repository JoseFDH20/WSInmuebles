package com.josedimash.wsinmuebles.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.josedimash.wsinmuebles.model.InmueblesRequestBody;
import com.josedimash.wsinmuebles.model.InmueblesVO;
import com.josedimash.wsinmuebles.service.InmueblesService;
import com.josedimash.wsinmuebles.util.Utilerias;

@Controller
public class InmueblesController {

	@Autowired
	private InmueblesService inmueblesService;

	@GetMapping("/")
	public String redirectToIndex(Model model) {
		model.addAttribute("inmuebles", inmueblesService.obtenerTodos());
		return "index";
	}

	@GetMapping("/index")
	public String showInmueblesList(Model model) {
		model.addAttribute("inmuebles", inmueblesService.obtenerTodos());
		return "index";
	}

	@GetMapping("/add-inmueble")
	public String showAddForm(InmueblesRequestBody inmueblesRequestBody) {
		return "add-inmueble";
	}

	@PostMapping("/addInmueble")
	public String addInmueble(@Valid InmueblesRequestBody inmueblesRequestBody, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addInmueble";
		}

		inmueblesService.agregarNuevoInmuebleByID(inmueblesRequestBody.getNombre(), inmueblesRequestBody.getDireccion(),
				inmueblesRequestBody.getTelefono());
		return "redirect:/index";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		InmueblesVO item = inmueblesService.obtenerByID(id);
		model.addAttribute("inmueblesVO", item);
		return "update-inmueble";
	}

	@PostMapping("/update/{id}")
	public String updateInmueble(@PathVariable("id") Integer id, @Valid InmueblesVO inmueblesVO, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			inmueblesVO.setFiid(Utilerias.toInt(id));
			return "update-inmueble";
		}

		inmueblesService.actualizarInmuebleByID(Utilerias.toInt(id), inmueblesVO.getFcnombre(),
				inmueblesVO.getFcdireccion(), inmueblesVO.getFctelefono());

		return "redirect:/index";
	}

	@GetMapping("/delete/{id}")
	public String deleteInmueble(@PathVariable("id") Integer id, Model model) {
		inmueblesService.eliminarInmuebleByID(id);
		return "redirect:/index";
	}
}
