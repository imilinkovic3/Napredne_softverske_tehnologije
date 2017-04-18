package rs.silab.nst.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import rs.silab.nst.model.Process;
import rs.silab.nst.model.User;
import rs.silab.nst.service.ProcessService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by kuzmanom on 4/15/2017.
 */
@Controller
@Scope("session")
@RequestMapping("/process/")
public class ProcessController {

    @Resource(name = "processService")
    private ProcessService processService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String process(HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        List<Process> processes = processService.findAll(user.getCompanyBean().getPib());

        for (Process process : processes) {
            process.setNodes();
            process.setText();
        }

        System.out.println(processes);

        return "process";
    }

    @RequestMapping(value = "/getAll/", method = RequestMethod.GET)
    public @ResponseBody  List<Process> getProcessTree(HttpSession session) {
        System.out.println("USAOooooooooooooooooooooooooooooooooooooo");
        User user = (User) session.getAttribute("sessionUser");
        List<Process> processes = processService.findAll(user.getCompanyBean().getPib());

        for (Process process : processes) {
            process.setNodes();
            process.setText();
        }

        System.out.println(processes);

        return processes;
    }
}
