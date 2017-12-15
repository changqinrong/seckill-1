package com.sinosoft.seckill.controler;

import com.sinosoft.seckill.bean.Seckill;
import com.sinosoft.seckill.dto.ExeSeckillResult;
import com.sinosoft.seckill.dto.ExportSeckill;
import com.sinosoft.seckill.dto.SeckillResult;
import com.sinosoft.seckill.emun.SeckillEnum;
import com.sinosoft.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/seckill")
public class SeckillControler {
    @Autowired
    private SeckillService seckillService;

    /**
     * @return 获取秒杀产品列表
     */
    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("list",seckillService.getAllSeckill());
        return "seckill/list";
    }

    /**
     * @param seckillId 秒杀产品id
     * @return 秒杀产品详细信息
     */
    @GetMapping(value = "/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId,Model model) {
        model.addAttribute("seckill",seckillService.getSeckillById(seckillId));
        return "seckill/detail";
    }


    /**
     * @return 当前系统时间
     */
    @GetMapping(value = "/time/now")
    @ResponseBody
    public SeckillResult<Date> now() {
        Date nowTime = new Date();
        return new SeckillResult<Date>(SeckillEnum.SUCCESS.getCode(), SeckillEnum.SUCCESS.getMsg(), nowTime);
    }

    /**
     * 暴露秒杀信息
     *
     * @param seckillId
     * @return
     */
    @GetMapping("/{seckillId}/exposer")
    @ResponseBody
    public SeckillResult<ExportSeckill> exposer(@PathVariable("seckillId") Long seckillId) throws Exception {
        ExportSeckill exportSeckill = seckillService.exprotSeckillUrl(seckillId);
        return new SeckillResult<ExportSeckill>(SeckillEnum.SUCCESS.getCode(), SeckillEnum.SUCCESS.getMsg(),exportSeckill);
    }

    @PostMapping("/{seckillId}/{md5}/exeSeckill")
    @ResponseBody
    public SeckillResult<ExeSeckillResult> exeSeckill(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5, @CookieValue(value = "killPhone") String userPhone) throws Exception {
        ExeSeckillResult exeSeckillResult = seckillService.executeSeckill(seckillId, userPhone, md5);
        return new SeckillResult<ExeSeckillResult>(SeckillEnum.SUCCESS.getCode(), SeckillEnum.SUCCESS.getMsg(),exeSeckillResult);

    }

}
