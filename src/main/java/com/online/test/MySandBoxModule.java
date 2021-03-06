package com.online.test;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.kohsuke.MetaInfServices;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.jvm.sandbox.api.ProcessController.returnImmediately;

@MetaInfServices(Module.class)
@Information(id = "my-sandbox-module")// 模块名,在指定挂载进程后通过-d指定模块,配合@Command注解来唯一确定方法
public class MySandBoxModule implements Module {

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    @Command("changeMethodLogic")// 模块命令名
    public void changeMethodLogic() {
        new EventWatchBuilder(moduleEventWatcher)
                .onClass("com.example.service.impl.BankServiceImpl")// 想要对 TestAdd 这个类进行切面
                .onBehavior("getBank")// 想要对上面类的 test 方法进行切面
                .onWatch(new AdviceListener() {
                    @Override
                    public void before(Advice advice) throws Throwable {
                        System.out.println("sandbox 切入成功！！！");
                        System.out.println("挂载模块的代码逻辑将覆盖目标进程方法逻辑！！！");
                        List<Map> list = new ArrayList<Map>();
                        Map<Object, Object> map = new HashMap<Object, Object>();
                        map.put("sandbox流程控制", "改变返回值666");
                        list.add(map);
                        returnImmediately(list);
                    }
                });
    }
}