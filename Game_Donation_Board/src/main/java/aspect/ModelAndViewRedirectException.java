package aspect;
import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewRedirectException extends RuntimeException{

	private final ModelAndView modelAndView;
	
	 public ModelAndViewRedirectException(ModelAndView modelAndView) {
	        this.modelAndView = modelAndView;
	    }

	    public ModelAndView getModelAndView() {
	        return modelAndView;
	    }
	
}
