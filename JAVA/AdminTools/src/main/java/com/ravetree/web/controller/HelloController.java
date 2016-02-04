package com.ravetree.web.controller;

import com.ravetree.model.bo.*;
import com.ravetree.model.dao.RavetreeDB;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    private static boolean reload = false;
    private static boolean firstLogin = true;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcomePage() {

        if (firstLogin) {
            firstLogin = false;
            reload = true;
        }
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("login");
		return model;

	}

    @RequestMapping(value = "/admin/reload", method = RequestMethod.GET)
    public String redirect() {
        reload = true;
        return "redirect:adminPage";
    }

	@RequestMapping(value = "/admin/**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();

        RavetreeDB db = new RavetreeDB();
        PortalStats portalStats = PortalStats.getInstance(db, reload);
		UserStats userStats = UserStats.getInstance(db, reload);

        ProjectStats projectStats = ProjectStats.getInstance(db, reload);
        WorkItemsStats workItemsStats = WorkItemsStats.getInstance(db, reload);
        AccountsStats accountsStats = AccountsStats.getInstance(db, reload);
        ContactsStats contactsStats = ContactsStats.getInstance(db, reload);

//        FilesStats filesStats = new FilesStats(db);
        PostsStats postsStats = PostsStats.getInstance(db, reload);
        EventsStats eventsStats = EventsStats.getInstance(db, reload);

        model.addObject("totalPortals", Long.toString(portalStats.totalPortals()));
		model.addObject("totalUsers", Long.toString(userStats.totalUsers()));
        model.addObject("portals", portalStats.getPortals());
        model.addObject("avgUsersPerPortal", Long.toString(portalStats.getAverageUsersPerPortal()));
        model.addObject("duplicatePortals", portalStats.getDuplicateDomainNames());

        model.addObject("totalProjects", Long.toString(projectStats.getTotalProjects()));
        model.addObject("totalWorkItems", Long.toString(workItemsStats.getTotalWorkItems()));
        model.addObject("totalAccounts", Long.toString(accountsStats.getTotalAccounts()));
        model.addObject("totalContacts", Long.toString(contactsStats.getTotalContacts()));
//        model.addObject("totalFiles", Long.toString(filesStats.totalFiles()));
        model.addObject("totalPosts", Long.toString(postsStats.getTotalPost()));
        model.addObject("totalEvents", Long.toString(eventsStats.getTotalEvents()));


		model.setViewName("admin");

        if(reload) {
            reload = false;
        }

		return model;
	}



	//Spring Security see this :
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;
	}
	
	

}