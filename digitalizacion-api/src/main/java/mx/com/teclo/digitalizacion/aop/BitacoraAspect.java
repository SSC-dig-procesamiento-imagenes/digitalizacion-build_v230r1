package mx.com.teclo.digitalizacion.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@Aspect
public class BitacoraAspect {

	//@Transactional
	//@After("execution(* mx.com.teclo.digitalizacion.api.rest..*(..))")
	public void logAfterTransfer(JoinPoint joinPoint){		
		System.out.println("Se ha llamado a un servicio");
		System.out.println("	entrando a: " + joinPoint);
        System.out.println(" 	argumentos: " + joinPoint.getArgs());
        System.out.println("      		en: " + joinPoint.getSourceLocation());
        System.out.println();
	}
}
