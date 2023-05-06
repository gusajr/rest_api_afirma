package com.api.microservicio.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.microservicio.model.Paginacion;
import com.api.microservicio.model.Usuario;

@Repository
@Transactional
public class MicroservicioDao{
    
    @Autowired
    private JdbcTemplate template;

    Logger logger;

    public void setTemplate(JdbcTemplate template) {    
        this.template = template;    
    }
    
    public int crearUsuario(Usuario usuario){
    	try{
            Object[] usuarioObj = new Object[] { usuario.getNombre(),
                usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getFechaNacimiento() };
            String sql="insert into usuario (nombre,apellido,correoElectronico,fechaNacimiento) values (?,?,?,?)";      
            return template.update(sql,usuarioObj);
        }catch(Exception e){
            logger.info("ERROR: "+e.toString());
            return 0;
        }
    }  
    
    public List<Usuario> obtenerUsuario(String idUsuario){
        String sql="select * from usuario where id="+idUsuario;      
        return template.query(sql, new BeanPropertyRowMapper<Usuario>(Usuario.class));       
    }    

    public List<Usuario> obtenerUsuarios(){
        String sql="select * from usuario";      
        return template.query(sql, new BeanPropertyRowMapper<Usuario>(Usuario.class));       
    }  
    
    public List<Usuario> obtenerUsuariosPaginacion(Paginacion paginacion){
    	if(paginacion.getPagina()<1) {
    		paginacion.setPagina(1);
    	}
    	int elementos = (paginacion.getPagina()-1)*paginacion.getNumeroElementos();
        String sql="select * from usuario order by id limit "+elementos+" offset "+(elementos+paginacion.getNumeroElementos()-1);      
        return template.query(sql, new BeanPropertyRowMapper<Usuario>(Usuario.class));
    }  
    

    public int actualizarUsuario(Usuario usuario){
        try{
            Object[] usuarioObj = new Object[] { usuario.getNombre(),
                    usuario.getApellido(), usuario.getCorreoElectronico(), usuario.getFechaNacimiento(), usuario.getId()};
            String sql="update usuario set nombre = ?,  apellido = ?, correoElectronico = ?, fechaNacimiento = ? where id = ?";      
            return template.update(sql,usuarioObj);
        }catch(Exception e){

            return 0;
        }
    }
    
    public int eliminarUsuario(String idUsuario){
        try{
            Object[] usuarioObj = new Object[] { idUsuario };
            String sql="delete from usuario where id=?";      
            return template.update(sql,usuarioObj);
        }catch(Exception e){
            return 0;
        }
    }
}