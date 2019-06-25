
package com.marman.controller;

import com.marman.modelos.Conectar;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class HomeController 
{
    private JdbcTemplate jdbctemplate;

    public HomeController() 
    {
        Conectar con = new Conectar();
        this.jdbctemplate=new JdbcTemplate(con.conectar());
        
    }
    
    
    
    @RequestMapping("home.htm")
    public ModelAndView home()
    {
        ModelAndView mav = new ModelAndView();
        String sql="select * from usuarios";
        List datos=this.jdbctemplate.queryForList(sql);
        mav.addObject("datos",datos);
        mav.setViewName("home");
        return mav;
    }
}
