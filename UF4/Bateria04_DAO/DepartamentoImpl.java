package Bateria04_DAO;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class DepartamentoImpl implements DepartamentoDAO {
	private static ODB db;

	DepartamentoImpl(){
		if (db == null) {
			db = ODBFactory.open("Departamento.DB");
		}
	}

	@Override
	public boolean InsertarDep(Departamento dep) {
		if (dep == null) {
			return false;
		} else {
			ICriterion where = Where.equal("deptno", dep.getDeptno());
			IQuery query = new CriteriaQuery(Departamento.class, where);
			Objects<Departamento> depart = db.getObjects(query);

			if (depart.size() > 0) {
				return false;
			}

			db.store(dep);
			return true;
		}
	}

	@Override
	public boolean EliminarDep(int deptno) {
		IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", deptno));
		Objects<Departamento> depart = db.getObjects(query);

		if (deptno > 0 || depart.size() > 0) {
			db.delete(depart.getFirst());
			db.commit();
			return true;
		}
		return false;
	}

	@Override
	public boolean ModificarDep(int deptno, Departamento dep) {
		Departamento depEdit = ConsultarDep(deptno);

		if (depEdit!=null) {
			depEdit.setDeptno(dep.getDeptno());
			depEdit.setDnombre(dep.getDnombre());
			depEdit.setLoc(dep.getLoc());
			db.store(depEdit);
			return true;
		}
		return false;
	}

	@Override
	public Departamento ConsultarDep(int deptno) {
		IQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", deptno));
		Objects<Departamento> depart = db.getObjects(query);  
		
		if(depart!=null && depart.size()>0) {
			Departamento depart2 = (Departamento) depart.getFirst();
			return depart2;  
		} else {
			System.out.println("No se ha encontrado el numero del departamento");
			return null;
		}
	}
}
