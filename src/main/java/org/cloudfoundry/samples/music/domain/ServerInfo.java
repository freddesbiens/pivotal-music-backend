package org.cloudfoundry.samples.music.domain;

public class ServerInfo{

    private String hostAddress;
    private String hostName;

    public ServerInfo(String address, String name){
        this.hostAddress = address;
        this.hostName = name;
    }

    public String getHostAddress(){
        return this.hostAddress;
    }

    public void setHostAddress(String address){
        this.hostAddress = address;
    }

    public String getHostName(){
        return this.hostName;
    }

    public void setHostName(String name){
        this.hostName = name;
    }
}