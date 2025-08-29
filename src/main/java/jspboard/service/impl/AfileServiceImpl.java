package jspboard.service.impl;

import java.util.List;

import jspboard.dao.AfileDao;
import jspboard.dao.impl.AfileDaoImpl;
import jspboard.model.Afile;
import jspboard.service.AfileService;

public class AfileServiceImpl implements AfileService {
	
	private AfileDao afileDao;
	
	public AfileServiceImpl() {
		afileDao = new AfileDaoImpl();
	}
	
	@Override
	public List<Afile> listAfile(int aid) throws Exception {
		return afileDao.selectAfile(aid);
	}
	
	@Override
	public Afile getAfile(int afid) throws Exception {
		return afileDao.getAfile(afid);
	}
	
	@Override
	public int registAfile(Afile afile) throws Exception {
		return afileDao.insertAfile(afile);
	}
	
	@Override
	public int modifyAfile(Afile afile) throws Exception {
		return afileDao.updateAfile(afile);
	}
	
	@Override
	public int removeAfile(int afid) throws Exception {
		return afileDao.deleteAfile(afid);
	}

	@Override
	public List<Afile> latestAfileList() throws Exception {
		return afileDao.latestAfileList();
	}
	
}
