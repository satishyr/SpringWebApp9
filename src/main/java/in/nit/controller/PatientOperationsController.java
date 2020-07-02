package in.nit.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import in.nit.command.PatientCommand;
import in.nit.dto.PatientDTO;
import in.nit.service.PatientMgmtService;

@Controller
@SessionAttributes("patCmd")
public class PatientOperationsController {

	@Autowired
	private PatientMgmtService service;

	@ModelAttribute("patCmd")
	public PatientCommand getPatientCommand() {
		System.out.println("getPatientCommand()");

		return new PatientCommand();
	}

	@GetMapping("/corona.htm")
	public String showForm(@ModelAttribute("patCmd") PatientCommand cmd) {
		cmd.setLocation("Hyd");
		cmd.setHospital("Gandhi Hospital");

		System.out.println("PatientOperationsController.showForm()");

		return "patient_registration";
	}

	@PostMapping("/corona.htm")
	public String processForm(Map<String, Object> map, @ModelAttribute("patCmd") PatientCommand cmd, BindingResult br) {

		System.out.println("PatientOperationsController.processForm()");

		PatientDTO dto = null;
		String result = null;
		dto = new PatientDTO();
		BeanUtils.copyProperties(cmd, dto);

		result = service.register(dto);

		map.put("resMsg", result);

		return "show_result";
	}
}
