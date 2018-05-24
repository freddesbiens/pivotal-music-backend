package org.cloudfoundry.samples.music.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.cloudfoundry.samples.music.domain.ApplicationInfo;
import org.cloudfoundry.samples.music.domain.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Autowired(required = false)
    private Cloud cloud;

    private Environment springEnvironment;

    @Autowired
    public InfoController(Environment springEnvironment) {
        this.springEnvironment = springEnvironment;
    }

    @RequestMapping(value = "/serverinfo")
    public ServerInfo serverInfo(){
        String address = "0.0.0.0";
        String name = "unknown";

        try{
            InetAddress inetAddress = InetAddress.getLocalHost();
            address = inetAddress.getHostAddress();
            name = inetAddress.getHostName();
        }
        catch(UnknownHostException uhex) { 
            // Return default value if we run into problems. 
        }

        return new ServerInfo(address, name);
    }

    @RequestMapping(value = "/appinfo")
    public ApplicationInfo info() {
        return new ApplicationInfo(springEnvironment.getActiveProfiles(), getServiceNames());
    }

    @RequestMapping(value = "/service")
    public List<ServiceInfo> showServiceInfo() {
        if (cloud != null) {
            return cloud.getServiceInfos();
        } else {
            return new ArrayList<>();
        }
    }

    private String[] getServiceNames() {
        if (cloud != null) {
            final List<ServiceInfo> serviceInfos = cloud.getServiceInfos();

            List<String> names = new ArrayList<>();
            for (ServiceInfo serviceInfo : serviceInfos) {
                names.add(serviceInfo.getId());
            }
            return names.toArray(new String[names.size()]);
        } else {
            return new String[]{};
        }
    }
}