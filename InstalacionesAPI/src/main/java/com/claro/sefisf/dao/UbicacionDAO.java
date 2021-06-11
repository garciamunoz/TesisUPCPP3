package com.claro.sefisf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claro.sefisf.entity.Ubicacion;

@Repository
public interface UbicacionDAO extends JpaRepository<Ubicacion, Integer>{
	
	@Query("SELECT u FROM Ubicacion u WHERE u.distrito= :dist and u.ciudad= :ciu")
	List<Ubicacion> listarPorCiudadDistritro(@Param("dist") String distrito, 
			  @Param("ciu") String ciudad);

}
