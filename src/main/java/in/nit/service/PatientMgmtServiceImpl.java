package in.nit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.nit.bo.PatientBO;
import in.nit.dao.PatientDAO;
import in.nit.dto.PatientDTO;

@Service
public class PatientMgmtServiceImpl implements PatientMgmtService {

	@Autowired
	private PatientDAO dao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String register(PatientDTO dto) {

		int count=0;
		
		PatientBO bo=null;
		
		bo=new PatientBO();
		
		BeanUtils.copyProperties(dto,bo);
		
		count=dao.insert(bo);
		
		return count==0?"Patient Registration Failed":"Patient Registration succeded";
	}

}
