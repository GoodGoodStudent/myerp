package com.puhuanyu.erp.myerp.qingController;

import com.puhuanyu.erp.myerp.bean.Shell;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShellController
{
    @ApiOperation("shell实体类参数测试")
    @PostMapping("/shell")
    public Shell shell(Shell shell)
    {
        return shell;
    }

    @ApiOperation("shell测试")
    @GetMapping("/shell")
    public String shell1(@ApiParam("订单编号") String id)
    {
        return "shell";
    }
}
