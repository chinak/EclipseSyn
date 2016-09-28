package org.seckill.web;



import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
	
	private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		//list.jsp+model = ModelAndView
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list",list);
		return "list";//web-inf/jsp/"list".jsp 
	}
	
	@RequestMapping(value="/{seckillId}/detail",method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId")Long seckillId,Model model){
		if(seckillId==null){
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if(seckill==null){
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill",seckill);
		return "detail";
	}
	
	//ajax接口 return json
	@RequestMapping(value="/{seckillId}/exposer" ,method=RequestMethod.POST ,produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable Long seckillId){
		
		SeckillResult<Exposer> result ;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			
			result = new SeckillResult<Exposer>(true, exposer);
			
		} catch (Exception e) {
			
			logger.error(e.getMessage(),e);
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		
		
		return result;
		
	}
	
	@RequestMapping(value="/{seckillId}/{md5}/execution", produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId")Long seckillId,@PathVariable("md5")String md5,
			@CookieValue(value="killPhone",required=false)Long phone){
		//Spring valid can do it as well
		if(phone==null){
			return new SeckillResult<SeckillExcution>(false, "not registion");
		}
		try {
			SeckillExcution excution = seckillService.executeSeckill(seckillId, phone, md5);
			return new SeckillResult<SeckillExcution>(true, excution);
		} catch(RepeatKillException e){
			SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.REPEAT);
			logger.info("repoeatexception======================"+e);
			return new SeckillResult<SeckillExcution>(true,seckillExcution);
		}catch(SeckillCloseException e){
			SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.END);
			logger.info("closeexception======================"+e);
			return new SeckillResult<SeckillExcution>(true,seckillExcution);
		}catch (Exception e) {
			logger.info("exception======================"+e);
			logger.error(e.getMessage(),e);
			SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExcution>(true,seckillExcution);
		}
		
	}
	@RequestMapping(value="/time/now",method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time(){
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
	
	
	
	
	
	
	
	
	
	
	
}
