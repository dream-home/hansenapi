package com.controller;

import com.Token;
import com.base.TokenUtil;
import com.base.page.Paging;
import com.base.page.RespBody;
import com.base.page.RespCodeEnum;
import com.constant.StatusType;
import com.model.SysUser;
import com.model.Task;
import com.service.TaskService;
import com.service.UserService;
import com.sysservice.ManageUserService;
import com.utils.codeutils.Md5Util;
import com.utils.toolutils.ToolUtil;
import com.vo.SysUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Resource
    private ManageUserService manageUserService;//用户业务层


    /**
     * 获取任务列表
     *
     * @param request
     * @param response
     * @param page
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public RespBody getTask(HttpServletRequest request, HttpServletResponse response, Paging page) throws Exception {
        // 创建返回对象
        RespBody respBody = new RespBody();
        String token = request.getHeader("token");
        //读取用户信息
        SysUserVo userVo = manageUserService.SysUserVo(token);
        SysUser user = manageUserService.readById(userVo.getId());
        if (user == null) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "用户不存在");
            return respBody;
        }
        List<Task> taskList = null;
        Task condition = new Task();
        Integer count = taskService.readCount(condition);
        if (count != null && count > 0) {
            taskList = taskService.readList(condition, page.getPageNumber(), page.getPageSize(), count);
        } else {
            taskList = Collections.emptyList();
        }
        page.setTotalCount(count);
        respBody.add(RespCodeEnum.SUCCESS.getCode(),"成功",page,taskList);
        return respBody;
    }


    /**
     * 新增任务
     *
     * @param request
     * @param response
     * @param vo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public RespBody addTask(HttpServletRequest request, HttpServletResponse response, @RequestBody Task vo) throws Exception {
        // 创建返回对象
        RespBody respBody = new RespBody();
        String token = request.getHeader("token");
        //读取用户信息
        SysUserVo userVo = manageUserService.SysUserVo(token);
        SysUser user = manageUserService.readById(userVo.getId());
        if (user == null) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "用户不存在");
            return respBody;
        }
        if (ToolUtil.isEmpty(vo.getName())) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "任务标题不能为空");
            return respBody;
        }
        vo.setStatus(StatusType.TRUE.getCode());
        taskService.create(vo);
        respBody.add(RespCodeEnum.SUCCESS.getCode(),"新增任务成功");
        return respBody;
    }

    /**
     * 修改任务
     *
     * @param request
     * @param response
     * @param vo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RespBody doTask(HttpServletRequest request, HttpServletResponse response, @RequestBody Task vo) throws Exception {
        // 创建返回对象
        RespBody respBody = new RespBody();
        String token = request.getHeader("token");
        //读取用户信息
        SysUserVo userVo = manageUserService.SysUserVo(token);
        SysUser user = manageUserService.readById(userVo.getId());
        if (user == null) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "用户不存在");
            return respBody;
        }
        if (ToolUtil.isEmpty(vo.getId())) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "任务id不能为空");
            return respBody;
        }
        if (ToolUtil.isEmpty(vo.getName())) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "任务标题不能为空");
            return respBody;
        }
        if (ToolUtil.isEmpty(vo.getTaskType())) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "任务类型不能为空");
            return respBody;
        }
        if (ToolUtil.isEmpty(vo.getRemark())) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "任务描述不能为空");
            return respBody;
        }
        vo.setStatus(StatusType.TRUE.getCode());
        Task updateTask = new Task();
        updateTask.setLinkImgPath(vo.getLinkImgPath());
        updateTask.setName(vo.getName());
        updateTask.setTaskType(vo.getTaskType());
        updateTask.setRemark(vo.getRemark());
        taskService.updateById(vo.getId(), vo);
        respBody.add(RespCodeEnum.SUCCESS.getCode(),"更新成功");
        return respBody;
    }

    /**
     * 修改任务
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public RespBody doDelTask(HttpServletRequest request, HttpServletResponse response, @RequestBody Map map) throws Exception {
        // 创建返回对象
        RespBody respBody = new RespBody();
        String token = request.getHeader("token");
        //读取用户信息
        SysUserVo userVo = manageUserService.SysUserVo(token);
        SysUser user = manageUserService.readById(userVo.getId());
        if (user == null) {
            respBody.add(RespCodeEnum.ERROR.getCode(), "用户不存在");
            return respBody;
        }
        String taskId = (String) map.get("id");
        if(ToolUtil.isEmpty(taskId)){
            respBody.add(RespCodeEnum.ERROR.getCode(), "任务不存在,请刷新");
            return respBody;
        }
        taskService.deleteById(taskId);
        respBody.add(RespCodeEnum.SUCCESS.getCode(), "任务已删除");
        return respBody;
    }

    public static void main(String[] args) {
        System.out.println(Md5Util.MD5Encode("123456","20170920"));
    }
}
