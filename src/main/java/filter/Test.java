package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class Test implements Filter{

	@Override
	public void doFilter(ServletRequest reqest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
	}
	
}
